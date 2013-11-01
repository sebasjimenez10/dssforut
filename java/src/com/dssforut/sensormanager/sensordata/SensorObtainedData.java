package com.dssforut.sensormanager.sensordata;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

/**
 * This class maps the needed data to be stored in the data base, including
 * sensor data
 * @author Sebastian
 */
public class SensorObtainedData {
    //Data sent from sensor
    private String frame;
    //Time when the data got read
    private Date time;

    //Empty constructor
    public SensorObtainedData() {
    }
    //Constructor with full data
    public SensorObtainedData(String data, Date time) {
        this.frame = data;
        this.time = time;
    }
    
    /**
     * Returns data value
     * @return data
     */
    public String getFrame() {
        return frame;
    }
    
    /**
     * Sets the data value
     * @param data 
     */
    public void setFrame(String frame) {
        this.frame = frame;
    }

    /**
     * Gets the time when data was read
     * @return time
     */
    public Date getTime() {
        return time;
    }

    /**
     * Sets time value
     * @param time 
     */
    public void setTime(Date time) {
        this.time = time;
    }
    
    /**
     * Used for printing data contained in the object
     * @return 
     */
    @Override
    public String toString(){
    	
    	String splittedData [] = this.frame.split("/");
    	JSONObject data = new JSONObject();
    	
    	data.put("humedad", splittedData[0]);
    	data.put("temperatura", splittedData[1]);
    	data.put("humedad_suelo", splittedData[2]);
    	data.put("intensidad_luz", splittedData[3]);
    	data.put("radiacion", splittedData[4]);
    	data.put("nodo", splittedData[5]);
    	data.put("hora", new SimpleDateFormat("HH:mm:SS").format(this.time));
    	
        return data.toString();
    }
}