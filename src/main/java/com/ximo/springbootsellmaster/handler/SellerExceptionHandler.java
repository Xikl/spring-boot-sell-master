package com.ximo.springbootsellmaster.handler;

import com.ximo.springbootsellmaster.config.ProjectUrlConfig;
import com.ximo.springbootsellmaster.exception.SellerAuthorizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description:
 * @author: 朱文赵
 * @date: 2017/11/15
 */
@ControllerAdvice
public class SellerExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    /**
     * 此处为添加 微信授权登录 操作 ，若用户没有登录 则跳转到微信扫码界面
     * 以后呢 如果没有微信扫码界面的 时候 可以单独写一个 登录 界面
     * 然后进行跳转 url不是固定的
     * @return
     */
    @ExceptionHandler(SellerAuthorizeException.class)
    public ModelAndView handlerSellerAuthorizeException(){
        return myTest();
    }

    /**
     * 实际的一个生产环境中的用的url跳转
     * @return
     */
    private ModelAndView pro(){
        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getWeChatOpenAuthorize())
                .concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat(projectUrlConfig.getSell())
                .concat("/sell/seller/login"));
    }

    /**
     * http://localhost:8080/sell/seller/order/list
     * 自己的一个测试方法
     * @return
     */
    private ModelAndView myTest(){
        return new ModelAndView("redirect:"
                .concat("http://localhost:8080/sell/seller/login?openid=oc8RZ0Wc_rt6kUd14IkKsw6UsbsA"));
    }

}
