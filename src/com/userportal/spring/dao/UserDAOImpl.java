package com.userportal.spring.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.userportal.spring.form.User;

@Repository
public class UserDAOImpl implements UserDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<User> listUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUser(User user) 
	{
		sessionFactory.getCurrentSession().save(user);	
		System.out.println("Save user done");
		
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub

	}

}
