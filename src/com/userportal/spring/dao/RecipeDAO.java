package com.userportal.spring.dao;

import java.util.List;

import com.userportal.spring.form.Recipe;
import com.userportal.spring.form.User;

public interface RecipeDAO 
{
	static final int limitResultsPerPage = 2;
	public void add(Recipe recipe);
	public List<Recipe> list(User user);
	public List<Recipe> getAllRecipe();
	public List<Recipe> getRecipeForPagination(int page);
}
