package com.ximo.springbootsellmaster.repository;

import com.ximo.springbootsellmaster.domain.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: 朱文赵
 * @date: 2017/11/13
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String>{

    /**
     * 通过openid 查找 sellerInfo
     * @param openid
     * @return
     */
    SellerInfo findByOpenid(String openid);

}
