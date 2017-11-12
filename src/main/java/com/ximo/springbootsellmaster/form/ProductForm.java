package com.ximo.springbootsellmaster.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 表单验证模块
 * @author: 朱文赵
 * @date: 2017/11/12
 */
@Data
public class ProductForm {

    /** 商品id，非自增String类型 */
    private String productId;

    /** 商品名字 */
    private String productName;

    /** 商品价格 */
    private BigDecimal productPrice;

    /** 商品库存 */
    private Integer productStock;

    /** 商品描述 */
    private String productDescription;

    /** 商品小图 */
    private String productIcon;

    /** 商品类目编号 */
    private Integer categoryType;


}
