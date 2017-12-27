package com.umesh.rest.api.security;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Component;


@Component
public class BasicAuthFilter implements ContainerRequestFilter{
	
	private static final String errorMessage = "{\"err_message\":\"Unauthorized request\"}";


	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// TODO Auto-generated method stub
		// Get the HTTP Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        
        boolean isAuthenticated = AuthService.authenticate(authorizationHeader);
        
        if(!isAuthenticated) {
        	requestContext.abortWith(Response.status(Status.UNAUTHORIZED).entity(errorMessage).build());
        }
	}

}
