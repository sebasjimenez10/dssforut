package com.dssforut.sensormanager.sensordata;

import java.util.Date;

/**
 * This class maps the needed data to be stored in the data base, including
 * sensor data
 * @author Sebastian
 */
public class SensorObtainedData {
    //Data sent from sensor
    private String data;
    //Sensor ID sending data
    private String node;
    //Time when the data got read
    private Date time;

    //Empty constructor
    public SensorObtainedData() {
    }
    //Constructor with full data
    public SensorObtainedData(String data, String node, Date time) {
        this.data = data;
        this.node = node;
        this.time = time;
    }
    
    /**
     * Returns data value
     * @return data
     */
    public String getData() {
        return data;
    }
    
    /**
     * Sets the data value
     * @param data 
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Returns the node ID
     * @return node
     */
    public String getNode() {
        return node;
    }

    /**
     * Sets the node ID
     * @param node 
     */
    public void setNode(String node) {
        this.node = node;
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
        return "{data: " + this.data + " node: " + this.node + "}";
    }
}