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
    
    private String sensorData;

    public String getSensorData() {
        return sensorData;
    }

    public void setSensorData(String sensorData) {
        this.sensorData = sensorData;
    }
    
}
