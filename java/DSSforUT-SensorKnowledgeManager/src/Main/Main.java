package Main;

import SensorManager.ConnectedSensorReader;
import SensorManager.DisconnectedSensorReader;
import SensorManager.SensorReader;
import SensorManager.SensorReaderObserver;
import Util.EnvInfoEnum;
import Util.PropsReader;

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

        //Data observer
        SensorReaderObserver sro = new SensorReaderObserver();
        //Data reader
        SensorReader sensorReader;
        
        //Get env configuration to know if we are connected or not to xbee
        String disconnectedEnvironment = new PropsReader().getConfigProperty(
                PropsReader.ConfigTarget.environment,
                EnvInfoEnum.real_env.name());
        
        if (disconnectedEnvironment.equals("false")) {

            sensorReader = new DisconnectedSensorReader();
        } else {

            sensorReader = new ConnectedSensorReader();
        }
        
        //Observer is added to the list of observers
        sensorReader.addObserver(sro);
        //data reader starts to read data.
        sensorReader.startReader();
        
    }
}