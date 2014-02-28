package com.zimmer.authentication.rest;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.zimmer.db.AbstractUserDao;

@SuppressWarnings("restriction")
@Component
@Path("/authentication")
public class AuthenticationResource {
	
	@Resource
	@Qualifier("userDao")
	AbstractUserDao userDao;
	
	@GET
	@Produces("text/plain")
	public String sayHello(){
		try {
			return userDao.sayHello();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
