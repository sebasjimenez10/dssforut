package com.dssforut.util;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropsReader {

	public String readProperty(String property){
		
		//Load specificFile properties in order to get one of them
        Properties targetFile = getFile("com/dssforut/util/web_config.properties");
        String value = targetFile.getProperty( property );
        
        //If the property was not found an exception is thrown
        if( value == null ){
            try {
                throw new Exception("The property: " + property + " was not found");
            } catch (Exception ex) {
                Logger.getLogger(PropsReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return value;
		
	}

	private Properties getFile(String file) {
		
		//Represents a properties file
        Properties props = new Properties();
        
        try {
            //Load a properties file
        	props.load( this.getClass().getClassLoader().getResourceAsStream(file) );

        } catch (IOException ex) {            
            Logger.getLogger(PropsReader.class.getName()).log(Level.SEVERE,
                    "It was not possible to load: " + file + " file.", ex);
        }
        
        return props;
		
	}
	
}
