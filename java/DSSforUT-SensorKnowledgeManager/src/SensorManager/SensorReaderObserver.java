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
        
        System.out.println("");
        
    }
    
}

//        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
//        SimpleDateFormat hour = new SimpleDateFormat("HH:mm:ss");
//            Date fullDate = new Date();
//            String data = date.format(fullDate) + separator 
//             + hour.format(fullDate) + separator
//             + ( 1 + (int)Math.random() * (6 - 1) ) + separator
//             + variables[( (int)Math.random() * (6 - 1) )] + separator;