package com.dssforut.sensormanager;

import java.util.Observable;

/**
 * This class represents an abstract reader
 * @author Sebastian
 */
public abstract class SensorReader extends Observable implements Runnable {

    /**
     * This method is used to start reading data
     */
    public void startReader(){
        
    }
    
    /**
     * This method is used to stop data reading
     */
    public void stopReader(){
        
    }

    /**
     * Overided method from Runnable interface
     */
    @Override
    public void run() {
        
    }
}
