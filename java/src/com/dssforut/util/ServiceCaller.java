package com.dssforut.util;

import com.dssforut.util.PropsReader.ConfigTarget;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ServiceCaller {
	
	public static void postDataOnServer(String data) {
		try {

			PropsReader reader = new PropsReader();
			String host = reader.getConfigProperty(ConfigTarget.environment,
					EnvInfoEnum.host);

			System.out.println( "Host: " + host );
			
			Client client = Client.create();
			WebResource webResource = client.resource(host
					+ "resources/realtime");
			ClientResponse response = webResource.type("text/plain").post(
					ClientResponse.class, data);

			if (response.getStatus() != 201) {
				System.out.println("Failed : HTTP error code : "
						+ response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
