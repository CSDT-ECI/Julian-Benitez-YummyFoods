package com.userportal.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
		return sessionFactory.getCurrentSession().createQuery("from Recipe where userId='"+user.getUserId()+"'").list();
	}
	
	@Override
	public List<Recipe> getAllRecipe()
	{
		return sessionFactory.getCurrentSession().createQuery("from Recipe").list();
	}

	@Override
	public List<Recipe> getRecipeForPagination(int page)
	{
		Query q = sessionFactory.getCurrentSession().createQuery("from Recipe");
        q.setFirstResult(page * limitResultsPerPage); 
        q.setMaxResults(limitResultsPerPage);
        return (List<Recipe>) q.list();
	}
	
	@Override
	public List<Recipe> getRecipeForPaginationByUserId(int page,String userId)
	{
		Query q = sessionFactory.getCurrentSession().createQuery("from Recipe where userId='"+userId+"'");
        q.setFirstResult(page * limitResultsPerPage); 
        q.setMaxResults(limitResultsPerPage);
        return (List<Recipe>) q.list();
	}
	@Override
	public List<Recipe> getFeaturedList() 
	{
		Criteria criteria =sessionFactory.getCurrentSession().createCriteria(Recipe.class);
		criteria.add(Restrictions.sqlRestriction("1=1 order by rand()"));
		criteria.setMaxResults(3);
		return criteria.list();
		
	}

}
