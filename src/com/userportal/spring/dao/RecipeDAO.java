package com.userportal.spring.dao;

import java.util.List;

import com.userportal.spring.form.Recipe;
import com.userportal.spring.form.User;

public interface RecipeDAO 
{
	public void add(Recipe recipe);
	public List<Recipe> list(User user);
}
