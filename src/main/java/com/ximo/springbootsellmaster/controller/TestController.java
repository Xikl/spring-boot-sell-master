package com.ximo.springbootsellmaster.controller;

import com.ximo.springbootsellmaster.domain.ProductCategory;
import com.ximo.springbootsellmaster.domain.mapper.ProductCategoryMapper;
import com.ximo.springbootsellmaster.util.ResultVOUtil;
import com.ximo.springbootsellmaster.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 测试 是不是 能够正常使用 mybatis
 * @author: 朱文赵
 * @date: 2017/11/18
 */
@RestController
public class TestController {

    @Autowired
    private ProductCategoryMapper categoryMapper;

    @GetMapping("/test")
    public ResultVO test(){
        ProductCategory productCategory = categoryMapper.selectByCategoryType(3);
        return ResultVOUtil.success(productCategory);
    }


}
