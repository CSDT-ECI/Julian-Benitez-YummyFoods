package com.userportal.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;




import com.userportal.spring.form.Login;

@Repository
public class LoginDAOImpl implements LoginDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<Login> listLogin() 
	{
		Session session=(Session) sessionFactory.getCurrentSession();
		session.beginTransaction();
		List loginList=session.createQuery("from login").list();
		System.out.println("Login list:"+loginList);
		session.getTransaction().commit();
		return loginList;

	
	}

}
