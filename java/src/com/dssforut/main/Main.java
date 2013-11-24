package com.dssforut.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.PropertyConfigurator;

import com.dssforut.sensormanager.ConnectedSensorReader;
import com.dssforut.sensormanager.DisconnectedSensorReader;
import com.dssforut.sensormanager.SensorReader;
import com.dssforut.sensormanager.SensorReaderObserver;
import com.dssforut.util.EnvInfoEnum;
import com.dssforut.util.PropsReader;

/**
 * This class initiates the program.
 * @author Sebastian
 */
public class Main {

    /*
     * This method creates an data observer and a data reader,
     * then the observer is added to the observer list in order to be
     * notified when new data is available, finally the sensor reader
     * starts to read data.
     */
    public static void main(String[] args) {

    	//Logger settings
    	PropertyConfigurator.configure("dssforutlog.properties");
    	
        //Data observer
        SensorReaderObserver sro = new SensorReaderObserver();
        //Data reader
        SensorReader sensorReader;
        
        //Get env configuration to know if we are connected or not to xbee
        String disconnectedEnvironment = new PropsReader().getConfigProperty(
                PropsReader.ConfigTarget.environment,
                EnvInfoEnum.real_env );
        
        if (disconnectedEnvironment.equals("false")) {
            
            sensorReader = new DisconnectedSensorReader();
        } else {
            
            sensorReader = new ConnectedSensorReader();
        }
        
        //Observer is added to the list of observers
        sensorReader.addObserver(sro);
        //Data reader starts to read data.
        sensorReader.startReader();
        
        //Sensor thread starts reading. This space can be used for forward
        //activities in the process
        
        //Console option to close program
        System.out.println("There are three logs: jooq.log, dssforut.log and Xbee.log, they are created under /bin folder");
        System.out.println("Enter \"1\" to stop the application");
        try{
        	String s = "";
        	while(!s.equals("1")){
        	
	        	BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	    	    s = bufferRead.readLine();
	    	    System.out.println("You entered: " + s);
        	}
        	System.out.println("Closing program, bye");
        	System.exit(0);
    	    
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
    }
}