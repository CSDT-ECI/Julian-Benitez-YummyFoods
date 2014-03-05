package com.yummyfoods.spring.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.yummyfoods.spring.form.User;

@Repository
public class UserDAOImpl implements UserDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUser() 
	{
		return sessionFactory.getCurrentSession().createQuery("from User").list();
	}
	
	@Override
	public void addUser(User user) 
	{
		sessionFactory.getCurrentSession().save(user);		
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub

	}
	@Override
	public User getUserById(String userId)
	{
		return (User) sessionFactory.getCurrentSession().createQuery("from User where userId='"+userId+"'").list().get(0);
	}

	@Override
	public void update(User user) 
	{
		sessionFactory.getCurrentSession().update(user);
		
	}

}
