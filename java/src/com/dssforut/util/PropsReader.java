package com.dssforut.util;

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
    
    //Posible configuration targets
    public enum ConfigTarget{
        database, environment;
    }
    
    //Route Keys
    private final static String DATABASE_ROUTE = "database_route";
    private final static String ENVIRONMENT_ROUTE = "env_route";
    
    //Route file
    private final static String ROUTES_FILE = "properties/routes_file.properties";
    //Database files
    private final static String DB_CONFIG_FILE = "db_config.properties";
    //Environment files
    private final static String ENV_CONFIG_FILE = "environment.properties";
    
    /**
     * Read a properties file
     * @param file
     * @return Properties file read
     */
    private Properties getFile(String file){
        //Represents a properties file
        Properties props = new Properties();
        
        try {
            //Load a properties file
            props.load( new FileInputStream(file) );
        	//props.load( this.getClass().getResourceAsStream(file) );

        } catch (IOException ex) {            
            Logger.getLogger(PropsReader.class.getName()).log(Level.SEVERE,
                    "It was not possible to load: \"" + file + "\" file.", ex);
        }
        
        return props;
    }
    
    /**
     * Bridge method to getConfigProperty receiving EnvInfoEnum element
     * @param targetConfig
     * @param property
     * @return
     */
    public String getConfigProperty(ConfigTarget targetConfig, EnvInfoEnum property){
    	return this.getConfigProperty(targetConfig, property.name());
    }
    
    /**
     * Bridge method to getConfigProperty receiving Database element
     * @param targetConfig
     * @param property
     * @return
     */
    public String getConfigProperty(ConfigTarget targetConfig, DatabaseInfoEnum property){
    	return this.getConfigProperty(targetConfig, property.name());
    }
    
    /**
     * This method get a property from specific config file
     * @param targetConfig desired configuration (database, environment, etc.)
     * Options can be found in PropsReader.ConfigTarget enum
     * @param property desired property in configuration file.
     * Options can be found in DatabaseInfoEnum or EnvInfoEnum
     * @return The value of the requested property
     * @throws Exception if the property does not exist
     */
    private String getConfigProperty(ConfigTarget targetConfig, String property){
        
        //Get the file where are the routes for the config files
        Properties routesFile = getFile(ROUTES_FILE);
        //Specific route given a targetConfig
        String specificFile = null;
        
        /*
         * Depending on what is the value of targetConfig this part loads
         * the corresponding configuration file.
         */
        if( targetConfig.equals(ConfigTarget.database) ){
            
            specificFile = routesFile.getProperty(DATABASE_ROUTE).concat(DB_CONFIG_FILE);
        }else if( targetConfig.equals(ConfigTarget.environment) ){
            
            specificFile = routesFile.getProperty(ENVIRONMENT_ROUTE).concat(ENV_CONFIG_FILE);
        }
        
        //Load specificFile properties in order to get one of them
        Properties targetFile = getFile(specificFile);
        String value = targetFile.getProperty( property );
        
        //If the property was not found an exception is thrown
        if( value == null ){
            try {
                throw new Exception("The property: " + property +
                        " from target " + targetConfig.name() + " was not found");
            } catch (Exception ex) {
                Logger.getLogger(PropsReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return value;
    }
}
