package com.ximo.springbootsellmaster.service;

import com.ximo.springbootsellmaster.dto.OrderDTO;

/**
 * Created by 朱文赵
 * 2017/9/12
 */
public interface BuyerService {

    /** 查询一个订单 */
    OrderDTO findOrderOne(String openid, String orderId);

    /** 取消订单 */
    OrderDTO cancelOrder(String openid, String orderId);

}
