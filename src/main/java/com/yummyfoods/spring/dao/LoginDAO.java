package com.yummyfoods.spring.dao;

import java.util.List;
import com.yummyfoods.spring.form.Login;

public interface LoginDAO 
{
	public List<Login> listLogin();
	public Login getParticularUser(String userId);
	public void updateLoginDetails(String userId,String userPassword);
	public void save(Login login);
	
}
