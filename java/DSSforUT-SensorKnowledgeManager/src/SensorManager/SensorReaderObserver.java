/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SensorManager;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Sebastian
 */
public class SensorReaderObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        
        System.out.println("Received data from event: " + arg.toString());
        
        String data = arg.toString();        
        String separatedData [] = data.split("/");
        
        for( String s : separatedData ){
            System.out.println("Data: " + s);
        }
        
        //Se llama a la parte de insercion de datos en la base de datos.
        //"Hey data base tengo un dato nuevo, tome".
    }
    
}