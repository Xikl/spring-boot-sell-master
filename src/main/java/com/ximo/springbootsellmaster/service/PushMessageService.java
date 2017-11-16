package com.ximo.springbootsellmaster.service;

import com.ximo.springbootsellmaster.dto.OrderDTO;

/**
 * @description: 微信模板消息推送
 * @author: 朱文赵
 * @date: 2017/11/15
 */
public interface PushMessageService {

    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);

}
