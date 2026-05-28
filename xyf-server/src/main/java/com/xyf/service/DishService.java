package com.xyf.service;

import com.xyf.dto.DishDTO;

public interface DishService {
    /**
     * 新增菜品和对应的口味
     * @param dishDTO
     */

    public void saveWithFlavor(DishDTO dishDTO);
}
