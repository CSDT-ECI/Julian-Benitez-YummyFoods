package com.userportal.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.userportal.spring.dao.LoginDAO;
import com.userportal.spring.form.Login;

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
	
}
