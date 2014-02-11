package com.userportal.spring.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.userportal.spring.form.Recipe;
import com.userportal.spring.form.User;
@Repository
public class RecipeDAOImpl implements RecipeDAO 
{
	@Autowired
	SessionFactory sessionFactory;
	@Override
	public void add(Recipe recipe)
	{
		sessionFactory.getCurrentSession().save(recipe);
	}

	@Override
	public List<Recipe> list(User user) 
	{
		return sessionFactory.getCurrentSession().createQuery("from Recipe where userId="+user.getUserId()).list();
	}

}
