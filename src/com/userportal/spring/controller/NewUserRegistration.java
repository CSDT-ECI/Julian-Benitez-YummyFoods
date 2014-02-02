package com.userportal.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.userportal.spring.form.Login;
import com.userportal.spring.form.User;
import com.userportal.spring.service.UserService;

@Controller
public class NewUserRegistration 
{
	@Autowired
	private UserService userService;	

	@RequestMapping(value="/newUser")
	public String newUser(@ModelAttribute("user")User user,Model model)
	{
		model.addAttribute("user", new User());
		return "NewUser";
	}
	
	@RequestMapping(value="/newUserAdd",method=RequestMethod.POST)
	public String addUser(User user,Login login,BindingResult result,Model model,@RequestParam String userPassword)
	{
		user.setLogin(login);
		userService.save(user);
		return "home";
	}
}
