package com.ximo.springbootsellmaster.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品（包含类目）
 * Created by 朱文赵
 * 2017/9/9
 */
@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = 4979608004706525629L;

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;

}
