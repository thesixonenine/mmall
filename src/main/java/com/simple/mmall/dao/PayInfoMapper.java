package com.simple.mmall.dao;

import com.simple.mmall.entity.PayInfo;

/**
 * @author simple
 */
public interface PayInfoMapper {
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
    int insert(PayInfo record);

    /**
     * 插入一条记录(不为空字段)
     *
     * @param record
     * @return
     */
    int insertSelective(PayInfo record);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    PayInfo selectByPrimaryKey(Integer id);

    /**
     * 更新不为空字段
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(PayInfo record);

    /**
     * 更新所有字段
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(PayInfo record);
}