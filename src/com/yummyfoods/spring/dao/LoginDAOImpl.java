package com.yummyfoods.spring.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.yummyfoods.spring.form.Login;

@Repository
public class LoginDAOImpl implements LoginDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@SuppressWarnings("unchecked")
	@Override
	public List<Login> listLogin() 
	{
		return sessionFactory.getCurrentSession().createQuery("from Login").list();
		
	
	}
	@Override
	public Login getParticularUser(String userId)
	{
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Login login= null;
		try 
		{	
			login = (Login) session.createQuery("from Login where userId='"+userId+"'").list().get(0);
		}
		catch (HibernateException e) 
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return login;
	}
	@Override
	public void updateLoginDetails(String userId, String userPassword) 
	{
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Login login= null;
		try 
		{	
			login = (Login) session.createQuery("from Login where userId="+userId+"").list();
			login.setUserPassword(userPassword);
			session.save(login);
		}
		catch (HibernateException e) 
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();	
	}
	@Override
	public void save(Login login)
	{
		sessionFactory.getCurrentSession().save(login);
	}

}
