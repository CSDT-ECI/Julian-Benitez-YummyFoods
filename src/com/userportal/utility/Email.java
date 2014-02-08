package com.userportal.utility;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class Email 
{
	public static ClientResponse sendEmail(String to,String subject,String body,String from)
	{
		try{
		Client client=Client.create();
		client.addFilter(new HTTPBasicAuthFilter("api", "key-41ky9rbh16hgacc3r9cr5vadgq9vnc43"));
		WebResource webResource=client.resource("https://api.mailgun.net/v2/userportal.mailgun.org/messages");
		MultivaluedMapImpl formData=new MultivaluedMapImpl();
		formData.add("to", to);
		formData.add("subject",subject);
		formData.add("text", body);
		formData.add("from",from);
		System.out.println("mail success");
		return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);
		
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
