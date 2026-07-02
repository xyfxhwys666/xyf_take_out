package com.xyf.mapper;

import com.github.pagehelper.Page;
import com.xyf.annotation.AutoFill;
import com.xyf.dto.DishPageQueryDTO;
import com.xyf.entity.Dish;
import com.xyf.enumeration.OperationType;
import com.xyf.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

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
     * 根据菜品id集合批量删除菜品数据
     *
     * @param ids
     */
    void deleteByIds(List<Long> ids);

    /**
     * 根据id修改菜品数据
     *
     * @param dish
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);

    /**
     * 根据分类id查询菜品
     *
     * @param categoryId
     * @return
     */
    List<Dish> list(Long categoryId);

    /**
     * 根据条件查询菜品数据
     * @param map
     * @return
     */
    Integer countByMap(Map map);

    /**
     * 根据id删除菜品数据
     * @param id
     */
/*    @Delete("delete from dish where id =#{id}")
    void deleteById(Long id);*/
}
