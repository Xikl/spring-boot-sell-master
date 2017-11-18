package com.ximo.springbootsellmaster.domain.mapper;

import com.ximo.springbootsellmaster.domain.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @description:
 * @author: 朱文赵
 * @date: 2017/11/17
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("category_name", "火锅");
        map.put("category_type", "4");
        int result = mapper.insertByMap(map);
        System.out.println(result);
        Assert.assertEquals(1, result);

    }

    @Test
    public void insertByBean() throws Exception{
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("酸奶");
        productCategory.setCategoryType(3);
        int result = mapper.insertByBean(productCategory);
        System.out.println(result);
        Assert.assertEquals(1, result);
    }

    @Test
    public void findByCategoryType() throws Exception{
        ProductCategory result = mapper.findByCategoryType(4);
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByCategoryName() throws Exception{
        List<ProductCategory> result = mapper.findByCategoryName("酸奶");
        System.out.println(result);
        Assert.assertNotEquals(0, result);
    }

    @Test
    public void updateByCategoryType() throws Exception{
        int result = mapper.updateByCategoryType("老北京火锅", 4);
        System.out.println(result);
        Assert.assertEquals(1, result);
    }

    @Test
    public void updateByBean() throws Exception{
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("一点点酸奶");
        productCategory.setCategoryType(4);
        int result = mapper.updateByBean(productCategory);
        System.out.println(result);
        Assert.assertEquals(1, result);
    }

    @Test
    public void deleteByCategoryType() throws Exception{
        int result = mapper.deleteByCategoryType(5);
        System.out.println(result);
        Assert.assertEquals(1, result);
    }

    @Test
    public void selectByCategoryType() throws Exception{
        ProductCategory result = mapper.selectByCategoryType(4);
        System.out.println(result);
        Assert.assertNotNull(result);
    }
}