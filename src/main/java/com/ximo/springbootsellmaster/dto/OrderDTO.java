package com.ximo.springbootsellmaster.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ximo.springbootsellmaster.domain.OrderDetail;
import com.ximo.springbootsellmaster.enums.OrderStatusEnums;
import com.ximo.springbootsellmaster.enums.PayStatusEnums;
import com.ximo.springbootsellmaster.util.EnumUtil;
import com.ximo.springbootsellmaster.util.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单数据传输对象
 * Created by 朱文赵
 * 2017/9/11
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)//通过配置文件实现
public class OrderDTO {

    /** 订单id*/
    private String orderId;

    /** 买家名字*/
    private String buyerName;

    /** 买家手机号*/
    private String buyerPhone;

    /** 买家地址*/
    private String buyerAddress;

    /** 买家微信openid*/
    private String buyerOpenid;

    /** 买家订单总额*/
    private BigDecimal buyerAmount;

    /** 订单状态，默认为0，表示为新订单*/
    private Integer orderStatus;

    /** 支付状态，默认为0，表示为等待支付*/
    private Integer payStatus;

    /** 创建时间, 采用自己的序列化类来让时间单位变为秒 */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /** 更新时间, 采用自己的序列化类来让时间单位变为秒*/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    /** 订单详情list对象 */
    private List<OrderDetail> orderDetailList;

    /** 返回订单枚举对象*/
    @JsonIgnore //忽略该数据
    public OrderStatusEnums getOrderStatusEnums(){
        return EnumUtil.getByCode(orderStatus, OrderStatusEnums.class);
    }

    /** 返回支付枚举对象*/
    @JsonIgnore //忽略该数据
    public PayStatusEnums getPayStatusEnums(){
        return EnumUtil.getByCode(payStatus, PayStatusEnums.class);
    }
}
