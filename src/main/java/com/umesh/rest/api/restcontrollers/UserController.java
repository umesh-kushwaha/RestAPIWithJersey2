package com.umesh.rest.api.restcontrollers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.umesh.rest.api.entity.User;
import com.umesh.rest.api.service.UserService;

@Component
@Path("/user")
public class UserController {

	private Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	private UserService mUserService;
	
	@GET
	@Path("/allusers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers() {
		
		List<User> userList = mUserService.getAllUsers();
		if(userList == null) {
	        String message= "{\"message\":\"No record exist\"}";
	        return Response.ok().entity(message).build();
	        
		}
		return Response.ok(userList).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserById(@PathParam("id") long id) {
		
		logger.info("user id is : " + id );
		User user = mUserService.getUserById(id);

		if(user == null) {
	        String message= "{\"message\":\"User record does not exist\"}";
	        return Response.ok().entity(message).build();
	        
		}
		return Response.ok(user).build();
	}
	
	@POST
	@Path("/adduser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addUser(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName,
			@FormParam("email") String email, @FormParam("password") String password, @FormParam("mobileNo") String mobileNo) {
		   User user = new User();
		   user.setFirstName(firstName);
		   user.setLastName(lastName);
		   user.setEmail(email);
		   user.setPassword(password);
		   user.setMobileNo(mobileNo);
		boolean isAdded = mUserService.addUser(user);
        if (!isAdded) {
		   logger.info("User already exits.");
	       return Response.status(Status.CONFLICT).build();
        }
        String message= "{\"message\":\"User added successfully\"}";
        		
        		
        return Response.ok().entity(message).build();
	}
	
	@POST
	@Path("/adduser/json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(User user) {
		 
		boolean isAdded = mUserService.addUser(user);
        if (!isAdded) {
		   logger.info("User already exits.");
	       return Response.status(Status.CONFLICT).build();
        }
        String message= "{\"message\":\"User added successfully\"}";
        		
        		
        return Response.ok().entity(message).build();
	}
	
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	public Response updateUser(User user) {
		mUserService.updateUser(user);
		String message= "{\"message\":\"User details updated successfully\"}";
		return Response.ok().entity(message).build();
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)		
	public Response deleteArticle(@PathParam("id") long id) {
		   logger.info("User Id: " + id);

		mUserService.deleteUser(id);
		String message= "{\"message\":\"User deleted successfully\"}";
		return Response.ok().entity(message).build();
	}	
}
