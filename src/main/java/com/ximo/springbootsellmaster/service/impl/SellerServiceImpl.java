package com.ximo.springbootsellmaster.service.impl;

import com.ximo.springbootsellmaster.domain.SellerInfo;
import com.ximo.springbootsellmaster.repository.SellerInfoRepository;
import com.ximo.springbootsellmaster.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: 朱文赵
 * @date: 2017/11/13
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    /**
     * 通过卖家端的openid 查询信息
     *
     * @param openid 微信id
     * @return sellerInfo
     */
    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }
}
