package com.simple.mmall.dao;

import com.simple.mmall.entity.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author simple
 */
public interface OrderItemMapper {
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
    int insert(OrderItem record);

    /**
     * 插入一条记录(不为空字段)
     *
     * @param record
     * @return
     */
    int insertSelective(OrderItem record);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    OrderItem selectByPrimaryKey(Integer id);

    /**
     * 更新不为空字段
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(OrderItem record);

    /**
     * 更新所有字段
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(OrderItem record);

    /**
     * 通过订单id和userId获取订单列表
     *
     * @param orderNo
     * @param userId
     * @return
     */
    List<OrderItem> getByOrderNoUserId(@Param("orderNo") Long orderNo, @Param("userId") Integer userId);

    /**
     * 根据orderNo获取订单
     *
     * @param orderNo
     * @return
     */
    List<OrderItem> getByOrderNo(@Param("orderNo") Long orderNo);

    /**
     * 批量插入orderItem
     *
     * @param orderItemList
     */
    void batchInsert(@Param("orderItemList") List<OrderItem> orderItemList);
}