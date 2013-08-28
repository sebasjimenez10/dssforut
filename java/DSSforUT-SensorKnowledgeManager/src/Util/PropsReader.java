/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import SensorManager.SensorData.SensorDataManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebastian
 */
public class PropsReader {
    
    public String readProp(String prop){
        
        Properties propertiesFile = new Properties();
        String readProp = null;
        
        try {
                //load a properties file
    		propertiesFile.load(new FileInputStream("db_config.properties"));
 
                //get the property value and print it out
                readProp = propertiesFile.getProperty( prop );
                
    	} catch (IOException ex) {            
            Logger.getLogger(PropsReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return readProp;
    }
    
}
