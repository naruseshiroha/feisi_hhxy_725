package com.example.service.impl;

import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.example.websocket.WebSocketServer;

/**
 * 解决点赞通知异步化而产生的中间类
 * 异步的注解写在中间类的发送方法上生效
 */
@Service
public class WsService {
    @Resource
    public WebSocketServer webSocketServer;

    @Async
    public void sendInfo(String message,String logId){
        MDC.put("LOG_ID",logId);
        // 使用ws服务端的发送消息方法发送消息
        webSocketServer.sendInfo(message);
    }
}
