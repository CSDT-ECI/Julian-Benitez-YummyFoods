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

	@Override
	@Transactional
	public List<Recipe> getRecipeForPagination(int page) 
	{
		return recipeDAO.getRecipeForPagination(page);	
	}

	@Override
	@Transactional
	public List<Recipe> getFeaturedList() 
	{
		return recipeDAO.getFeaturedList();
	}

	@Override
	@Transactional
	public List<Recipe> getRecipeForPaginationByUserId(int page, String userId) 
	{
		return recipeDAO.getRecipeForPaginationByUserId(page, userId);
	}

	@Override
	@Transactional
	public List<Recipe> getRecipeByName(String recipeName,int page) 
	{
		return recipeDAO.getRecipeByName(recipeName,page);
	}
	
	@Override
	@Transactional
	public Recipe getRecipeById(int recipeId)
	{
		return recipeDAO.getRecipeById(recipeId);
	}

	@Override
	@Transactional
	public void update(Recipe recipe) 
	{
		recipeDAO.update(recipe);
		
	}

	@Override
	@Transactional
	public void delete(int recipeId) 
	{
		recipeDAO.delete(recipeId);
		
	}

}
