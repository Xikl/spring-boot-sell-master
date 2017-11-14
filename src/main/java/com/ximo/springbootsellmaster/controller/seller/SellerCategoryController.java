package com.ximo.springbootsellmaster.controller.seller;

import com.ximo.springbootsellmaster.domain.ProductCategory;
import com.ximo.springbootsellmaster.enums.ResultEnums;
import com.ximo.springbootsellmaster.exception.SellException;
import com.ximo.springbootsellmaster.form.CategoryForm;
import com.ximo.springbootsellmaster.service.ProductCategoryService;
import com.ximo.springbootsellmaster.util.ModelUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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

    /**
     * 修改和添加方法
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map){
        /*不为空则表示 为修改*/
        if(categoryId != 0){
            ProductCategory productCategory = productCategoryService.findOne(categoryId);
            map.put("productCategory", productCategory);
        }
        return new ModelAndView("category/index", map);
    }

    /**
     * 保存、新增类目的方法
     * @param categoryForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm,
                             BindingResult bindingResult,
                             Map<String, Object> map){
        if (bindingResult.hasErrors()){
            return ModelUtil.error(map, bindingResult.getFieldError().getDefaultMessage(), ModelUtil.CATEGORY_INDEX_URL);
        }

        ProductCategory productCategory;
        try {
            Integer categoryId = categoryForm.getCategoryId();
            /*id 不为空 则为新增*/
            if(categoryId != null) {
                productCategory = productCategoryService.findOne(categoryId);
            }else{
                /*id为空 则为添加 id 为自增 所以不用 赋值*/
                productCategory = new ProductCategory();
            }
            /*属性值拷贝*/
            BeanUtils.copyProperties(categoryForm, productCategory);
            /*保存*/
            productCategoryService.save(productCategory);
        } catch (SellException e) {
            return ModelUtil.error(map, e.getMessage(), ModelUtil.CATEGORY_INDEX_URL);
        }
        /*成功*/
        return ModelUtil.success(map, ResultEnums.CATEGORY_SAVE_SUCCESS.getMsg(), ModelUtil.CATEGORY_DEFAULT_URL);
    }




}

