/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dssforut.sensormanager.sensordata;



import com.dssforut.util.DatabaseInfoEnum;
import com.dssforut.util.PropsReader;
import static com.dssforut.sensormanager.sensordata.Tables.*;
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
 * Class in charge of sending/pushing received data to database
 * @author Sebastian
 */
public class SensorDataManager {
    
	// Constant array with the names of the variables
    private static final String sensorOrder [] = {
        "Humedad",
        "Temperatura",
        "Humedad del Suelo",
        "Intensidad de Luz",
        "Radiacion UV"};
    
    //Separator used in the packet
    private static final String separator = "/";
    
    /*
     * This method sends what was received from sensor to data base.
     */
    public void sendFullPacketToDb( SensorObtainedData data ) {
        
        Connection conn = getConnection();

        String separatedData [] = data.getFrame().split( separator );
        pushDataPacket(conn, separatedData, data.getTime(), separatedData[separatedData.length - 1]);

        closeConnection(conn);
    }

    /*
     * This method gets a connection to the configured database in config files.
     */
    private Connection getConnection() {
        Connection conn = null;

        PropsReader propsReader = new PropsReader();
        
        String userName = propsReader.getConfigProperty(PropsReader.ConfigTarget.database, DatabaseInfoEnum.db_user );
        String password = propsReader.getConfigProperty(PropsReader.ConfigTarget.database, DatabaseInfoEnum.db_password );
        String host = propsReader.getConfigProperty(PropsReader.ConfigTarget.database, DatabaseInfoEnum.db_host );
        String db_name = propsReader.getConfigProperty(PropsReader.ConfigTarget.database, DatabaseInfoEnum.db_name );
        
        String url = "jdbc:mysql://" + host + ":3306/" + db_name;

        try {
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection) DriverManager.getConnection(url, userName, password);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(SensorDataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conn;
    }

    /*
     * Closing connection after using it.
     */
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

    /*
     * Use jOOQ to insert data to database. Time and date are passed as parameters,
     * but the insertion requires sql Date not java.util.Date,
     * so it uses the java.util.Date values to build, sql.Date.
     */
    private void pushDataPacket(Connection conn, String[] separatedData, java.util.Date time, String node) throws DataAccessException {
        
        DSLContext create = DSL.using(conn, SQLDialect.MYSQL);

        for (int i = 0; i < separatedData.length - 1; i++) {
            
            create.insertInto(SENSOR_DATA_REGISTRY)
                .set(SENSOR_DATA_REGISTRY.FECHA, new Date(time.getTime()))
                .set(SENSOR_DATA_REGISTRY.HORA, new Time(time.getTime()))
                .set(SENSOR_DATA_REGISTRY.DATO, separatedData[i])
                .set(SENSOR_DATA_REGISTRY.VARIABLE, sensorOrder[i])
                .set(SENSOR_DATA_REGISTRY.NODO, node)
                .execute();
        }
    }
}
