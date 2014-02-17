package com.userportal.spring.controller;

import java.awt.List;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.Validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.userportal.spring.form.Login;
import com.userportal.spring.form.User;
import com.userportal.spring.service.UserService;
import com.userportal.spring.validator.NewUserValidator;
import com.userportal.utility.Email;

@Controller
public class NewUserRegistration 
{
	@Autowired
	private UserService userService;	
	
	@Autowired
	private NewUserValidator newUserValidator;
	
	
	@InitBinder("user")
	public void initBinder(WebDataBinder binder)
	{
		binder.setValidator(newUserValidator);
	}
	
	@RequestMapping(value="/newUser")
	public String newUser(Model model)
	{
		model.addAttribute("user", new User());
		return "NewUser";
	}
	
	@RequestMapping(value="/newUserAdd", method=RequestMethod.POST)
	public String addUser(@ModelAttribute("user")@Valid User user,BindingResult result,Login login, Model model,HttpSession session)
	{
		if(result.hasErrors())
		{
			if(result.hasFieldErrors("userId"))
			{
				model.addAttribute("userIdError", result.getFieldError("userId").getDefaultMessage());
			}
			 if(result.hasFieldErrors("userName"))
			{
				model.addAttribute("userNameError", result.getFieldError("userName").getDefaultMessage());
			}
			 if(result.hasFieldErrors("userPassword"))
			{
				model.addAttribute("userPasswordError", result.getFieldError("userPassword").getDefaultMessage());
			}
			 if(result.hasFieldErrors("userConfirmPassword"))
			{
				model.addAttribute("userConfirmPasswordError", result.getFieldError("userConfirmPassword").getDefaultMessage());
			}
			 if(result.hasFieldErrors("userEmailId"))
			 {
				 model.addAttribute("userEmailIdError", result.getFieldError("userEmailId").getDefaultMessage());
			 }
			return "NewUser";
		}
		if(user.getUserConfirmPassword().equals(login.getUserPassword()))
		{

			user.setLogin(login);
			userService.save(user);
			Email.sendEmail(user.getUserEmailId(), "Registration", "Hi,\n\nCongratulations "+user.getUserId()+" for registering!!\n\nRegards\nYummyFoods", "Support<support@userportal.mailgun.org>");
			session.setAttribute("sessionValue", user.getUserId());
			return "UserHome";
		}
		else
		{
			model.addAttribute("userPasswordMismatchError", "Password mismatch");
		}
		return "NewUser";
	}
}
