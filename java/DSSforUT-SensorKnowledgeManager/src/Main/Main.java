/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import SensorManager.ConnectedSensorReader;
import SensorManager.DisconnectedSensorReader;
import SensorManager.SensorReader;
import SensorManager.SensorReaderObserver;

/**
 *
 * @author Sebastian
 */
public class Main {

    public static void main(String[] args) {

        SensorReaderObserver sro = new SensorReaderObserver();
        SensorReader sensorReader;
        
        boolean disconnectedEnvironment = true;
        
        if( disconnectedEnvironment ){
            
            sensorReader = new DisconnectedSensorReader();
        }else{
            
            sensorReader = new ConnectedSensorReader();
        }
        
        sensorReader.addObserver(sro);
        sensorReader.run();

    }
}
