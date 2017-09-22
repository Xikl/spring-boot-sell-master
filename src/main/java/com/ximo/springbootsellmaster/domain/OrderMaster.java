package com.ximo.springbootsellmaster.domain;

import com.ximo.springbootsellmaster.enums.OrderStatusEnums;
import com.ximo.springbootsellmaster.enums.PayStatusEnums;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单主表
 * Created by 朱文赵
 * 2017/9/10
 */
@Entity
@Data
@DynamicUpdate//自动更新时间
public class OrderMaster {

    /** 订单id*/
    @Id
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
    private Integer orderStatus = OrderStatusEnums.NEW.getCode();

    /** 支付状态，默认为0，表示为等待支付*/
    private Integer payStatus = PayStatusEnums.WAIT.getCode();

    /** 创建时间*/
    private Date createTime;

    /** 更新时间*/
    private Date updateTime;

}
