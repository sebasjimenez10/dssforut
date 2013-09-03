/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dssforut.sensormanager;

import com.dssforut.sensormanager.sensordata.SensorObtainedData;
import com.dssforut.util.EnvInfoEnum;
import com.dssforut.util.PropsReader;
import com.dssforut.util.PropsReader.ConfigTarget;
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
 * This class represents a connected sensor reader.
 * This means a XBee Coordinator is available for reading.
 * @author Sebastian
 */
public class ConnectedSensorReader extends SensorReader implements PacketListener {

    //XBee object used to obtain data
    private XBee xbee;

    //Constructor
    public ConnectedSensorReader() {
    
        xbee = new XBee();
    }
    
    /**
     * Start reading data
     */
    @Override
    public void startReader(){
        new Thread(this).start();
    }
    
    /**
     * Stop reading data
     */
    @Override
    public void stopReader(){
        super.stopReader();
        xbee.close();
    }
    
    /**
     * Runs the thread. Sets this clase as a PacketListener. This means,
     * every time a packet arrives this class is notified, with the respective
     * obtained data
     */
    @Override
    public void run(){
        //PropertyConfigurator.configure("log4j.properties");
    	//Get port from environment configuration file
    	String port = new PropsReader().getConfigProperty(ConfigTarget.environment, EnvInfoEnum.usb_com_port);
        try {
            xbee.open(port, 9600);
            xbee.addPacketListener(this);
            
        } catch (XBeeException xe) {
            Logger.getLogger(ConnectedSensorReader.class.getName())
                    .log(Level.SEVERE, null, xe);
        }
    }
    
    /**
     * Method called in PacketListener interface to process XBeeResponse
     * @param response 
     */
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