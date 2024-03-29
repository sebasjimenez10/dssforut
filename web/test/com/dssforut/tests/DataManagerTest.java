package com.dssforut.tests;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.Assert;

import org.junit.Test;

import com.dssforut.datacontroller.DataController;

public class DataManagerTest {

	@Test
	public void getVariableDataGivenDate1() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
		
		DataController dm = new DataController();
		Date minDate = new GregorianCalendar(2013, 8, 1).getTime();
		Date maxDate = new GregorianCalendar(2013, 8, 3).getTime();
		dm.selectDataWithFechaAndVariable(maxDate, minDate, "Humedad del Suelo");
	}
	
	@Test
	public void getVariableDataGivenDate2() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
		
		DataController dm = new DataController();
		Date minDate = new GregorianCalendar(2013, 9, 1).getTime();
		Date maxDate = new GregorianCalendar(2013, 9, 1).getTime();
		dm.selectDataWithFechaAndVariable(maxDate, minDate, "Humedad");
	}
	
	@Test
	public void connNotNull() throws IOException{
		
		DataController dm = new DataController();
		Assert.assertNotNull( dm.getConnection() );
	}

}
