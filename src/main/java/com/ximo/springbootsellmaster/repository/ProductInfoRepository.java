package com.ximo.springbootsellmaster.repository;

import com.ximo.springbootsellmaster.domain.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 朱文赵
 * 2017/9/9
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);

}
