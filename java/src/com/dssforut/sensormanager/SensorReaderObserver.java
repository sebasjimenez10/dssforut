package com.dssforut.sensormanager;

import com.dssforut.main.LogAppender;
import com.dssforut.sensormanager.sensordata.SensorObtainedData;
import com.dssforut.sensormanager.sensordata.SensorDataManager;
import com.dssforut.util.ServiceCaller;

import java.util.Observable;
import java.util.Observer;

/**
 * This class is the one who is watching the sensor reader
 * and the one who is spreding the data. (Database, Knowledge Base, etc)
 * @author Sebastian
 */
public class SensorReaderObserver implements Observer {

    //Sensor data manager is the one who pushes
    //the received data to the database
    private SensorDataManager sdm;
    
    //CONTRUCTOR
    public SensorReaderObserver() {
        sdm = new SensorDataManager();
    }
    
    /**
     * In this method observer gets the data and pass it to SensorDataManager
     * to store it in the database
     * @param o
     * @param arg 
     */
    @Override
    public void update(Observable o, Object arg) {
        //Printing out what came
    	LogAppender.logDebugMessage("Received obj from event: " + arg.toString());
        
        //Casting the received data to SensorObtainedData which contains the
        //full structure of the sensor data
        SensorObtainedData data = (SensorObtainedData) arg;
        
        //Casted object is sent to store the data
        sdm.sendFullPacketToDb( data );
        
        
        //Call WebService to post data on server
        ServiceCaller.postDataOnServer( data.toString() );
        
        //In some point here the data must be sent to knowledge base so
        //it can be figured out if everything is OK or if there is something
        //going wrong.
    }
}