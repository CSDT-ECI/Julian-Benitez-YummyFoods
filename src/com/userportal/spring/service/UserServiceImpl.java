package com.userportal.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.userportal.spring.dao.UserDAO;
import com.userportal.spring.form.User;

@Service
public class UserServiceImpl implements UserService 
{
	@Autowired
	private UserDAO userDAO;
	
	@Override
	@Transactional
	public List<User> list() 
	{
		return userDAO.listUser();
	}
	
	@Override
	@Transactional
	public void save(User user) 
	{
		userDAO.addUser(user);
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub

	}
	@Transactional
	public User getUserById(String userId)
	{
		return userDAO.getUserById(userId);
	}

}
