package com.umesh.rest.api.dao;

import java.util.List;

import com.umesh.rest.api.entity.User;

public interface  IUserDAO {

	List<User> getAllUsers();
	void addUser(User user);
	void updateUser(User user);
	void deleteUser(long id);
	User getUserById(long id);
	boolean isUserExist(User user);
}
