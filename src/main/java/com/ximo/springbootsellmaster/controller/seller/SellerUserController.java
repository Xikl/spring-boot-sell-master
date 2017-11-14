package com.ximo.springbootsellmaster.controller.seller;

import com.ximo.springbootsellmaster.domain.SellerInfo;
import com.ximo.springbootsellmaster.enums.ResultEnums;
import com.ximo.springbootsellmaster.service.SellerService;
import com.ximo.springbootsellmaster.util.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @description: 卖家用户controller
 * @author: 朱文赵
 * @date: 2017/11/14
 */
@Controller
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              Map<String, Object> map){
        //1.openid 和 数据库进行匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if (sellerInfo == null){
            /*跳转到错误页面*/
            return ModelUtil.error(map, ResultEnums.LOGIN_FAIL.getMsg(), ModelUtil.ORDER_DEFAULT_URL);
        }
        //2.设置token到redis

        //3.设置token到cookie
        return null;
    }

    @GetMapping("/logout")
    public void logout(){

    }

}
