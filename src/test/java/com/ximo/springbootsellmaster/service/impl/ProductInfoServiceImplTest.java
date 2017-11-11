package com.ximo.springbootsellmaster.service.impl;

import com.ximo.springbootsellmaster.domain.ProductInfo;
import com.ximo.springbootsellmaster.enums.ProductStatusEnums;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: 朱文赵
 * @date: 2017/11/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productService;

    @Test
    public void onSale() throws Exception {
        ProductInfo result = productService.onSale("1");
        Assert.assertEquals(ProductStatusEnums.UP.getCode(), result.getProductStatus());
    }

    @Test
    public void offSale() throws Exception {
        ProductInfo result = productService.offSale("1");
        Assert.assertEquals(ProductStatusEnums.DOWN.getCode(), result.getProductStatus());
    }

}