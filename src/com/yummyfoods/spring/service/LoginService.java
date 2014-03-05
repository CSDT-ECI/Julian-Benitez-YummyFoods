package com.yummyfoods.spring.service;

import java.util.List;

import com.yummyfoods.spring.form.Login;

public interface LoginService 
{
	public List<Login> login();
	public Login getParticularUser(String userId);
	public void update(String userId,String userPassword);
	public void save(Login login);
}
