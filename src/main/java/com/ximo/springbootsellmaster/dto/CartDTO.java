package com.ximo.springbootsellmaster.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 购物车
 * Created by 朱文赵
 * 2017/9/11
 */
@Data
@AllArgsConstructor
public class CartDTO {

    /** 商品id */
    private String productId;

    /** 商品数量 */
    private Integer productQuantity;

}
