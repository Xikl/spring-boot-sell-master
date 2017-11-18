package com.ximo.springbootsellmaster.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品详情
 * Created by 朱文赵
 * 2017/9/9
 */
@Data
public class ProductInfoVO implements Serializable {

    private static final long serialVersionUID = -5488070972183326126L;

    @JsonProperty("id")
//    @SerializedName("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;
}
