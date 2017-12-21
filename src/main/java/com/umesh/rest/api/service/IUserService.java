package com.umesh.rest.api.service;

import java.util.List;

import com.umesh.rest.api.entity.User;

public interface IUserService {
	List<User> getAllUsers();
	boolean addUser(User user);
	void updateUser(User user);
	void deleteUser(long id);
	User getUserById(long id);
}
