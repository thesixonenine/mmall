package com.simple.mmall.service;

import com.simple.mmall.common.ServerResponse;
import com.simple.mmall.entity.Category;

import java.util.List;

/**
 * @author Simple
 * @create 2017-10-08 15:04
 **/
public interface ICategoryService {

    /**
     * 新增节点
     *
     * @param categoryName
     * @param parentId
     * @return
     */
    ServerResponse addCategory(String categoryName, Integer parentId);

    /**
     * 更新节点名称
     *
     * @param categoryId
     * @param categoryName
     * @return
     */
    ServerResponse updateCategoryName(Integer categoryId, String categoryName);

    /**
     * 根据父节点id找到子节点
     *
     * @param categoryId
     * @return
     */
    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);

    /**
     * 根据父节点找到子节点以及下面的所有节点
     *
     * @param categoryId
     * @return
     */
    ServerResponse<List<Integer>> getCategoryAndChildrenById(Integer categoryId);
}
