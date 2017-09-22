package com.ximo.springbootsellmaster.service.impl;

import com.ximo.springbootsellmaster.domain.ProductInfo;
import com.ximo.springbootsellmaster.dto.CartDTO;
import com.ximo.springbootsellmaster.enums.ProductStatusEnums;
import com.ximo.springbootsellmaster.enums.ResultEnums;
import com.ximo.springbootsellmaster.exception.SellException;
import com.ximo.springbootsellmaster.repository.ProductInfoRepository;
import com.ximo.springbootsellmaster.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ProductInfoService实现类
 * Created by 朱文赵
 * 2017/9/9
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoRepository.findOne(productId);
    }

    /**
     * 查询所有的在架商品
     * @return
     */
    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnums.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    /**
     * 加库存
     *
     * @param cartDTOList 购物车list
     */
    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList){
            ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());
            if (productInfo == null){
                //商品不存在
                throw new SellException(ResultEnums.PRODUCT_NOT_EXIST);
            }
            //加库存
            Integer result  = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }

    }

    /**
     * 减库存
     *
     * @param cartDTOList 购物车
     */
    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList){
            ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());
            if (productInfo == null){
                //商品不存在
                throw new SellException(ResultEnums.PRODUCT_NOT_EXIST);
            }

            //减库存
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0){
                throw new SellException(ResultEnums.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }
}
