package com.xyf.mapper;

import com.github.pagehelper.Page;
import com.xyf.annotation.AutoFill;
import com.xyf.dto.DishPageQueryDTO;
import com.xyf.entity.Dish;
import com.xyf.enumeration.OperationType;
import com.xyf.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * 新增菜品数据
     * @param dish
     */
    @AutoFill(value= OperationType.INSERT)
    void insert(Dish dish);

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);

    /**
     * 根据id删除菜品数据
     * @param id
     */
    @Delete("delete from dish where id =#{id}")
    void deleteById(Long id);
}
