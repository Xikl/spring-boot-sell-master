package com.ximo.springbootsellmaster.service;

import com.ximo.springbootsellmaster.dto.OrderDTO;

/**
 * 支付service类
 * Created by 朱文赵
 * 2017/9/16
 */
public interface PayService {

    void create(OrderDTO orderDTO);


}
