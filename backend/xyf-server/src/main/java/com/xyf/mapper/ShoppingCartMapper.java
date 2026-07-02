package com.xyf.mapper;

import com.xyf.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    /**
     * 查询购物车列表
     * @param userId
     * @return
     */
    @Select("select * from shopping_cart where user_id = #{userId}")
    List<ShoppingCart> list(Long userId);

    /**
     * 根据用户id和菜品id查询购物车
     * @param userId
     * @param dishId
     * @return
     */
    @Select("select * from shopping_cart where user_id = #{userId} and dish_id = #{dishId}")
    ShoppingCart getByUserIdAndDishId(Long userId, Long dishId);


    /**
     * 根据用户id和套餐id查询购物车
     *
     * @param userId
     * @param setmealId
     * @return
     */

    @Select("select * from shopping_cart where user_id = #{userId} and setmeal_id = #{setmealId}")
    ShoppingCart getByUserIdAndSetmealId(Long userId, Long setmealId);



    /**
     * 更新购物车数量
     * @param shoppingCart
     */
    @Update("update shopping_cart set number = #{number} where id = #{id}")
    void updateNumberById(ShoppingCart shoppingCart);

    /**
     * 插入购物车数据
     * @param shoppingCart
     */
    @Insert("insert into shopping_cart (name, image, user_id, dish_id, setmeal_id, dish_flavor, amount, number, create_time) " +
            "values (#{name}, #{image}, #{userId}, #{dishId}, #{setmealId}, #{dishFlavor}, #{amount}, #{number}, #{createTime})")
    void insert(ShoppingCart shoppingCart);

    /**
     * 根据用户id删除购物车
     * @param userId
     */
    @Delete("delete from shopping_cart where user_id = #{userId}")
    void deleteByUserId(Long userId);

    /**
     * 根据id删除购物车
     * @param id
     */
    @Delete("delete from shopping_cart where id = #{id}")
    void deleteById(Long id);
}
