package com.simple.mmall.dao;

import com.simple.mmall.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author simple
 */
public interface UserMapper {
    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入一条记录(所有字段)
     *
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 插入一条记录(不为空字段)
     *
     * @param record
     * @return
     */
    int insertSelective(User record);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    User selectByPrimaryKey(Integer id);

    /**
     * 更新不为空字段
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 更新所有字段
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(User record);

    /**
     * 根据username查询User数量
     *
     * @param username
     * @return
     */
    int selectUserCountByUsername(String username);

    /**
     * 根据email查询User数量
     *
     * @param email
     * @return
     */
    int selectUserCountByEmail(String email);

    /**
     * 根据用户名和密码查询User
     *
     * @param username
     * @param password
     * @return
     */
    User selectUserByUsernamePassword(@Param("username") String username, @Param("password") String password);

    /**
     * 根据用户名查询密保问题
     *
     * @param username
     * @return
     */
    String selectQuestionByUsername(String username);

    /**
     * 根据用户名,密保问题,密保答案查询用户数量
     *
     * @param username
     * @param question
     * @param answer
     * @return
     */
    int selectUesrCountByUsernameAndQAndA(@Param("username") String username, @Param("question") String question, @Param("answer") String answer);

    /**
     * 根据用户名重设密码
     *
     * @param username
     * @param password
     * @return
     */
    int updatePasswordByUsername(@Param("username") String username, @Param("password") String password);

    /**
     * 根据userid,密码查询用户数量
     *
     * @param userId
     * @param password
     * @return
     */
    int selectUesrCountByUseridAndPassword(@Param("userId") Integer userId, @Param("password") String password);

    /**
     * 查询userid不等,email相等的用户数量
     *
     * @param userId
     * @param email
     * @return
     */
    int selectUesrCountByUseridAndEmail(@Param("userId") Integer userId, @Param("email") String email);
}