package com.dssforut.util;

import java.util.Date;

public class ServiceParamsValidations {

	public static void historyServiceValidations(Date minDate,
			Date maxDate, String variable) throws Exception {

		/**
		 * HACER VALIDACIONES
		 *  - SI LA VARIABLE SI ESTA DENTRO DE LAS VARIABLES QUE SON
		 */
		
		// Validate every param is not null
		if (minDate == null || maxDate == null || variable == null) {
			throw new Exception("Null values in request");
		}

		// min date must be smaller than max date
		if (minDate.compareTo(maxDate) > 0) {
			throw new Exception("min date is greater than max date");
		}
	}

}
