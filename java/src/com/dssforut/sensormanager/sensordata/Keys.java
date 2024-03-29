/**
 * This class is generated by jOOQ
 */
package com.dssforut.sensormanager.sensordata;

/**
 * This class is generated by jOOQ.
 *
 * A class modelling foreign key relationships between tables of the <code>dssforut_db</code> 
 * schema
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.1.0" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

	// -------------------------------------------------------------------------
	// IDENTITY definitions
	// -------------------------------------------------------------------------

	public static final org.jooq.Identity<com.dssforut.sensormanager.sensordata.tables.records.SensorDataRegistryRecord, java.lang.Integer> IDENTITY_SENSOR_DATA_REGISTRY = Identities0.IDENTITY_SENSOR_DATA_REGISTRY;

	// -------------------------------------------------------------------------
	// UNIQUE and PRIMARY KEY definitions
	// -------------------------------------------------------------------------

	public static final org.jooq.UniqueKey<com.dssforut.sensormanager.sensordata.tables.records.SensorDataRegistryRecord> KEY_SENSOR_DATA_REGISTRY_PRIMARY = UniqueKeys0.KEY_SENSOR_DATA_REGISTRY_PRIMARY;

	// -------------------------------------------------------------------------
	// FOREIGN KEY definitions
	// -------------------------------------------------------------------------


	// -------------------------------------------------------------------------
	// [#1459] distribute members to avoid static initialisers > 64kb
	// -------------------------------------------------------------------------

	private static class Identities0 extends org.jooq.impl.AbstractKeys {
		public static org.jooq.Identity<com.dssforut.sensormanager.sensordata.tables.records.SensorDataRegistryRecord, java.lang.Integer> IDENTITY_SENSOR_DATA_REGISTRY = createIdentity(com.dssforut.sensormanager.sensordata.tables.SensorDataRegistry.SENSOR_DATA_REGISTRY, com.dssforut.sensormanager.sensordata.tables.SensorDataRegistry.SENSOR_DATA_REGISTRY.ID);
	}

	private static class UniqueKeys0 extends org.jooq.impl.AbstractKeys {
		public static final org.jooq.UniqueKey<com.dssforut.sensormanager.sensordata.tables.records.SensorDataRegistryRecord> KEY_SENSOR_DATA_REGISTRY_PRIMARY = createUniqueKey(com.dssforut.sensormanager.sensordata.tables.SensorDataRegistry.SENSOR_DATA_REGISTRY, com.dssforut.sensormanager.sensordata.tables.SensorDataRegistry.SENSOR_DATA_REGISTRY.ID);
	}
}
