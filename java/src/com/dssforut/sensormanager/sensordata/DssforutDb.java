/**
 * This class is generated by jOOQ
 */
package com.dssforut.sensormanager.sensordata;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.1.0" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DssforutDb extends org.jooq.impl.SchemaImpl {

	private static final long serialVersionUID = -908663081;

	/**
	 * The singleton instance of <code>dssforut_db</code>
	 */
	public static final DssforutDb DSSFORUT_DB = new DssforutDb();

	/**
	 * No further instances allowed
	 */
	private DssforutDb() {
		super("dssforut_db");
	}

	@Override
	public final java.util.List<org.jooq.Table<?>> getTables() {
		java.util.List result = new java.util.ArrayList();
		result.addAll(getTables0());
		return result;
	}

	private final java.util.List<org.jooq.Table<?>> getTables0() {
		return java.util.Arrays.<org.jooq.Table<?>>asList(
			com.dssforut.sensormanager.sensordata.tables.SensorDataRegistry.SENSOR_DATA_REGISTRY);
	}
}
