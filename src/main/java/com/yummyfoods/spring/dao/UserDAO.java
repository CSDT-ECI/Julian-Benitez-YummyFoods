package com.yummyfoods.spring.dao;

import java.util.List;

import com.yummyfoods.spring.form.User;

public interface UserDAO
{
	public List<User> listUser();
	public void addUser(User user);
	public void deleteUser(User user);
	public User getUserById(String userId);
	public void update(User user);
}
