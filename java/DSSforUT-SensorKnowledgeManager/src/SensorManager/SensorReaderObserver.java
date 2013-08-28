/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SensorManager;

import SensorManager.SensorData.SensorDataManager;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Sebastian
 */
public class SensorReaderObserver implements Observer {

    private SensorDataManager sdm;
    private static final String separator = "/";
    
    public SensorReaderObserver() {
        sdm = new SensorDataManager();
    }
    
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Received obj from event: " + arg.toString());
        
        SensorObtainedData data = (SensorObtainedData) arg;
        
        String separatedData [] = data.getData().split( separator );
        String node = data.getNode();
        Date time = data.getTime();
        
        sdm.sendFullPacketToDb(separatedData, node, time);
    }
}