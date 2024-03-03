package com.yummyfoods.spring.service;

import java.util.List;
import com.yummyfoods.spring.form.User;

public interface UserService
{
	public List<User> list();
	public void save(User user);
	public void delete(User user);
	public User getUserById(String userId);
	public void update(User user);
}
