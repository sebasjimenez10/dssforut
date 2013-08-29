package com.dssforut.sensormanager;

import java.util.Observable;

/**
 * This class represents an abstract reader
 * @author Sebastian
 */
public abstract class SensorReader extends Observable {

    /**
     * This method is used to begin with data reading
     */
    public void startReader(){
        
    }
    
    /**
     * This method is used to stop data reading
     */
    public void stopReader(){
        
    }
}
