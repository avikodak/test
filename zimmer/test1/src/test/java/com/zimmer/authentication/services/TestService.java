package com.zimmer.authentication.services;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.JerseyTest;



public class TestService extends JerseyTest{
	
	public TestService(){
		super("com.zimmer.authentication.rest");
	}
	
	@Test
	public void testFetchPerson() {     
        WebResource webResource = resource();       
        String person = webResource.path("person")
            .accept(MediaType.TEXT_PLAIN)
            .get(String.class);
            
        assertEquals("hello world", person);
    }
}
