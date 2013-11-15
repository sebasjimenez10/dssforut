package com.dssforut.sensormanager;

import com.dssforut.main.LogAppender;
import com.dssforut.sensormanager.sensordata.SensorObtainedData;
import com.dssforut.util.EnvInfoEnum;
import com.dssforut.util.PropsReader;
import com.dssforut.util.RandomRange;
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
    //Default frecuency
    private int frecuency = 5000;
    
    /**
     * Used to start receiving data from sensors
     */
    @Override
    public void startReader(){
        //Read frecuency from env properties
        frecuency = Integer.parseInt( new PropsReader().getConfigProperty(
                PropsReader.ConfigTarget.environment,
                EnvInfoEnum.disconnected_env_frecuency ) );
        //Start reading thread
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
            String data = RandomRange.generateRandomRange(30f, 60f) + separator + RandomRange.generateRandomRange(15f, 25f)
                    + separator + RandomRange.generateRandomRange(3f, 11f) + separator + RandomRange.generateRandomRange(20f, 30f)
                    + separator + RandomRange.generateRandomRange(1, 10) + separator + "Fake Node" + RandomRange.generateRandomRange(1, 3);
            
            //Current time
            Date time = new Date();
            //Printing data and node
            LogAppender.logDebugMessage("\"Received data\" from:: " + data);
            //Packing data into object
            SensorObtainedData sd = new SensorObtainedData(data, time);
            //Setting state of the observable object
            this.setChanged();
            //notifying all observers with packed data object
            this.notifyObservers(sd);
            //Sleeping thread
            sleepThread( frecuency );
        }
    }

    /**
     * Makes the thread sleep for millis milliseconds
     * @param millis time in milliseconds in which the thread will be sleeping
     */
    private void sleepThread(int millis) {
        try {
        	LogAppender.logDebugMessage("Sleeping thread");
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            Logger.getLogger(DisconnectedSensorReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        LogAppender.logDebugMessage("Waking up thread");
    }
    
}
