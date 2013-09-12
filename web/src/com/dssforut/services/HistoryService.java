package com.dssforut.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dssforut.datacontroller.DataController;
import com.dssforut.util.ServiceParamsValidations;

@Path("/history")
public class HistoryService {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getHistory( @QueryParam("min") String minDate,
			@QueryParam("max") String maxDate,
			@QueryParam("variable") String variable) throws Exception{
		
		// Obtain dates is the proper format
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date minDateObj = df.parse(minDate);
		Date maxDateObj = df.parse(maxDate);
		
		//Run validations
		ServiceParamsValidations.historyServiceValidations(minDateObj, maxDateObj, variable);
		
		//Execute logic
		DataController dm = new DataController();
		HistoryData hd = dm.selectDataWithFechaAndVariable(maxDateObj, minDateObj, variable);
		
		//Manage and return response
		String jsonResp = returnHistoryResultJson(hd);
		return jsonResp;
	}

	/**
	 * Turn history response into a Json Object
	 * @param resp
	 * @return
	 */
	private String returnHistoryResultJson(HistoryData resp) {
		JSONObject objResponse = new JSONObject();
		JSONArray arrayData = new JSONArray();
		JSONArray arrayHours = new JSONArray();
		
		for (int i = 0; i < resp.getSize(); i++) {
			arrayData.put( resp.getData()[i] );
			arrayHours.put( resp.getHours()[i] );
		}
		
		objResponse.put("datos", arrayData);
		objResponse.put("horas", arrayHours);
		
		return objResponse.toString();
	}
}
