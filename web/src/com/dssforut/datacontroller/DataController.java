package com.dssforut.datacontroller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import com.dssforut.datamodel.tables.SensorDataRegistry;
import com.dssforut.services.HistoryData;
import com.dssforut.util.PropsReader;
import com.mysql.jdbc.Connection;


public class DataController {
	
	private Connection conn;
	private DSLContext create;
	
	public DataController() {
		try {
			conn = this.getConnection();
			create = DSL.using(conn, SQLDialect.MYSQL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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

		Date dates [] = this.getDatesInRange(maxFecha, minFecha);
		String avgPerDate [] = this.getAvgPerDate(dates, variable);
		
		String strDates [] = new String[dates.length];
		for (int i = 0; i < dates.length; i++) {
			strDates[i] = dates[i].toString();
			
		}
		
		return new HistoryData(avgPerDate, strDates, dates.length);
	}
	
	/**
	 * 
	 * @param maxFecha
	 * @param minFecha
	 * @return
	 */
	public Date [] getDatesInRange( java.util.Date maxFecha, java.util.Date minFecha ){
		
		Result<Record1<Date>> result = create
				.selectDistinct(SensorDataRegistry.SENSOR_DATA_REGISTRY.FECHA)
				.from(SensorDataRegistry.SENSOR_DATA_REGISTRY)
				.where(SensorDataRegistry.SENSOR_DATA_REGISTRY.FECHA.greaterOrEqual(new Date(minFecha.getTime())))
				.and(SensorDataRegistry.SENSOR_DATA_REGISTRY.FECHA.lessOrEqual(new Date(maxFecha.getTime())))
				.fetch();
		
		Date dates [] = new Date[result.size()];
		
		for (int i = 0; i < result.size(); i++) {
			
			dates[i] = result.get(i).value1();
			
		}
		
		return dates;
		
	}
	
	/**
	 * 
	 * @param dates
	 * @param variable
	 * @return
	 */
	public String [] getAvgPerDate( Date [] dates, String variable ){
		
		String avgPerDate [] = new String[dates.length];
		
		for (int i = 0; i < dates.length; i++) {
			
			Result<Record1<BigDecimal>> result = create
					.select(SensorDataRegistry.SENSOR_DATA_REGISTRY.DATO.avg())
					.from(SensorDataRegistry.SENSOR_DATA_REGISTRY)
					.where(SensorDataRegistry.SENSOR_DATA_REGISTRY.FECHA.equal(dates[i]))
					.and(SensorDataRegistry.SENSOR_DATA_REGISTRY.VARIABLE.equal(variable))
					.fetch();
			
			avgPerDate[i] = result.get(0).value1().toString();
			
		}
		
		return avgPerDate;
		
	}
	
	/**
	 * CONNECTION METHODS
	 */

	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws IOException {

		PropsReader pr = new PropsReader();
		if( conn == null ){
			
			String db_host = pr.readProperty("db_host");
			String db_name = pr.readProperty("db_name");
			String db_user = pr.readProperty("db_user");
			String db_password = pr.readProperty("db_password");
			
			String url = "jdbc:mysql://" + db_host + ":3306/" + db_name;
			
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = (Connection) DriverManager.getConnection(url, db_user, db_password);
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | SQLException e) {
				Logger.getLogger(DataController.class.getName()).log(Level.SEVERE,
	                    "It was not possible to instance DB Driver", e);
			}
		}
		
		return conn;
	}
}
