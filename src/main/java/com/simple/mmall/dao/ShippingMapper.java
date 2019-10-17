package com.simple.mmall.dao;

import com.simple.mmall.entity.Shipping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author simple
 */
public interface ShippingMapper {

    /**
     * 根据主键id删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入
     *
     * @param record
     * @return
     */
    int insert(Shipping record);

    /**
     * 插入非空数据
     *
     * @param record
     * @return
     */
    int insertSelective(Shipping record);

    /**
     * 根据主键id查询
     *
     * @param id
     * @return
     */
    Shipping selectByPrimaryKey(Integer id);

    /**
     * 根据主键id更新非空字段
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Shipping record);

    /**
     * 根据主键id更新所有字段
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Shipping record);

    /**
     * 根据地址id和userId删除地址
     *
     * @param userId
     * @param shippingId
     * @return
     */
    int deleteByShippingIdUserId(@Param("userId") Integer userId, @Param("shippingId") Integer shippingId);

    /**
     * 更新地址
     *
     * @param record
     * @return
     */
    int updateByShipping(Shipping record);

    /**
     * 根据地址id和userID查询地址
     *
     * @param userId
     * @param shippingId
     * @return
     */
    Shipping selectByShippingIdUserId(@Param("userId") Integer userId, @Param("shippingId") Integer shippingId);

    /**
     * 根据userId查询所有地址
     *
     * @param userId
     * @return
     */
    List<Shipping> selectByUserId(@Param("userId") Integer userId);
}