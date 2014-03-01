package com.userportal.spring.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;


import com.userportal.spring.form.Login;
import com.userportal.spring.form.Recipe;
import com.userportal.spring.service.LoginService;
import com.userportal.spring.service.RecipeService;
import com.userportal.spring.validator.LoginValidator;

@Controller
public class LoginController
{
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private LoginValidator loginValidator; 
	
	@Autowired
	private RecipeService recipeService;
	
	@InitBinder("login")
	public void initBinder(WebDataBinder binder)
	{
		binder.setValidator(loginValidator);
	}
	
	
	@RequestMapping(value="/index")
	public String index(Model model,HttpServletRequest request)
	{
		HttpSession session =request.getSession();
		List<Recipe> recipeList=null;
		
			recipeList=recipeService.getAllRecipe();
			List<Recipe> sessionRecipeList=recipeService.getFeaturedList();
			
			session.setAttribute("sessionList", sessionRecipeList);
			
		session.setAttribute("sessionFullList", recipeList);
		model.addAttribute("login", new Login());

		return "main";
	}
	

	@RequestMapping(value="/doLogin", method=RequestMethod.POST)
	public String doLogin(@ModelAttribute("login")@Valid Login login,BindingResult result, Model model,HttpServletRequest request,HttpSession session)
	{
		session=request.getSession();
		if(result.hasErrors())
		{
			if(result.hasFieldErrors("userId"))
			{
				model.addAttribute("userIdError", result.getFieldError("userId").getDefaultMessage());
				
			}
			if(result.hasFieldErrors("userPassword"))
			{
				model.addAttribute("userPasswordError", result.getFieldError("userPassword").getDefaultMessage());
			}
			return "Login";
		}
		
		
		for(Login obj:loginService.login())
		{
			if(obj.getUserId().equals(login.getUserId()))
			{
				if(obj.getUserPassword().equals(login.getUserPassword()))
				{
					
					request.getSession().setAttribute("sessionValue", login.getUserId());
					if(session.getAttribute("recipeIdForRating")!=null)
					{
						return "redirect:recipe";
						
					}
					else
					{
						return "UserHome";
					}
					
				}
				else
				{
					model.addAttribute("userPasswordError", "Wrong Password");
					
					return "Login";
				}
				
			}
			
		}
		model.addAttribute("userIdError", "Wrong User Id");
		model.addAttribute("userPasswordError", "Wrong Password");
		return "Login";
	}
	
	@RequestMapping(value="/doLogout")
	public String doLogout(HttpServletRequest request)
	{
		request.getSession().setAttribute("sessionValue", null);
		request.getSession().invalidate();
		return "redirect:index";
		
	}
	@RequestMapping(value="/login")
	public String login(Model model,HttpServletRequest request)
	{
		HttpSession session =request.getSession();
		List<Recipe> recipeList=null;
		List<Recipe> sessionRecipeList=recipeService.getFeaturedList();
		recipeList=recipeService.getAllRecipe();
		
		session.setAttribute("sessionList", sessionRecipeList);
			session.setAttribute("sessionFullList", recipeList);
		
		model.addAttribute("login", new Login());
		return "Login";
	}
	
	@RequestMapping(value="loginForRating")
	public String loginForRecipe(@RequestParam(value="recipeId")String recipeId,HttpServletRequest request,Model model)
	{
		System.out.println("recipe id for session:"+recipeId);
		HttpSession session=request.getSession();
		System.out.println("2. Sesison deos not exist, we have to set value");
		session.setAttribute("recipeIdForRating", recipeId);
		
		model.addAttribute("login",new Login());
		return "Login";
	}
	
		
}
