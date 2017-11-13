package com.ximo.springbootsellmaster.controller.seller;

import com.ximo.springbootsellmaster.domain.ProductCategory;
import com.ximo.springbootsellmaster.service.ProductCategoryService;
import com.ximo.springbootsellmaster.util.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @description: 卖家类目controller
 * @author: 朱文赵
 * @date: 2017/11/12
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map){
        List<ProductCategory> categoryList = productCategoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("category/list", map);
    }



}

