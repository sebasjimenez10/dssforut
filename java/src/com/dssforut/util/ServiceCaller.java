package com.dssforut.util;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class ServiceCaller {
	
	private static final String HOST = "http://localhost:8080/";
	//private static final String HOST = "http://ec2-54-200-166-101.us-west-2.compute.amazonaws.com:8080/";

	public static void postDataOnServer( String data ){
		try {

            Client client = Client.create();
            WebResource webResource = client.resource( HOST + "resources/realtime" );
            ClientResponse response = webResource.type("text/plain").post(ClientResponse.class, data);

            if (response.getStatus() != 201) {
                System.out.println( "Failed : HTTP error code : " + response.getStatus() );
            }

            System.out.println("Output from Server .... \n");
            String output = response.getEntity(String.class);
            System.out.println(output);

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
}
