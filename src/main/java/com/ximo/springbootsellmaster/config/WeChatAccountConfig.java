package com.ximo.springbootsellmaster.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

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

    /**微信开放平台id*/
    private String openAppId;

    /** 微信开放平台秘钥*/
    private String openAppSecret;

    /** 商户号*/
    private String mchId;

    /** 商户秘钥*/
    private String mchKey;

    /** 商户证书路径*/
    private String mchPath;

    /** 微信消息模板id 定义为map 用的时候可以直接 get("属性")*/
    private Map<String, String> templateId;
}
