package com.dssforut.datacontroller;

import java.io.IOException;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import com.dssforut.datamodel.tables.SensorDataRegistry;
import com.dssforut.services.HistoryData;
import com.mysql.jdbc.Connection;


public class DataController {

	/**
	 * Responds to: Given a max date, min date and a variable, return the values
	 * between min and max
	 * 
	 * @param minFecha
	 * @param maxFecha
	 * @param variable
	 * @throws IOException 
	 */
	public HistoryData selectDataWithFechaAndVariable(java.util.Date maxFecha,
			java.util.Date minFecha, String variable) throws IOException {

		Connection conn = this.getConnection();
		
		DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
		
		Result<Record2<String, Time>> result = create
				.select(SensorDataRegistry.SENSOR_DATA_REGISTRY.DATO, SensorDataRegistry.SENSOR_DATA_REGISTRY.HORA)
				.from(SensorDataRegistry.SENSOR_DATA_REGISTRY)
				.where(SensorDataRegistry.SENSOR_DATA_REGISTRY.FECHA.greaterOrEqual(new Date(minFecha.getTime())))
				.and(SensorDataRegistry.SENSOR_DATA_REGISTRY.FECHA.lessOrEqual(new Date(maxFecha.getTime())))
				.and(SensorDataRegistry.SENSOR_DATA_REGISTRY.VARIABLE.equal(variable))
				.fetch();

		String dataResponse[] = new String[result.size()];
		String hourResponse[] = new String[result.size()];

		for (int i = 0; i < result.size(); i++) {
			dataResponse[i] = result.get(i).value1();
			hourResponse[i] = result.get(i).value2().toString();
		}	

		this.closeConnection(conn);
		return new HistoryData(dataResponse, hourResponse, result.size());
	}
	
	/**
	 * CONNECTION METHODS
	 */

	private void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws IOException {
		
		String db_host = "ec2-23-21-211-172.compute-1.amazonaws.com";
		String db_name = "dssforut_db";
		String db_user = "dssforut_root";
		String db_password = "dssforut_root";
		
		String url = "jdbc:mysql://" + db_host + ":3306/" + db_name;
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			Logger.getLogger(DataController.class.getName()).log(Level.SEVERE,
                    "It was not possible to instance DB Driver", e);
		}
		try {
			conn = (Connection) DriverManager.getConnection(url, db_user, db_password);
		} catch (SQLException e) {
			Logger.getLogger(DataController.class.getName()).log(Level.SEVERE,
                    "It was not possible to connect", e);
		}
		
		return conn;
	}
}
