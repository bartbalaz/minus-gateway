package com.bbalaz.minusgateway.controller;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Log4j2
public class SocketHandler extends TextWebSocketHandler {

    List<WebSocketSession> sessions = new ArrayList();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {

        for(WebSocketSession webSocketSession : sessions) {
            webSocketSession.sendMessage( new TextMessage("Hello ! " + message.getPayload()));
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //the messages will be broadcasted to all users.
        log.info("New session!");
        sessions.add(session);
    }
}
