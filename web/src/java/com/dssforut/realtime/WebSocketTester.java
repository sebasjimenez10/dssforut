/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dssforut.realtime;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Sebastian
 */
@ServerEndpoint(value = "/tester")
public class WebSocketTester {

    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session session) {

        sessions.add(session);
        this.onMessage("Server side WORKING");
        System.out.println("New session added");
    }

    @OnMessage
    public void onMessage(String message) {

        System.out.println("Message:" + message);
        for (Session openSession : sessions) {
            try {
                openSession.getBasicRemote().sendText(message);
            } catch (IOException ex) {
                Logger.getLogger(WebSocketTester.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @OnClose
    public void onClose(Session session) {

        sessions.remove(session);

    }
}