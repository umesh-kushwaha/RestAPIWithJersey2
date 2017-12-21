package com.umesh.rest.api.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureParameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.umesh.rest.api.entity.User;
@Transactional
@Repository
public class UserDAO implements IUserDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();	
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		String query = "SELECT new com.umesh.rest.api.entity.User(u.userId,u.firstName, u.lastName,u.email,u.mobileNo) FROM User u";
		return (List<User>)entityManager.createQuery(query).getResultList();
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		entityManager.persist(user);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		User user1 = getUserById(user.getUserId());
		
		if(user.getFirstName() != null)
		user1.setFirstName(user.getFirstName());
		
		if(user.getLastName() != null)
		user1.setLastName(user.getLastName());
		
		if(user.getMobileNo() != null)
		user1.setMobileNo(user.getMobileNo());
		
		if(user.getEmail() != null)
		user1.setEmail(user.getEmail());
		entityManager.flush();
	}

	@Override
	public void deleteUser(long id) {
		// TODO Auto-generated method stub
		entityManager.remove(getUserById(id));
	}

	@Override
	public User getUserById(long id) {
		// TODO Auto-generated method stub
		return entityManager.find(User.class, id);
	}

	@Override
	public boolean isUserExist(User user) {
		// TODO Auto-generated method stub
		String hql = "Select  u FROM User  u WHERE u.email = ? and u.mobileNo = ?";

		int count = entityManager.createQuery(hql).setParameter(1, user.getEmail())
	              .setParameter(2, user.getMobileNo()).getResultList().size();
		return count > 0 ? true : false;
	}
	
	private boolean matchPassword(String rawPassword, String storedPassword) {
		return passwordEncoder.matches(rawPassword, storedPassword);
	}

}
