package com.ximo.springbootsellmaster.service.impl;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @description: 推送 websocket
 * @author: 朱文赵
 * @date: 2017/11/16
 */
@Component
@ServerEndpoint("/webSocket")
@Slf4j
@EqualsAndHashCode
public class WebSocket {

    /** 与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;

    private CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);
        log.info("【websocket消息】有新的连接, 总数为={}", webSocketSet.size());
    }

    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        log.info("【websocket消息】连接断开，总数为={}", webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message){
        log.info("【websocket消息】收到新的消息={}", message);
    }

    public void sendMessage(String message){
        for (WebSocket webSocket : webSocketSet) {
            log.info("【websocket消息】广播消息， message={}", message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.error("【websocket消息】出现异常，e={}", e);
            }
        }

    }

}
