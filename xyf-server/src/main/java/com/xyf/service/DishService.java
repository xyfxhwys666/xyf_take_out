package com.xyf.service;

import com.xyf.dto.DishDTO;
import com.xyf.dto.DishPageQueryDTO;
import com.xyf.result.PageResult;
import com.xyf.vo.DishVO;

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

    /**
     * 根据id查询菜品和对应的口味数据
     * @param id
     * @return
     */
    DishVO getByIdWithFlavor(Long id);
/**
     * 修改菜品和口味
     * @param dishDTO
     */
    void updateWithFlavor(DishDTO dishDTO);

    /**
     * 根据分类id查询菜品
     * @param categoryId
     * @return
     */
    List<DishVO> list(Long categoryId);
}
