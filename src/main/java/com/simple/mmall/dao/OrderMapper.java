package com.simple.mmall.dao;

import com.simple.mmall.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author simple
 */
public interface OrderMapper {
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
    int insert(Order record);

    /**
     * 插入一条记录(不为空字段)
     *
     * @param record
     * @return
     */
    int insertSelective(Order record);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Order selectByPrimaryKey(Integer id);

    /**
     * 更新不为空字段
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Order record);

    /**
     * 更新所有字段
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Order record);

    /**
     * 根据userId和订单号来查询订单
     *
     * @param userId
     * @param orderNo
     * @return
     */
    Order selectByUserIdAndOrderNo(@Param("userId") Integer userId, @Param("orderNo") Long orderNo);

    /**
     * 根据订单号查询订单
     *
     * @param orderNo
     * @return
     */
    Order selectByOrderNo(Long orderNo);

    /**
     * 根据userId获取订单
     *
     * @param userId
     * @return
     */
    List<Order> selectByUserId(Integer userId);

    /**
     * 查询所有订单
     *
     * @return
     */
    List<Order> selectAllOrder();
}