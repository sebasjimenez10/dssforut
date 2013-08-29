package SensorManager;

import SensorManager.SensorData.SensorObtainedData;
import SensorManager.SensorData.SensorDataManager;
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
        System.out.println("Received obj from event: " + arg.toString());
        
        //Casting the received data to SensorObtainedData which contains the
        //full structure of the sensor data
        SensorObtainedData data = (SensorObtainedData) arg;
        
        //Casted object is sent to store the data
        sdm.sendFullPacketToDb( data );
        
        //In some point here the data must be sent to knowloedge base so
        //it can be figured out if everythings is ok or if there is something
        //going wrong.
    }
}