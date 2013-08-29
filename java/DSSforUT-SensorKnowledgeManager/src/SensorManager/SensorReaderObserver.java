/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SensorManager;

import SensorManager.SensorData.SensorObtainedData;
import SensorManager.SensorData.SensorDataManager;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Sebastian
 */
public class SensorReaderObserver implements Observer {

    private SensorDataManager sdm;
    
    public SensorReaderObserver() {
        sdm = new SensorDataManager();
    }
    
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Received obj from event: " + arg.toString());
        
        SensorObtainedData data = (SensorObtainedData) arg;
        sdm.sendFullPacketToDb( data );
        
        //En algun punto se envia lo obtenido a la base de conocimiento para
        //que esto evalue los sensado.
    }
}