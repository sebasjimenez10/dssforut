/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dssforut.services;

import com.dssforut.realtime.DataHolder;
import com.dssforut.realtime.DataWebSocket;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Sebastian
 */

@Path("/realtime")
public class RealtimeService {
    
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response postSensorData( String data ){
        
        System.out.println("Posted data = " + data);
        
        DataHolder dh = new DataHolder();
        dh.saveData(data);
        
        DataWebSocket dws = new DataWebSocket();
        dws.broadcastMessage(data);
        
        return Response.status(201).entity(data).build();
    }
    
}