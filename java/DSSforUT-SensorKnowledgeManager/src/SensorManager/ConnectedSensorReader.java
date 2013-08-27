/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SensorManager;

import com.rapplogic.xbee.api.ApiId;
import com.rapplogic.xbee.api.PacketListener;
import com.rapplogic.xbee.api.XBee;
import com.rapplogic.xbee.api.XBeeException;
import com.rapplogic.xbee.api.XBeeResponse;
import com.rapplogic.xbee.api.zigbee.ZNetRxResponse;
import com.rapplogic.xbee.util.ByteUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebastian
 */
public class ConnectedSensorReader extends SensorReader implements Runnable {

    @Override
    public void run() {
        
        //PropertyConfigurator.configure("log4j.properties");
        XBee xbee = new XBee();

        try {
            xbee.open("COM5", 9600);

            //Atiende los eventos cuando llega un paquete
            xbee.addPacketListener(new PacketListener() {
                @Override
                public void processResponse(XBeeResponse response) {
                    
                    if( response.getApiId() == ApiId.ZNET_RX_RESPONSE ){
                        ZNetRxResponse znetResponse = (ZNetRxResponse) response;
                        System.out.println("Received Data: " + ByteUtils.toString(znetResponse.getData()));
                    }
                }
            });
            
        } catch (XBeeException xe) {
            Logger.getLogger(ConnectedSensorReader.class.getName())
                    .log(Level.SEVERE, null, xe);
        } finally {
            //Se comenta o no dependiendo si se desea que permanezca escuchando
            //xbee.close();
        }
        
    }
    
    
    
}
