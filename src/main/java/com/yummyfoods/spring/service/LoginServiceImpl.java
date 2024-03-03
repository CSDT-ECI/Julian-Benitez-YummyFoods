package com.yummyfoods.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yummyfoods.spring.dao.LoginDAO;
import com.yummyfoods.spring.form.Login;

@Service
public class LoginServiceImpl implements LoginService 
{
	@Autowired
	private LoginDAO loginDAO;

	@Override
	@Transactional
	public List<Login> login()
	{
		return loginDAO.listLogin();
		
	}

	@Override
	@Transactional
	public Login getParticularUser(String userId) 
	{
		return loginDAO.getParticularUser(userId);
	}
	
	@Transactional
	public void update(String userId,String userPassword)
	{
		loginDAO.updateLoginDetails(userId, userPassword);
	}

	@Override
	@Transactional
	public void save(Login login) 
	{
		loginDAO.save(login);
		
	}
	
}
