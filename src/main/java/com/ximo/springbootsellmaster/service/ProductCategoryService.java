package com.ximo.springbootsellmaster.service;

import com.ximo.springbootsellmaster.domain.ProductCategory;

import java.util.List;

/**
 * 类目service
 * Created by 朱文赵
 * 2017/9/9
 */
public interface ProductCategoryService {

    ProductCategory findOne(Integer id);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType);

    ProductCategory save(ProductCategory productCategory);

}
