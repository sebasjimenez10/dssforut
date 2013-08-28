/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SensorManager;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebastian
 */
public class DisconnectedSensorReader extends SensorReader implements Runnable{

    @Override
    public void startReading() {
        super.startReading();
        this.run();
    }
    
    @Override
    public void run() {
        
        String separator = "/";
        
        while( this.isThreadRunning() ){
            
            String data = Math.random() + separator + Math.random()
                    + separator + Math.random() + separator + Math.random()
                    + separator + Math.random();
            String node = "Fake Node" + (int)( 1 + (int)Math.random()*(3 - 1));
            Date time = new Date();
            
            System.out.println("\"Received data\" from: " + node + ": " + data);
            
            SensorObtainedData sd = new SensorObtainedData(data, node, time);

            this.setChanged();
            
            synchronized (this){
                this.notifyObservers(sd);
            }
            
            sleepThread(5000);
        }
        
    }

    private void sleepThread(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            Logger.getLogger(DisconnectedSensorReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
