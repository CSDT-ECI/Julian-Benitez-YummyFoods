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
		System.out.println("Entry point");
		HttpSession session =request.getSession();
		List<Recipe> recipeList=null;
		if(session.getAttribute("sessionList")==null)
		{	
			System.out.println("No session");
			
			recipeList=recipeService.getAllRecipe();
			List<Recipe> sessionRecipeList=new ArrayList<Recipe>();
			
			sessionRecipeList.add(recipeList.get(0));
			sessionRecipeList.add(recipeList.get(1));
			sessionRecipeList.add(recipeList.get(2));
			session.setAttribute("sessionList", sessionRecipeList);
			
		}
		session.setAttribute("sessionFullList", recipeList);
		model.addAttribute("login", new Login());
		return "main";
	}
	

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@ModelAttribute("login")@Valid Login login,BindingResult result, Model model,HttpServletRequest request)
	{
		System.out.println("Coming to controller");
		System.out.println(login.getUserId());
		System.out.println(login.getUserPassword());
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
					return "home";
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
	
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request)
	{
		System.out.println(request.getSession().getAttribute("sessionValue"));
		request.getSession().setAttribute("sessionValue", null);
		request.getSession().invalidate();
		return "redirect:index";
		
	}
	
}
