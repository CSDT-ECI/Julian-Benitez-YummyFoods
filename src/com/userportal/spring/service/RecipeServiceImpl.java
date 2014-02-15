package com.userportal.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.userportal.spring.dao.RecipeDAO;
import com.userportal.spring.form.Recipe;
import com.userportal.spring.form.User;

@Service
public class RecipeServiceImpl implements RecipeService 
{
	@Autowired
	private RecipeDAO recipeDAO;
	
	@Transactional
	@Override
	public void add(Recipe recipe) 
	{
		recipeDAO.add(recipe);
	}

	@Transactional
	@Override
	public List<Recipe> list(User user) 
	{
		return recipeDAO.list(user);
	}

	@Override
	@Transactional
	public List<Recipe> getAllRecipe() 
	{
		return recipeDAO.getAllRecipe();
	}

}
