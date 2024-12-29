package com.zy.mapper;

import com.zy.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    /**
     * 查询父分类列表
     */
    @Select("select * from tb_category where category_parentid = 0")
    List<Category> findCategoryParentList();

    /**
     * 查询子分类列表
     */
    @Select("select * from tb_category where category_parentid != 0")
    List<Category> findCategoryChildList();

    /**
     * 查询所有分类
     */
    @Select("select * from tb_category")
    List<Category> findAllCategory();

    /**
     * 根据分类ID查询
     */
    @Select("select * from tb_category where category_id = #{category_id}")
    Category findCategoryByCid(int category_id);

    /**
     * 添加分类
     */
    @Insert("insert into tb_category(category_name, category_parentid) values(#{category_name}, #{category_parentid})")
    void addCategory(Category category);

    /**
     * 删除分类
     */
    @Delete("delete from tb_category where category_id = #{category_id}")
    void delCategoryById(int category_id);

    /**
     * 更新分类名称
     */
    @Update("update tb_category set category_name = #{categoryName} where category_id = #{categoryId}")
    void updateCategoryName(@Param("categoryName") String categoryName, @Param("categoryId") int categoryId);
}