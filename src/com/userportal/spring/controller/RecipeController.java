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
	
	@RequestMapping(value="/user/recipe")
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
	
	@RequestMapping(value="/recipe")
	public String viewRecipeById(Model model,HttpServletRequest request)
	{
		Integer recipeId=Integer.parseInt(request.getParameter("recipeId"));
		List<Recipe>sessionList=(List<Recipe>) request.getSession().getAttribute("sessionList");
		for(int i=0;i<sessionList.size();i++)
		{
			if(sessionList.get(i).getRecipeId().equals(recipeId))
			{
				List<Recipe> recipeDetails= new ArrayList<Recipe>();
				recipeDetails.add(sessionList.get(i));
				model.addAttribute("recipeDetails", recipeDetails);
				return "ViewRecipe";
			}
		}
		return "main";
	}
	
	@RequestMapping(value="allRecipe")
	public String allRecipe(@RequestParam(value = "page", required = false) Integer page,Model model,HttpServletRequest request)
	{
		int page1=0,page2=0,page3=0;
		List<Recipe> recipeList=recipeService.getAllRecipe();
		request.getSession().setAttribute("sessionList", recipeList);
		model.addAttribute("recipeList", recipeService.getRecipeForPagination(page));
	
		if(page==-1)
		{
			page1=(Integer) request.getSession().getAttribute("PageValue1");
			page2=(Integer) request.getSession().getAttribute("PageValue2");
			page3=(Integer) request.getSession().getAttribute("PageValue3");
			System.out.println("Page1:"+page1);
			System.out.println("Page2:"+page2);
			System.out.println("Page3:"+page3);
			page1=page1-1;
			page2=page2-1;
			page3=page3-1;
			request.getSession().setAttribute("PageValue1", page1);
			request.getSession().setAttribute("PageValue2", page2);
			request.getSession().setAttribute("PageValue3", page3);
			
		}
		else if(page<-1)
		{
			page1=1;
			page2=2;
			page3=3;
		}
		else if(page>=0)
		{
			page1=page+1;
			page2=page1+1;
			page3=page2+1;
			request.getSession().setAttribute("PageValue1", page1);
			request.getSession().setAttribute("PageValue2", page2);
			request.getSession().setAttribute("PageValue3", page3);
			
		}
			model.addAttribute("pageValue1",page1);
			model.addAttribute("pageValue2",page2);
			model.addAttribute("pageValue3",page3);
		//model.addAttribute("recipeList", recipeList);
		return "AllRecipe";
	}
	
	
	
 	
}
