package com.yummyfoods.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yummyfoods.spring.form.Recipe;
import com.yummyfoods.spring.form.User;
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

	@Override
	public List<Recipe> getRecipeByName(String recipeName,int page) 
	{
		Query qry = sessionFactory.getCurrentSession().createQuery("From Recipe where name like :name");
		qry.setParameter("name",'%'+recipeName+'%');
		qry.setFirstResult(page * limitResultsPerPage); 
        qry.setMaxResults(limitResultsPerPage);
		return qry.list();
	}

	@Override
	public Recipe getRecipeById(int recipeId) 
	{
		
		return (Recipe) sessionFactory.getCurrentSession().createQuery("From Recipe where recipeId="+recipeId).list().get(0);
	}

	@Override
	public void update(Recipe recipe) 
	{
		sessionFactory.getCurrentSession().update(recipe);
	}
	
	@Override
	public void delete(int recipeId)
	{
		sessionFactory.getCurrentSession().delete(getRecipeById(recipeId));
	}

}
