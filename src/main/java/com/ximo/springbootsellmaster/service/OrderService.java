package com.ximo.springbootsellmaster.service;

import com.ximo.springbootsellmaster.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by 朱文赵
 * 2017/9/11
 */
public interface OrderService {

    /** 创建订单 */
    OrderDTO create(OrderDTO orderDTO);

    /** 查询单个订单 */
    OrderDTO findOne(String orderId);

    /** 查询单个人订单列表 */
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /** 查询所有的订单列表*/
    Page<OrderDTO> findList(Pageable pageable);

    /** 取消订单 */
    OrderDTO cancel(OrderDTO orderDTO);

    /** 完结订单 */
    OrderDTO finish(OrderDTO orderDTO);

    /** 支付订单 */
    OrderDTO paid(OrderDTO orderDTO);







}
