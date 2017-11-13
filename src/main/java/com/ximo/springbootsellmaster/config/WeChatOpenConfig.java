package com.ximo.springbootsellmaster.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @description: 微信开放平台的配置文件
 * @author: 朱文赵
 * @date: 2017/11/13
 */
@Component
public class WeChatOpenConfig {

    @Autowired
    private WeChatAccountConfig weChatAccountConfig;

    /**
     * 获得微信open的配置
     * @return
     */
    @Bean
    public WxMpService wxOpenService(){
        WxMpService wxOpenService = new WxMpServiceImpl();
        wxOpenService.setWxMpConfigStorage(wxOpenConfigStorage());
        return wxOpenService;
    }

    /**
     * 获得一个开放平台的单独的配置
     * @return
     */
    @Bean
    public WxMpConfigStorage wxOpenConfigStorage(){
        WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpInMemoryConfigStorage.setAppId(weChatAccountConfig.getOpenAppId());
        wxMpInMemoryConfigStorage.setSecret(weChatAccountConfig.getOpenAppSecret());
        return wxMpInMemoryConfigStorage;
    }

}
