package com.xyf.service;

import com.xyf.dto.ShoppingCartDTO;
import com.xyf.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    /**
     * 查询购物车
     * @return
     */
    List<ShoppingCart> list();

    /**
     * 添加购物车
     * @param shoppingCartDTO
     */
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);

    /**
     * 清空购物车
     */
    void cleanShoppingCart();

    /**
     * 删除购物车中一个商品
     * @param shoppingCartDTO
     */
    void subShoppingCart(ShoppingCartDTO shoppingCartDTO);
}
