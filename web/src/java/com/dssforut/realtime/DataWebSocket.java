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

@ServerEndpoint(value = "/datasocket")
public class DataWebSocket {
    
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
    
    @OnOpen
    public void onOpen(Session session){
        sessions.add(session);
        this.sendStoredData(session);
        System.out.println("Session added: " + session.getId() + " Total sessions: " + sessions.size());
    }
    
    @OnMessage
    public void onMessage(String message){}
    
    @OnClose
    public void onClose(Session session){
        sessions.remove(session);
        System.out.println("Session removed: " + session.getId() + " Total sessions: " + sessions.size());
    }
    
    /**
     * Broadcasts a message to every opened session
     * @param message 
     */
    public void broadcastMessage(String message){
        for (Session openedSession : sessions) {
            try {
                openedSession.getBasicRemote().sendText(message);
            } catch (IOException ex) {
                Logger.getLogger(WebSocketTester.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void sendStoredData(Session session) {
        DataHolder dh = new DataHolder();
        String[] memoryData = dh.getMemoryData();
        
        for (String string : memoryData) {
            try {
                session.getBasicRemote().sendText(string);
            } catch (IOException ex) {
                Logger.getLogger(DataWebSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println(memoryData.length + " data sent");
    }
}
