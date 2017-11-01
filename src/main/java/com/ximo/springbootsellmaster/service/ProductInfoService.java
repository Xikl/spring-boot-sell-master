package com.ximo.springbootsellmaster.service;

import com.ximo.springbootsellmaster.domain.ProductInfo;
import com.ximo.springbootsellmaster.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品信息service类
 * Created by 朱文赵
 * 2017/9/9
 */
public interface ProductInfoService {

    ProductInfo findOne(String productId);

    /**
     * 查询所有的在架商品
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    /**
     * 加库存
     * @param cartDTOList 购物车list
     */
    void increaseStock(List<CartDTO> cartDTOList);

    /**
     * 减库存
     * @param cartDTOList 购物车
     */
    void decreaseStock(List<CartDTO> cartDTOList);

}
