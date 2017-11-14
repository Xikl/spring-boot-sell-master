package com.ximo.springbootsellmaster.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description: 项目中统一url
 * @author: 朱文赵
 * @date: 2017/11/14
 */
@Component
@Data
@ConfigurationProperties(prefix = "projectUrl")
public class ProjectUrlConfig {

    /** 微信公众平台url*/
    public String weChatMpAuthorize;

    /** 微信开放平台url*/
    public String weChatOpenAuthorize;

    /** 点餐系统url*/
    public String sell;
}
