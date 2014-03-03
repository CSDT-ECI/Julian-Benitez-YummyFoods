package com.userportal.spring.dao;

import java.util.List;

import com.userportal.spring.form.Recipe;
import com.userportal.spring.form.User;

public interface RecipeDAO 
{
	static final int limitResultsPerPage = 6;
	public void add(Recipe recipe);
	public List<Recipe> list(User user);
	public List<Recipe> getAllRecipe();
	public List<Recipe> getRecipeForPagination(int page);
	public List<Recipe> getFeaturedList();
	public List<Recipe> getRecipeForPaginationByUserId(int page, String userId);
	public List<Recipe> getRecipeByName(String recipeName,int page);
	public Recipe getRecipeById(int recipeId);
	public void update(Recipe recipe);
	public void delete(int recipeId);
}
