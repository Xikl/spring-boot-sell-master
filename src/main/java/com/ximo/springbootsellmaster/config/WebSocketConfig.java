package com.ximo.springbootsellmaster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @description: websocket的配置
 * @author: 朱文赵
 * @date: 2017/11/16
 */
//@Component
public class WebSocketConfig {

//    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

}
