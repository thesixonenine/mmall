package com.simple.mmall.service;

import com.simple.mmall.common.ServerResponse;
import com.simple.mmall.entity.User;

/**
 * 用户Service接口
 *
 * @author Simple
 * @create 2017-10-04 16:57
 **/
public interface IUserService {

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    ServerResponse<User> login(String username, String password);

    /**
     * 注册
     *
     * @param user
     * @return
     */
    ServerResponse<String> register(User user);

    /**
     * 参数验证
     *
     * @param str
     * @param type
     * @return
     */
    ServerResponse<String> checkValid(String str, String type);

    /**
     * 根据用户名获取密保问题
     *
     * @param username
     * @return
     */
    ServerResponse<String> forgetGetQuestion(String username);

    /**
     * 根据用户名,密保问题,密保答案进行验证
     *
     * @param username
     * @param question
     * @param answer
     * @return
     */
    ServerResponse<String> forgetCheckAnswer(String username, String question, String answer);

    /**
     * 验证密保问题后重置密码
     *
     * @param username
     * @param newPassword
     * @param forgetToken
     * @return
     */
    ServerResponse<String> forgetResetPassword(String username, String newPassword, String forgetToken);

    /**
     * 登录后重置密码
     *
     * @param passwordOld
     * @param passwordNew
     * @param user
     * @return
     */
    ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user);

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    ServerResponse<User> updateInformation(User user);

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    ServerResponse<User> getInformation(Integer userId);

    /**
     * backend 后台
     *
     * @param user
     * @return
     */
    ServerResponse checkAdminRole(User user);
}
