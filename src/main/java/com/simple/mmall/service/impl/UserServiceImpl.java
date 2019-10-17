package com.simple.mmall.service.impl;

import com.simple.mmall.common.Const;
import com.simple.mmall.common.ServerResponse;
import com.simple.mmall.common.TokenCache;
import com.simple.mmall.dao.UserMapper;
import com.simple.mmall.exception.user.UserException;
import com.simple.mmall.entity.User;
import com.simple.mmall.service.IUserService;
import com.simple.mmall.util.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * 用户Service
 *
 * @author Simple
 * @create 2017-10-04 16:58
 **/
@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> login(String username, String password) {
        int resultCount = userMapper.selectUserCountByUsername(username);
        if (0 == resultCount) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        String md5Password = Md5Util.md5EncodeUtf8(password);
        User user = userMapper.selectUserByUsernamePassword(username, md5Password);
        if (null == user) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccessMessageData("登录成功", user);
    }

    /**
     * 使用事务来防止多个人同时注册同一用户名，Email
     *
     * @param user
     * @return
     */
    @Override
    @Transactional(rollbackFor = UserException.class)
    public ServerResponse<String> register(User user) {
        ServerResponse<String> validResponse = this.checkValid(user.getUsername(), Const.USERNAME);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }
        validResponse = this.checkValid(user.getEmail(), Const.EMAIL);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }

        user.setRole(Const.Role.ROLE_CUSTOMER);
        user.setPassword(Md5Util.md5EncodeUtf8(user.getPassword()));
        int resultCount = 0;
        try {
            resultCount = userMapper.insert(user);
        } catch (Exception e) {
            throw new UserException(01, "用户注册失败");
        }
        if (0 == resultCount) {
            return ServerResponse.createByErrorMessage("数据库插入失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    @Override
    public ServerResponse<String> checkValid(String str, String type) {
        if (StringUtils.isNotBlank(type)) {
            if (Const.USERNAME.equals(type)) {
                int resultCount = userMapper.selectUserCountByUsername(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            } else if (Const.EMAIL.equals(type)) {
                int resultCount = userMapper.selectUserCountByEmail(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("Email已存在");
                }
            } else {
                return ServerResponse.createByErrorMessage("无效的参数类型");
            }
        } else {
            return ServerResponse.createByErrorMessage("参数类型为空");
        }
        //注册时,用户存在为失败,用户不存在为成功
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    @Override
    public ServerResponse<String> forgetGetQuestion(String username) {
        ServerResponse<String> validResponse = this.checkValid(username, Const.USERNAME);
        if (validResponse.isSuccess()) {
            //用户名不存在
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        String question = userMapper.selectQuestionByUsername(username);
        if (StringUtils.isNotBlank(question)) {
            return ServerResponse.createBySuccessData(question);
        }
        return ServerResponse.createByErrorMessage("找回密码的问题是空的");
    }

    @Override
    public ServerResponse<String> forgetCheckAnswer(String username, String question, String answer) {
        int resultCount = userMapper.selectUesrCountByUsernameAndQAndA(username, question, answer);
        if (resultCount > 0) {
            String forgetToken = UUID.randomUUID().toString();
            //把token放到本地cache中
            TokenCache.setKey(TokenCache.TOKEN_PREFIX + username, forgetToken);
            return ServerResponse.createBySuccessData(forgetToken);
        }
        return ServerResponse.createByErrorMessage("问题的答案错误");
    }

    @Override
    public ServerResponse<String> forgetResetPassword(String username, String newPassword, String forgetToken) {
        //为空判断
        if (StringUtils.isBlank(username)) {
            return ServerResponse.createByErrorMessage("参数错误,用户名需要传递");
        }
        if (StringUtils.isBlank(newPassword)) {
            return ServerResponse.createByErrorMessage("参数错误,新密码需要传递");
        }
        if (StringUtils.isBlank(forgetToken)) {
            return ServerResponse.createByErrorMessage("参数错误,token需要传递");
        }
        //用户存在判断
        ServerResponse<String> validResponse = this.checkValid(username, Const.USERNAME);
        if (validResponse.isSuccess()) {
            //用户名不存在
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        //获取token
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX + username);
        //token判断
        if (StringUtils.isBlank(token)) {
            return ServerResponse.createByErrorMessage("Token无效或者已过期");
        }
        if (StringUtils.equals(forgetToken, token)) {
            //更新密码
            String md5Password = Md5Util.md5EncodeUtf8(newPassword);
            int rowCount = userMapper.updatePasswordByUsername(username, md5Password);
            if (rowCount > 0) {
                return ServerResponse.createBySuccessMessage("修改密码成功");
            }
        } else {
            return ServerResponse.createByErrorMessage("Token错误,请重新获取重置密码的Token");
        }
        return ServerResponse.createByErrorMessage("修改密码失败");
    }

    @Override
    @Transactional(rollbackFor = UserException.class)
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user) {
        if (StringUtils.isBlank(passwordOld)) {
            return ServerResponse.createByErrorMessage("旧密码不能为空");
        }
        if (StringUtils.isBlank(passwordNew)) {
            return ServerResponse.createByErrorMessage("新密码不能为空");
        }
        //防止横向越权(一个用户操作同级的另一个用户的信息)
        int resultCount = userMapper.selectUesrCountByUseridAndPassword(user.getId(), Md5Util.md5EncodeUtf8(passwordOld));
        if (0 == resultCount) {
            return ServerResponse.createByErrorMessage("旧密码错误");
        }
        user.setPassword(Md5Util.md5EncodeUtf8(passwordNew));
        int updateCount = 0;
        try {
            updateCount = userMapper.updateByPrimaryKeySelective(user);
        } catch (Exception e) {
            throw new UserException(01, "重置密码失败");
        }
        if (updateCount > 0) {
            return ServerResponse.createBySuccessMessage("密码更新成功");
        }
        return ServerResponse.createByErrorMessage("密码更新失败");
    }

    @Override
    public ServerResponse<User> updateInformation(User user) {
        //username不能被更新
        //email是否已存在,如果已经存在,不能是当前用户的
        int resultCount = userMapper.selectUesrCountByUseridAndEmail(user.getId(), user.getEmail());
        if (resultCount > 0) {
            return ServerResponse.createByErrorMessage("Email已存在");
        }
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        updateUser.setQuestion(user.getQuestion());
        updateUser.setAnswer(user.getAnswer());

        int updateCount = userMapper.updateByPrimaryKeySelective(updateUser);
        if (updateCount > 0) {
            return ServerResponse.createBySuccessMessageData("更新个人信息成功", updateUser);
        }
        return ServerResponse.createByErrorMessage("更新个人信息失败");
    }

    @Override
    public ServerResponse<User> getInformation(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (null == user) {
            return ServerResponse.createByErrorMessage("中啊不到当前用户");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccessData(user);
    }

    //backend

    /**
     * 校验是否是管理员
     *
     * @param user
     * @return
     */
    @Override
    public ServerResponse checkAdminRole(User user) {
        if (user != null && user.getRole().intValue() == Const.Role.ROLE_ADMIN) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }
}
