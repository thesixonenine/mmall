package com.simple.mmall.dao;


import com.simple.mmall.entity.Category;

import java.util.List;

/**
 * @author simple
 */
public interface CategoryMapper {
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
    int insert(Category record);

    /**
     * 插入一条记录(不为空字段)
     *
     * @param record
     * @return
     */
    int insertSelective(Category record);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Category selectByPrimaryKey(Integer id);

    /**
     * 更新不为空字段
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Category record);

    /**
     * 更新所有字段
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Category record);

    /**
     * 根据父节点id查询子节点
     *
     * @param parentId
     * @return
     */
    List<Category> selectCategoryChildrenByParentId(Integer parentId);
}