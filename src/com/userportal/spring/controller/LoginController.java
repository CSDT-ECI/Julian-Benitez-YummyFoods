package com.userportal.spring.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.userportal.spring.form.Login;
import com.userportal.spring.service.LoginService;

@Controller 
public class LoginController 
{
	@Autowired
	private LoginService loginService;
	
	
	@RequestMapping(value="/login")
	public String login(Map<String, Object> map)
	{
		//map.put("home", new Login());
		//map.put("loginList", loginService.login());
		return "home";
	}
}
