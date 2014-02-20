package com.userportal.spring.dao;

import java.util.List;

import com.userportal.spring.form.User;

public interface UserDAO
{
	public List<User> listUser();
	public void addUser(User user);
	public void deleteUser(User user);
	public User getUserById(String userId);
	public void update(User user);
}
