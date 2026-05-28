package com.xyf.service;

import com.xyf.dto.DishDTO;
import com.xyf.dto.DishPageQueryDTO;
import com.xyf.result.PageResult;

import java.util.List;

public interface DishService {
    /**
     * 新增菜品和对应的口味
     * @param dishDTO
     */

    public void saveWithFlavor(DishDTO dishDTO);

    /**
     * 菜品分页查询
      * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 批量删除菜品
     * @param ids
     */
    void deleteBatch(List<Long> ids);
}
