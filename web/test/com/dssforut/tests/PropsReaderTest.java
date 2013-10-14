package com.dssforut.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dssforut.util.PropsReader;

public class PropsReaderTest {

	@Test
	public void propsReaderDbName() {
		
		PropsReader pr = new PropsReader();
		String bdName = pr.readProperty("db_name");
		assertEquals("dssforut_db", bdName);
		
	}
	
	@Test
	public void propsReaderDbUser() {
		
		PropsReader pr = new PropsReader();
		String bdName = pr.readProperty("db_user");
		assertEquals("dssforut_root", bdName);
		
	}

}
