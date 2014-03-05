package com.yummyfoods.spring.service;

import java.util.List;

import com.yummyfoods.spring.form.Recipe;
import com.yummyfoods.spring.form.User;

public interface RecipeService 
{
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
