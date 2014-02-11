package com.userportal.utility;

import javax.ws.rs.core.MediaType;


import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;


public class TestMail
{
	public static void main(String arg[])
	{
		//SendSimpleMessage();
	try {
		execute();
		//SendSimpleMessage();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public static ClientResponse SendSimpleMessage() 
	{
		try
		{
			Client client = Client.create();
		    client.addFilter(new HTTPBasicAuthFilter("api","key-41ky9rbh16hgacc3r9cr5vadgq9vnc43"));
		    WebResource webResource = client.resource("https://api.mailgun.net/v2/userportal.mailgun.org/messages");
		    MultivaluedMapImpl formData = new MultivaluedMapImpl();
		    formData.add("from", "Admin<admin@userportal.mailgun.org>");
		    formData.add("to", "pulkit.sharva@gmail.com");
		    formData.addFirst("to","pulkit_sharva@syntelinc.com");
		    formData.add("subject", "Hello");
		    formData.add("text", "Testing some Mailgun awesomeness!");
		    System.out.println("success");
		    return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static String execute() throws Exception{
		 
	    Client client = Client.create();
	    client.addFilter(new HTTPBasicAuthFilter("api","key-41ky9rbh16hgacc3r9cr5vadgq9vnc43"));
	 
	    WebResource webResource = client.resource("https://api.mailgun.net/v2/userportal.mailgun.org/mailboxes");
	    System.out.println(webResource.getProperties());
	   /* MultivaluedMapImpl formData = new MultivaluedMapImpl();
	    formData.add("mailbox", "admin@userportal.mailgun.org");
	    formData.add("password", "password");
	    ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);
	 
	    String output = response.getEntity(String.class);
	     webResource.get(ClientResponse.class);
	    System.out.println("All done");
	   
	    */
	 

	      return "SUCCESS";
	 }

}
