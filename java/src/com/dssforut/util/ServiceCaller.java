package com.dssforut.util;

import com.dssforut.main.LogAppender;
import com.dssforut.util.PropsReader.ConfigTarget;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ServiceCaller {
	
	private static final Client client = Client.create();
	private static final PropsReader reader = new PropsReader();
	private static final String host = reader.getConfigProperty(ConfigTarget.environment, EnvInfoEnum.host);
	
	public static void postDataOnServer(String data) {
		try {
			
			LogAppender.logDebugMessage( "Host: " + host );
			WebResource webResource = client.resource(host + "resources/realtime");
			
			ClientResponse response = webResource.type("text/plain").post(ClientResponse.class, data);

			if (response.getStatus() != 201) {
				LogAppender.logDebugMessage("Failed : HTTP error code : " + response.getStatus());
			}

			LogAppender.logDebugMessage("Output from Server .... \n");
			
			String output = response.getEntity(String.class);
			LogAppender.logDebugMessage(output);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
