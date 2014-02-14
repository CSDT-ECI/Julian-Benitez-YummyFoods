package com.userportal.spring.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	
	@RequestMapping(value="/recipe")
	public String recipe(Model model)
	{
		model.addAttribute("recipe",new Recipe());
		return "RecipeForm";
		
	}
	
	@RequestMapping(value="/viewRecipe")
	public String viewRecipe(Model model,HttpSession session)
	{
		User user=userService.getUserById("pulkit");
		List<Recipe> list=recipeService.list(user);
		session.setAttribute("sessionList", list);
		model.addAttribute("recipeList", list);
		
		return "ViewRecipe";
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
	
	
 	
}
