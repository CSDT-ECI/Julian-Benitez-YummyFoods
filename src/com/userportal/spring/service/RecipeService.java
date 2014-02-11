package com.userportal.spring.service;

import java.util.List;

import com.userportal.spring.form.Recipe;
import com.userportal.spring.form.User;

public interface RecipeService 
{
	public void add(Recipe recipe);
	public List<Recipe> list(User user);
}
