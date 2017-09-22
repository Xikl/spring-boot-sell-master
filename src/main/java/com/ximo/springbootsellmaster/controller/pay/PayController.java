package com.ximo.springbootsellmaster.controller.pay;

import com.lly835.bestpay.service.BestPayService;
import com.ximo.springbootsellmaster.dto.OrderDTO;
import com.ximo.springbootsellmaster.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 支付界面
 * Created by 朱文赵
 * 2017/9/16
 */
@Controller
@RequestMapping("/pay")
@Slf4j
public class PayController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public void create(@RequestParam("orderId") String orderId,
                       @RequestParam("returnUrl") String returnUrl){
//        //查询订单,不用判空，service中已经进行了判断
//        OrderDTO orderDTO = orderService.findOne(orderId);
//        //发起支付
//        BestPayService
        log.info("【支付开始】");
    }


}
