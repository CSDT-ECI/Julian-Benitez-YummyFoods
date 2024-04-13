package com.yummyfoods.spring.service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MediaType;

@Service
public class EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Value("${email.api.url}")
    private String EMAIL_API_URL;

    @Value("${email.api.key:#{null}")
    private String EMAIL_API_KEY;

    @Value("${email.api.username}")
    private String EMAIL_API_USERNAME;

    public EmailService() {
    }

    public ClientResponse sendEmail(String to, String subject, String body, String from) {
        try {
            Client client = Client.create();
            client.addFilter(new HTTPBasicAuthFilter(EMAIL_API_USERNAME, EMAIL_API_KEY));
            WebResource webResource = client.resource(EMAIL_API_URL);
            MultivaluedMapImpl formData = new MultivaluedMapImpl();
            formData.add("to", to);
            formData.add("subject", subject);
            formData.add("text", body);
            formData.add("from", from);
            LOGGER.info(String.format("Sending email to %s with subject %s", to, subject));
            return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);
        } catch (Exception e) {
            LOGGER.error("Error sending email", e);
            // skip exception handling for now
            return null;
        }
    }
}