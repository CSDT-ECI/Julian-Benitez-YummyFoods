package com.userportal.spring.controller;

import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;


import com.userportal.spring.form.Recipe;
import com.userportal.spring.form.User;
import com.userportal.spring.service.RecipeService;
import com.userportal.spring.service.UserService;
import com.userportal.spring.service.UserServiceImpl;

@Controller
public class RecipeController 
{
	@Autowired
	RecipeService recipeService;

	@Autowired
	private UserService userService;
	
	/*@InitBinder(value="recipe")
	protected void initBinder(HttpServletRequest request,
	        ServletRequestDataBinder binder) throws ServletException {
	    binder.registerCustomEditor(byte[].class,
	            new ByteArrayMultipartFileEditor());
	}
	*/
	@RequestMapping(value="/recipe")
	public String recipe(Model model)
	{
		model.addAttribute("recipe",new Recipe());
		return "RecipeForm";
		
	}
	@RequestMapping(value="/addRecipe", method=RequestMethod.POST)
	public String addRecipe(@ModelAttribute("recipe")Recipe recipe,User user,@RequestParam("file") MultipartFile file)
	{
		try 
        {
        	Blob blob = Hibernate.createBlob(file.getBytes());
        	recipe.setFileName(file.getOriginalFilename());
            recipe.setPic(blob);
            recipe.setContentType(file.getContentType());
        	recipe.setPic(blob);
        	user=userService.getUserById("pulkit");
        	recipe.setUser(user);
        	recipeService.add(recipe);
        } 
        
        catch (IOException e) 
        {
            e.printStackTrace();
        }
         
		return "home";
	}
	
	
 
	@RequestMapping(value="/viewRecipe")
	public String viewRecipe()
	{
		return null;
	}
	
}
