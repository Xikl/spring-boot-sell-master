package com.ximo.springbootsellmaster.service.impl;

import com.ximo.springbootsellmaster.dto.OrderDTO;
import com.ximo.springbootsellmaster.service.OrderService;
import com.ximo.springbootsellmaster.service.PushMessageService;
import me.chanjar.weixin.mp.api.WxMpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @description:
 * @author: 朱文赵
 * @date: 2017/11/15
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PushMessageServiceImplTest {

   @Autowired
   private PushMessageService pushMessageService;

   @Autowired
   private OrderService orderService;

    private static String orderId = "1506231402035918244";

    @Test
    public void orderStatus() throws Exception {
        OrderDTO orderDTO = orderService.findOne(orderId);
        pushMessageService.orderStatus(orderDTO);
    }

}