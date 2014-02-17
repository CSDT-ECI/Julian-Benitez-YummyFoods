package com.userportal.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.userportal.spring.form.Login;
import com.userportal.spring.form.User;
import com.userportal.spring.service.LoginService;
import com.userportal.spring.service.UserService;
import com.userportal.utility.Email;

@Controller
public class ResetPassword 
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/forgotPassword")
	public String forgotPassword(Model model)
	{
		model.addAttribute("user", new User());
		return "ForgotPassword";
	}
	
	@RequestMapping(value="/resetPassword",method=RequestMethod.POST)
	public String resetPassword(@ModelAttribute("user")User user,Model model)
	{
		for(User tempUser:userService.list())
		{
			if(tempUser.getUserEmailId().equals(user.getUserEmailId()))
			{
				for(Login login:loginService.login())
				{
					if(login.getUserId().equals(tempUser.getUserId()))
					{
						Email.sendEmail(user.getUserEmailId(), "Password Details", "Hi user,\n\nYour password details are Password:"+login.getUserPassword()+"\n\nRegards\nYummyFoods Admin", "Admin<admin@userportal.mailgun.org>");
						
					}
				}
				model.addAttribute("login",new Login());
				model.addAttribute("ResetPassword", "Password detail have been sent to "+user.getUserEmailId());
			}
		}
		
		return "Login";
	}
}
