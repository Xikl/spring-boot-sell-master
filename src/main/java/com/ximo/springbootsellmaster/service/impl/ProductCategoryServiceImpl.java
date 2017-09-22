package com.ximo.springbootsellmaster.service.impl;

import com.ximo.springbootsellmaster.domain.ProductCategory;
import com.ximo.springbootsellmaster.repository.ProductCategoryRepository;
import com.ximo.springbootsellmaster.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类目service实现类
 * Created by 朱文赵
 * 2017/9/9
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory findOne(Integer id) {
        return productCategoryRepository.findOne(id);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType) {
        return productCategoryRepository.findByCategoryTypeIn(categoryType);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }
}
