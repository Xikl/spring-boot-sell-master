package com.ximo.springbootsellmaster.service.impl;

import com.ximo.springbootsellmaster.converter.OrderMaster2OrderDTOConverter;
import com.ximo.springbootsellmaster.domain.OrderDetail;
import com.ximo.springbootsellmaster.domain.OrderMaster;
import com.ximo.springbootsellmaster.domain.ProductInfo;
import com.ximo.springbootsellmaster.dto.CartDTO;
import com.ximo.springbootsellmaster.dto.OrderDTO;
import com.ximo.springbootsellmaster.enums.OrderStatusEnums;
import com.ximo.springbootsellmaster.enums.PayStatusEnums;
import com.ximo.springbootsellmaster.enums.ResultEnums;
import com.ximo.springbootsellmaster.exception.SellException;
import com.ximo.springbootsellmaster.repository.OrderDetailRepository;
import com.ximo.springbootsellmaster.repository.OrderMasterRepository;
import com.ximo.springbootsellmaster.service.OrderService;
import com.ximo.springbootsellmaster.service.ProductInfoService;
import com.ximo.springbootsellmaster.service.PushMessageService;
import com.ximo.springbootsellmaster.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 朱文赵
 * 2017/9/11
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private PushMessageService pushMessageService;

    @Autowired
    private WebSocket webSocket;

    /**
     * 创建订单
     *
     * @param orderDTO 订单主表数据传输对象
     */
    @Override
    @Transactional//采用事务提交
    public OrderDTO create(OrderDTO orderDTO) {
        //商品总价初始化
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        //生成orderId
        String orderId = KeyUtil.generateUniqueKey();

        //查询商品（数量， 价格）
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        for (OrderDetail orderDetail : orderDetailList) {
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnums.PRODUCT_NOT_EXIST);
            }
            //计算订单总价，此处应该从productInfo中获取
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            //订单详情入库
            //将productInfo复制到orderDetail
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetail.setDetailId(KeyUtil.generateUniqueKey());
            orderDetail.setOrderId(orderId);
            orderDetailRepository.save(orderDetail);
        }
        //写入数据库，（orderMaster和 orderDetail）
        OrderMaster orderMaster = new OrderMaster();

        //将orderDTO传输到orderMaster
        //先赋值id
        orderDTO.setOrderId(orderId);
        //在进行拷贝
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setBuyerAmount(orderAmount);
        //由于之前OrderDTO中这两个状态都没有设置默认值，所以赋值之后会变空
        orderMaster.setOrderStatus(OrderStatusEnums.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnums.WAIT.getCode());
        //保存orderMaster
        orderMasterRepository.save(orderMaster);

        //扣库存
        List<CartDTO> cartDTOList = orderDetailList.stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOList);

        /*推送消息*/
        webSocket.sendMessage("有新的订单");
        return orderDTO;
    }

    /**
     * 查询单个订单
     *
     * @param orderId
     */
    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if(orderMaster == null){
            log.error("【订单查询】 查不到该订单， orderId={}", orderId);
            throw new SellException(ResultEnums.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnums.ORDER_DETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    /**
     * 查询订单列表
     *
     * @param buyerOpenid
     * @param pageable
     */
    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage =
                orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        //获得该买家的订单列表
        List<OrderDTO> orderDTOList =
                OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());
        //返回Page对象
        return new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());
    }

    /**
     * 查询所有的订单列表
     *
     * @param pageable
     */
    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
        //获得该买家的订单列表
        List<OrderDTO> orderDTOList =
                OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());
        //返回Page对象
        return new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());
    }

    /**
     * 取消订单
     *
     * @param orderDTO
     */
    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态
        if(! orderDTO.getOrderStatus().equals(OrderStatusEnums.NEW.getCode())){
            log.error("【取消订单】 订单状态不正确， orderId={}, orderStatus={}",
                    orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnums.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnums.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(updateResult == null){
            log.error("【取消订单】 更新失败", orderMaster);
            throw new SellException(ResultEnums.ORDER_UPDATE_FAIL);
        }
        //返回库存
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【取消订单】 订单中无商品， orderDTO={}", orderDTO);
            throw new SellException(ResultEnums.ORDER_DETAIL_EMPTY);
        }

        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.increaseStock(cartDTOList);
        //如果以及支付，需要退款
        if(orderDTO.getPayStatus().equals(PayStatusEnums.SUCCESS.getCode())){
            //TODO
        }
        return orderDTO;
    }

    /**
     * 完结订单
     *
     * @param orderDTO
     */
    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if(! orderDTO.getOrderStatus().equals(OrderStatusEnums.NEW.getCode())){
            log.error("【完结订单】订单状态错误， orderId={}， orderStatus={}",
                    orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnums.ORDER_STATUS_ERROR);
        }
        //修改状态
        orderDTO.setOrderStatus(OrderStatusEnums.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(updateResult == null){
            log.error("【完结订单】 更新失败", orderMaster);
            throw new SellException(ResultEnums.ORDER_UPDATE_FAIL);
        }
        /*推送微信模板消息*/
        pushMessageService.orderStatus(orderDTO);
        //orderDTO
        return orderDTO;
    }

    /**
     * 支付订单
     *
     * @param orderDTO 订单数据传输对象
     */
    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if(! orderDTO.getOrderStatus().equals(OrderStatusEnums.NEW.getCode())){
            log.error("【支付订单】订单状态错误， orderId={}， orderStatus={}",
                    orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnums.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if(! orderDTO.getPayStatus().equals(PayStatusEnums.WAIT.getCode())){
            log.error("【支付订单】订单支付状态错误， orderDTO={}", orderDTO);
            throw new SellException(ResultEnums.PAY_STATUS_ERROR);
        }
        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnums.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(updateResult == null){
            log.error("【支付订单】 更新失败", orderMaster);
            throw new SellException(ResultEnums.ORDER_UPDATE_FAIL);
        }
        //返回dto
        return orderDTO;
    }
}
