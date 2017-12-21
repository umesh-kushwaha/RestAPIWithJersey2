package com.umesh.rest.api.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chainfilter)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:8585");
        httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
    	httpResponse.setHeader("Access-Control-Allow-Headers", "X-Auth-Token, Content-Type");
        httpResponse.setHeader("Access-Control-Expose-Headers", "custom-header1, custom-header2");
    	httpResponse.setHeader("Access-Control-Allow-Credentials", "false");
        httpResponse.setHeader("Access-Control-Max-Age", "4800");
        
        if (request instanceof HttpServletRequest) {
        	   HttpServletRequest httpRequest = (HttpServletRequest) request;

        	   String authorization = httpRequest.getHeader("Authorization");


        	   boolean isAuthenticated = AuthService.authenticate(authorization);

        	   if (isAuthenticated) {
        		   chainfilter.doFilter(request, response);
        	   }else {
        		  // Response.status(Status.UNAUTHORIZED).build();
        		     String errorMessage = "{\"err_message\":\"Unauthorized request\"}";
        		     httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        		     httpResponse.getOutputStream().write(errorMessage.getBytes());
        	   }
        }
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	

}
