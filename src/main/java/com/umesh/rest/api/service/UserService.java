package com.umesh.rest.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umesh.rest.api.dao.UserDAO;
import com.umesh.rest.api.entity.User;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserDAO userDAO;
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userDAO.getAllUsers();
	}

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		if(userDAO.isUserExist(user)) {
			return false;
		}
		
		userDAO.addUser(user);
		
		return true;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userDAO.updateUser(user);
	}

	@Override
	public void deleteUser(long id) {
		// TODO Auto-generated method stub
		userDAO.deleteUser(id);
	}

	@Override
	public User getUserById(long id) {
		// TODO Auto-generated method stub
		return userDAO.getUserById(id);
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userDAO.getUserByEmail(email);
	}

	@Override
	public boolean isPasswordMatched(String rawPassword, String storedPassword) {
		// TODO Auto-generated method stub
		return userDAO.isPasswordMatched(rawPassword, storedPassword);
	}

}
