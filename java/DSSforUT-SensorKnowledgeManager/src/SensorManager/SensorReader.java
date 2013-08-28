/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SensorManager;

import java.util.Observable;

/**
 * Sensor data reader
 * @author Sebastian Jimenez V.
 */
public abstract class SensorReader extends Observable {

    private boolean threadRunning = true;
    
    public void startReading(){
        
    }

    public boolean isThreadRunning() {
        return threadRunning;
    }

    public void setThreadRunning(boolean threadRunning) {
        this.threadRunning = threadRunning;
    }
}
