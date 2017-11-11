package com.ximo.springbootsellmaster.controller.seller;

import com.ximo.springbootsellmaster.domain.ProductInfo;
import com.ximo.springbootsellmaster.enums.ResultEnums;
import com.ximo.springbootsellmaster.exception.SellException;
import com.ximo.springbootsellmaster.service.ProductInfoService;
import com.ximo.springbootsellmaster.util.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping("/on_sell")
    public ModelAndView onSale(@RequestParam("productId") String productId,
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
    @RequestMapping("/off_sell")
    public ModelAndView offSale(@RequestParam("productId") String productId,
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

}
