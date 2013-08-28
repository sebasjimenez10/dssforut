/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SensorManager.SensorData;



import Util.DatabaseInfoEnum;
import Util.PropsReader;
import static SensorManager.SensorData.Tables.*;
import com.mysql.jdbc.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
/**
 *
 * @author Sebastian
 */
public class SensorDataManager {
    
    private final String sensorOrder [] = {
        "Humedad",
        "Temperatura",
        "Humedad del Suelo",
        "Intensidad de Luz",
        "Radiacion UV"};
    
    public void sendFullPacketToDb(String[] separatedData, String sensor, java.util.Date time) {
        
        Connection conn = getConnection();

        pushDataPacket(conn, separatedData, time);

        closeConnection(conn);
    }

    private Connection getConnection() {
        Connection conn = null;

        PropsReader propsReader = new PropsReader();
        
        String userName = propsReader.readProp( DatabaseInfoEnum.db_user.name() );
        String password = propsReader.readProp( DatabaseInfoEnum.db_password.name() );
        String host = propsReader.readProp( DatabaseInfoEnum.db_host.name() );
        String db_name = propsReader.readProp( DatabaseInfoEnum.db_name.name() );
        
        String url = "jdbc:mysql://" + host + ":3306/" + db_name;

        try {
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection) DriverManager.getConnection(url, userName, password);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(SensorDataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conn;
    }

    private void closeConnection(Connection conn) {

        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection closed");
            } catch (SQLException ignore) {
                Logger.getLogger(SensorDataManager.class.getName()).log(Level.SEVERE, null, ignore);
            }
        }
    }

    private void pushDataPacket(Connection conn, String[] separatedData, java.util.Date time) throws DataAccessException {
        
        DSLContext create = DSL.using(conn, SQLDialect.MYSQL);

        for (int i = 0; i < separatedData.length; i++) {
            
            create.insertInto(SENSOR_DATA_REGISTRY)
                .set(SENSOR_DATA_REGISTRY.FECHA, new Date(time.getTime()))
                .set(SENSOR_DATA_REGISTRY.HORA, new Time(time.getTime()))
                .set(SENSOR_DATA_REGISTRY.DATO, separatedData[i])
                .set(SENSOR_DATA_REGISTRY.VARIABLE, sensorOrder[i])
                .set(SENSOR_DATA_REGISTRY.NODO, "Nodo1")
                .execute();
        }
    }
}
