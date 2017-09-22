package com.ximo.springbootsellmaster.service.impl;

import com.ximo.springbootsellmaster.dto.OrderDTO;
import com.ximo.springbootsellmaster.enums.ResultEnums;
import com.ximo.springbootsellmaster.exception.SellException;
import com.ximo.springbootsellmaster.service.BuyerService;
import com.ximo.springbootsellmaster.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 朱文赵
 * 2017/9/12
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService{

    @Autowired
    private OrderService orderService;

    /**
     * 查询一个订单
     *
     * @param openid
     * @param orderId
     */
    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    /**
     * 取消订单
     *
     * @param openid
     * @param orderId
     */
    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = this.checkOrderOwner(openid, orderId);
        if (orderDTO == null){
            log.error("【取消订单】 查不到该订单, orderId={}", orderId);
            throw new SellException(ResultEnums.ORDER_NOT_EXIST);
        }
        //取消该订单
        return orderService.cancel(orderDTO);
    }

    /**
     * 私有方法
     * 判断是否为该用户的订单
     * @param openid
     * @param orderId
     * @return
     */
    private OrderDTO checkOrderOwner(String openid, String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO == null){
            return null;
        }
        //判断是否为自己的订单
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("【查询订单】订单的openid不一致， openid={}, orderDTO={}",
                    openid, orderDTO);
            throw new SellException(ResultEnums.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
