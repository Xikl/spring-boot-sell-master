package com.ximo.springbootsellmaster.repository;

import com.ximo.springbootsellmaster.domain.SellerInfo;
import com.ximo.springbootsellmaster.util.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @description:
 * @author: 朱文赵
 * @date: 2017/11/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Test
    public void save() throws Exception{
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.generateUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("123456");
        SellerInfo result = sellerInfoRepository.save(sellerInfo);
        System.out.println(result);
        Assert.assertNotNull(result);
    }


    @Test
    public void findByOpenid() throws Exception {
        SellerInfo sellerInfo = sellerInfoRepository.findByOpenid("123456");
        System.out.println(sellerInfo);
        Assert.assertEquals("123456", sellerInfo.getOpenid());
    }

}