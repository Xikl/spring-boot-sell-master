package com.ximo.springbootsellmaster.service.impl;

import com.ximo.springbootsellmaster.domain.SellerInfo;
import com.ximo.springbootsellmaster.service.SellerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: 朱文赵
 * @date: 2017/11/13
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerServiceImplTest {

    @Autowired
    private SellerService sellerService;

    private static final String OPENID = "123456";
    @Test
    public void findSellerInfoByOpenid() throws Exception {
        SellerInfo result = sellerService.findSellerInfoByOpenid(OPENID);
        System.out.println(result);
        Assert.assertEquals(OPENID, result.getOpenid());
    }

}