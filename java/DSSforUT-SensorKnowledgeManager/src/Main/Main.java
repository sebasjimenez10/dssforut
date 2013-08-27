/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import SensorManager.DisconnectedSensorReader;
import SensorManager.SensorReaderObserver;

/**
 *
 * @author Sebastian
 */
public class Main {

    public static void main(String[] args) {

        SensorReaderObserver sro = new SensorReaderObserver();
        DisconnectedSensorReader dsr = new DisconnectedSensorReader();
        
        dsr.addObserver(sro);
        dsr.run();

    }
}
