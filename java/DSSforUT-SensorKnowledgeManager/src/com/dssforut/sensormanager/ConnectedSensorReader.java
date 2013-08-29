/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dssforut.sensormanager;

import com.dssforut.sensormanager.sensordata.SensorObtainedData;
import com.rapplogic.xbee.api.ApiId;
import com.rapplogic.xbee.api.PacketListener;
import com.rapplogic.xbee.api.XBee;
import com.rapplogic.xbee.api.XBeeException;
import com.rapplogic.xbee.api.XBeeResponse;
import com.rapplogic.xbee.api.zigbee.ZNetRxResponse;
import com.rapplogic.xbee.util.ByteUtils;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebastian
 */
public class ConnectedSensorReader extends SensorReader implements PacketListener {

    private XBee xbee;

    public ConnectedSensorReader() {
    
        xbee = new XBee();
        
    }
    
    @Override
    public void startReader(){
        super.startReader();
        
        //PropertyConfigurator.configure("log4j.properties");
        try {
            xbee.open("COM5", 9600);
            xbee.addPacketListener(this);
            
        } catch (XBeeException xe) {
            Logger.getLogger(ConnectedSensorReader.class.getName())
                    .log(Level.SEVERE, null, xe);
        }
        
    }
    
    @Override
    public void stopReader(){
        super.stopReader();
        xbee.close();
    }
    
    @Override
    public void processResponse(XBeeResponse response) {

        if (response.getApiId() == ApiId.ZNET_RX_RESPONSE) {
            ZNetRxResponse znetResponse = (ZNetRxResponse) response;

            String data = ByteUtils.toString(znetResponse.getData());
            String node = "Nodo Cualquiera";
            System.out.println("Received Data from Sensor: " + data);

            SensorObtainedData sod = new SensorObtainedData(data, node, new Date());
            
            this.setChanged();
            this.notifyObservers(sod);
        }
    }
    
}
