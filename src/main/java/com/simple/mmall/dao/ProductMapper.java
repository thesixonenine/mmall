package com.simple.mmall.dao;

import com.simple.mmall.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author simple
 */
public interface ProductMapper {
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
    int insert(Product record);

    /**
     * 插入一条记录(不为空字段)
     *
     * @param record
     * @return
     */
    int insertSelective(Product record);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Product selectByPrimaryKey(Integer id);

    /**
     * 更新不为空字段
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Product record);

    /**
     * 更新所有字段
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Product record);

    /**
     * 查询产品列表
     *
     * @return
     */
    List<Product> selectList();

    /**
     * 根据名称或id查询产品
     *
     * @param productName
     * @param productId
     * @return
     */
    List<Product> selectByNameAndProductId(@Param("productName") String productName, @Param("productId") Integer productId);

    /**
     * 根据关键字和节点查询产品(在线的)
     *
     * @param productName
     * @param categoryIdList
     * @return
     */
    List<Product> selectByNameAndCategoryIds(@Param("productName") String productName, @Param("categoryIdList") List<Integer> categoryIdList);
}