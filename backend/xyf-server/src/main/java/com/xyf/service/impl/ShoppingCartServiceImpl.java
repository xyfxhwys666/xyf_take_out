package com.xyf.service.impl;

import com.xyf.context.BaseContext;
import com.xyf.dto.ShoppingCartDTO;
import com.xyf.entity.Dish;
import com.xyf.entity.Setmeal;
import com.xyf.entity.ShoppingCart;
import com.xyf.mapper.DishMapper;
import com.xyf.mapper.SetmealMapper;
import com.xyf.mapper.ShoppingCartMapper;
import com.xyf.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetmealMapper setmealMapper;

    /**
     * 查询购物车
     * @return
     */
    @Override
    public List<ShoppingCart> list() {
        Long userId = BaseContext.getCurrentId();
        return shoppingCartMapper.list(userId);
    }

    /**
     * 添加购物车
     * @param shoppingCartDTO
     */
         @Override
    public void addShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);

        Long userId = BaseContext.getCurrentId();
        shoppingCart.setUserId(userId);
        shoppingCart.setCreateTime(LocalDateTime.now());

        Long dishId = shoppingCartDTO.getDishId();
        if (dishId != null) {
            Dish dish = dishMapper.getById(dishId);
            shoppingCart.setName(dish.getName());
            shoppingCart.setImage(dish.getImage());
            shoppingCart.setAmount(dish.getPrice());
        } else {
            Long setmealId = shoppingCartDTO.getSetmealId();
            Setmeal setmeal = setmealMapper.getById(setmealId);
            shoppingCart.setName(setmeal.getName());
            shoppingCart.setImage(setmeal.getImage());
            shoppingCart.setAmount(setmeal.getPrice());
        }

        ShoppingCart cartExist = null;
        if (dishId != null) {
            cartExist = shoppingCartMapper.getByUserIdAndDishId(userId, dishId);
        } else {
            cartExist = shoppingCartMapper.getByUserIdAndSetmealId(userId, shoppingCartDTO.getSetmealId());
        }

        if (cartExist != null) {
            cartExist.setNumber(cartExist.getNumber() + 1);
            shoppingCartMapper.updateNumberById(cartExist);
        } else {
            shoppingCart.setNumber(1);
            shoppingCartMapper.insert(shoppingCart);
        }
    }
// ... existing code ...



    /**
     * 清空购物车
     */
    @Override
    public void cleanShoppingCart() {
        Long userId = BaseContext.getCurrentId();
        shoppingCartMapper.deleteByUserId(userId);
    }

    /**
     * 删除购物车中一个商品
     * @param shoppingCartDTO
     */
        @Override
    public void subShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);

        Long userId = BaseContext.getCurrentId();
        shoppingCart.setUserId(userId);

        ShoppingCart cart = null;
        if (shoppingCartDTO.getDishId() != null) {
            cart = shoppingCartMapper.getByUserIdAndDishId(userId, shoppingCartDTO.getDishId());
        } else {
            cart = shoppingCartMapper.getByUserIdAndSetmealId(userId, shoppingCartDTO.getSetmealId());
        }

        if (cart != null && cart.getNumber() > 1) {
            cart.setNumber(cart.getNumber() - 1);
            shoppingCartMapper.updateNumberById(cart);
        } else {
            shoppingCartMapper.deleteById(cart.getId());
        }
    }
}

