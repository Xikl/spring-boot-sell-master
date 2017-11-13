package com.ximo.springbootsellmaster.service;

import com.ximo.springbootsellmaster.domain.SellerInfo;

/**
 * @description:
 * @author: 朱文赵
 * @date: 2017/11/13
 */
public interface SellerService {

    /**
     * 通过卖家端的openid 查询信息
     * @param openid 微信id
     * @return sellerInfo
     */
    SellerInfo findSellerInfoByOpenid(String openid);



}
