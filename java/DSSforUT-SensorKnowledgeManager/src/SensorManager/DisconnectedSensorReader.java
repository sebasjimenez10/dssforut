/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SensorManager;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebastian
 */
public class DisconnectedSensorReader extends SensorReader implements Runnable{

    private boolean threadRunning = true;
    
    @Override
    public void run() {
        
        String separator = "/";
        
        while( threadRunning ){
            
            String data = Math.random() + separator + Math.random()
                    + separator + Math.random() + separator + Math.random()
                    + separator + Math.random();
            
            System.out.println("\"Received data\" from sensor: " + data);
            
            this.setSensorData(data);
            this.setChanged();
            
            synchronized (this){
                this.notifyObservers(data);
            }
            sleepThread(5000);
        }
        
    }

    public boolean isThreadRunning() {
        return threadRunning;
    }

    public void setThreadRunning(boolean threadRunning) {
        this.threadRunning = threadRunning;
    }

    private void sleepThread(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            Logger.getLogger(DisconnectedSensorReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
