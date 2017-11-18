package com.ximo.springbootsellmaster.domain.mapper;

import com.ximo.springbootsellmaster.domain.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: 朱文赵
 * @date: 2017/11/17
 */
public interface ProductCategoryMapper {

    @Insert("INSERT  INTO product_category(category_name, category_type) " +
            "VALUES (#{category_name, jdbcType=VARCHAR}, #{category_type, jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

    @Insert("INSERT  INTO product_category(category_name, category_type) " +
            "VALUES (#{categoryName, jdbcType=VARCHAR}, #{categoryType, jdbcType=INTEGER})")
    int insertByBean(ProductCategory productCategory);


    @Select("SELECT * FROM product_category WHERE category_type=#{categoryType}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    })
    ProductCategory findByCategoryType(Integer categoryType);

    @Select("SELECT * FROM product_category WHERE category_name=#{categoryName}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    })
    List<ProductCategory> findByCategoryName(String categoryName);

    @Update("UPDATE product_category SET category_name = #{categoryName} WHERE category_type = #{categoryType}")
    int updateByCategoryType(@Param("categoryName") String categoryName, @Param("categoryType") Integer categoryType);

    @Update("UPDATE product_category SET category_name = #{categoryName} WHERE category_type = #{categoryType}")
    int updateByBean(ProductCategory productCategory);

    @Delete("DELETE FROM product_category WHERE category_type = #{categoryType}")
    int deleteByCategoryType(Integer categoryType);
}
