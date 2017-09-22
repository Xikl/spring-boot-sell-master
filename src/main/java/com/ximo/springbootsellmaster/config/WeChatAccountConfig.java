package com.ximo.springbootsellmaster.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取配置文件类获得两个属性
 * Created by 朱文赵
 * 2017/9/16
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WeChatAccountConfig {

    /** 公众号appId*/
    private String mpAppId;

    /** 公众号appSecret*/
    private String mpAppSecret;

    /** 商户号*/
    private String mchId;

    /** 商户秘钥*/
    private String mchKey;

    /** 商户证书路径*/
    private String mchPath;

}
