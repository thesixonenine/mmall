package com.simple.mmall.dao;

import com.simple.mmall.entity.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author simple
 */
public interface CartMapper {

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
    int insert(Cart record);

    /**
     * 插入非空数据
     *
     * @param record
     * @return
     */
    int insertSelective(Cart record);

    /**
     * 根据主键id查询
     *
     * @param id
     * @return
     */
    Cart selectByPrimaryKey(Integer id);

    /**
     * 根据主键id更新非空字段
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Cart record);

    /**
     * 根据主键id更新所有字段
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Cart record);

    /**
     * 根据userId和productId查询购物车
     *
     * @param userId
     * @param productId
     * @return
     */
    Cart selectCartByUserIdProductId(@Param("userId") Integer userId, @Param("productId") Integer productId);

    /**
     * 根据userId查询购物车
     *
     * @param userId
     * @return
     */
    List<Cart> selectCartByUserId(Integer userId);

    /**
     * 根据userId查新购物车中的产品选择状态
     *
     * @param userId
     * @return
     */
    int selectCartProductCheckedStatusByUserId(Integer userId);

    /**
     * 根据userId和产品ids删除产品
     *
     * @param userId
     * @param productIdList
     * @return
     */
    int deleteByUserIdProductIds(@Param("userId") Integer userId, @Param("productIdList") List<String> productIdList);

    /**
     * 选/不选产品
     *
     * @param userId
     * @param productId
     * @param checked
     * @return
     */
    int checkedOrUncheckedProduct(@Param("userId") Integer userId, @Param("productId") Integer productId, @Param("checked") Integer checked);

    /**
     * 根据userId查询总产品数
     *
     * @param userId
     * @return
     */
    int selectCartProductCount(@Param("userId") Integer userId);

    /**
     * 从购物车中获取已经被勾选的产品
     *
     * @param userId
     * @return
     */
    List<Cart> selectCheckedCartByUserId(@Param("userId") Integer userId);
}