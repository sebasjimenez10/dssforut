package com.dssforut.sensormanager;

import com.dssforut.sensormanager.sensordata.SensorObtainedData;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is a mock or simulation of data in order to work in a disconnected
 * environment, where XBee is not available. This class uses a thread which
 * has a stablished frecuency (MUST FIX to make it variable) in which the data
 * is delivered to the observer
 * @author Sebastian
 */
public class DisconnectedSensorReader extends SensorReader{

    //Indicates if the thread is running or not
    private boolean threadRunning = true;
    
    /**
     * Used to start receiving data from sensors
     */
    @Override
    public void startReader(){
        new Thread(this).start();
    }
    
    /**
     * Used to stop reader by chaging threadRunning value
     */
    @Override
    public void stopReader(){
        super.stopReader();
        threadRunning = false;
    }
    
    /**
     * Begin to simulate data. In this scenario the data are simulated
     * via random numbers.
     */
    @Override
    public void run() {
        //Separator used to separate data according to the definition.
        String separator = "/";
        
        //while threadRunning is true we simulate data and send it to observer
        while( threadRunning ){
            //Simulated data
            String data = Math.random() + separator + Math.random()
                    + separator + Math.random() + separator + Math.random()
                    + separator + Math.random();
            //Fake sender node
            String node = "Fake Node" + (int)( 1 + (int)Math.random()*(3 - 1));
            //Current time
            Date time = new Date();
            //Printing data and node
            System.out.println("\"Received data\" from: " + node + ": " + data);
            //Packing data into object
            SensorObtainedData sd = new SensorObtainedData(data, node, time);
            //Setting state of the observable object
            this.setChanged();
            //notifying all observers with packed data object
            this.notifyObservers(sd);
            //Sleeping thread
            //FIXME time must be variable
            sleepThread(5000);
        }
    }

    /**
     * Makes the thread sleep for millis milliseconds
     * @param millis time in milliseconds in which the thread will be sleeping
     */
    private void sleepThread(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            Logger.getLogger(DisconnectedSensorReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
