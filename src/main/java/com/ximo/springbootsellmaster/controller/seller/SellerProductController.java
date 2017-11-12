package com.ximo.springbootsellmaster.controller.seller;

import com.ximo.springbootsellmaster.domain.ProductCategory;
import com.ximo.springbootsellmaster.domain.ProductInfo;
import com.ximo.springbootsellmaster.enums.ResultEnums;
import com.ximo.springbootsellmaster.exception.SellException;
import com.ximo.springbootsellmaster.form.ProductForm;
import com.ximo.springbootsellmaster.service.ProductCategoryService;
import com.ximo.springbootsellmaster.service.ProductInfoService;
import com.ximo.springbootsellmaster.util.KeyUtil;
import com.ximo.springbootsellmaster.util.ModelUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @description: 卖家商品列表
 * @author: 朱文赵
 * @date: 2017/11/1
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;
    /**
     * 商品列表页
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map){
        PageRequest pageRequest = new PageRequest(page - 1, size);
        //查询所有的商品
        Page<ProductInfo> productInfoPage = productInfoService.findAll(pageRequest);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/list", map);
    }

    @RequestMapping("/on_sell/{productId}")
    public ModelAndView onSale(@PathVariable("productId") String productId,
                               Map<String, Object> map){
        try {
            productInfoService.onSale(productId);
        } catch (SellException e) {
            /*失败的时候*/
            return ModelUtil.error(map, e.getMessage());
        }
        /*成功的时候*/
        return ModelUtil.success(map, ResultEnums.PRODUCT_ON_SALE_SUCCESS.getMsg());
    }

    /**
     * 下架
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("/off_sell/{productId}")
    public ModelAndView offSale(@PathVariable("productId") String productId,
                               Map<String, Object> map){
        try {
            productInfoService.offSale(productId);
        } catch (SellException e) {
            /*失败的时候*/
           return ModelUtil.error(map, e.getMessage());
        }
        /*成功的时候*/
        return ModelUtil.success(map, ResultEnums.PRODUCT_OFF_SALE_SUCCESS.getMsg());
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                      Map<String, Object> map){
        if(!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productInfoService.findOne(productId);
            map.put("productInfo", productInfo);
        }

        /*查询所有的类目*/
        List<ProductCategory> categoryList = productCategoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("product/index", map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm productForm,
                             BindingResult bindingResult,
                             Map<String, Object> map){
        if(bindingResult.hasErrors()){
            return ModelUtil.errorIndex(map, bindingResult.getFieldError().getDefaultMessage());
        }

        ProductInfo productInfo;
        try {
            String productId = productForm.getProductId();
            /*id不为空的时候 ，表示为修改*/
            if (!StringUtils.isEmpty(productId)){
                productInfo = productInfoService.findOne(productId);
            }else{
                /*id为空， 表示为新增*/
                productInfo = new ProductInfo();
                productForm.setProductId(KeyUtil.generateUniqueKey());
            }
            /*值的拷贝*/
            BeanUtils.copyProperties(productForm, productInfo);
            /*保存*/
            productInfoService.save(productInfo);
        } catch (SellException e) {
            return ModelUtil.errorIndex(map, e.getMessage());
        }
        return ModelUtil.successIndex(map, ResultEnums.PRODUCT_SAVE_SUCCESS.getMsg());
    }




}
