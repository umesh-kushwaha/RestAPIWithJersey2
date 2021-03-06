package com.umesh.rest.api.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.umesh.rest.api.restcontrollers.UserController;
import com.umesh.rest.api.security.BasicAuthFilter;

@Component
@ApplicationPath("/v1/api")
public class JerseyConfig extends ResourceConfig{
	
	public JerseyConfig() {
		register(BasicAuthFilter.class);
		register(UserController.class);
		register(MultiPartFeature.class);
	}

}
