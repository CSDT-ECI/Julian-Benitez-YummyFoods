package com.userportal.spring.controller;

import java.util.List;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.http.HttpServletRequest;
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

import com.userportal.spring.form.Login;
import com.userportal.spring.form.User;
import com.userportal.spring.service.LoginService;
import com.userportal.spring.service.UserService;
import com.userportal.spring.validator.LoginValidator;
import com.userportal.spring.validator.NewUserValidator;
import com.userportal.spring.validator.UserChangePasswordValidator;
import com.userportal.utility.Email;

@Controller
public class ResetPassword 
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserChangePasswordValidator userChangePasswordValidator;
	
	@InitBinder("user")
	public void initBinder(WebDataBinder binder)
	{
		binder.setValidator(userChangePasswordValidator);
	}
	
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
						Email.sendEmail(user.getUserEmailId(), "Password Details", "Hi "+tempUser.getUserId()+",\n\nYour password details are Password:"+login.getUserPassword()+"\n\nRegards\nYummyFoods Admin", "Admin<admin@userportal.mailgun.org>");
						
					}
				}
				model.addAttribute("login",new Login());
				model.addAttribute("ResetPassword", "Password detail have been sent to "+user.getUserEmailId()+".");
				return "Login";
				
			}
		}
		model.addAttribute("login",new Login());
		model.addAttribute("ResetPassword", "Email address doesn't exist.");
		return "Login";
	}
	
	@RequestMapping(value="/changePassword")
	public String changePassword(Model model)
	{
		model.addAttribute("user", new User());
		return "UserChangePassword";
	}
	@RequestMapping(value="/doChangePassword")
	public String doChangePassword(@ModelAttribute("user")@Valid User user,BindingResult result,Model model,HttpServletRequest request)
	{
		if(result.hasErrors())
		{
			if(result.hasFieldErrors("userPassword"))
			{
				model.addAttribute("userPasswordError", result.getFieldError("userPassword").getDefaultMessage());
			}
			 if(result.hasFieldErrors("userConfirmPassword"))
			{
				model.addAttribute("userConfirmPasswordError", result.getFieldError("userConfirmPassword").getDefaultMessage());
			}
			if(result.hasFieldErrors("userNewPassword"))
			{
				model.addAttribute("userNewPasswordError", result.getFieldError("userNewPassword").getDefaultMessage());
			}
			return "UserChangePassword";
		}
		if(user.getUserNewPassword()==null)
		{
			model.addAttribute("userNewPasswordError","New Password can't be blank");
			return "UserChangePassword";
		}
		String userId=(String)request.getSession(true).getAttribute("sessionValue");
		
		if(user.getUserNewPassword().equals(user.getUserConfirmPassword()))
		{
			User newUserDetails=userService.getUserById(userId);
			if(newUserDetails.getLogin().getUserPassword().equals(user.getUserPassword()))
			{
				newUserDetails.getLogin().setUserPassword(user.getUserNewPassword());
				userService.update(newUserDetails);
				model.addAttribute("Message","Password changed successfully!!");
				return "UserHome";
			}
			else
			{
				model.addAttribute("Error", "Wrong Password");
				return "UserChangePassword";
			}
			
		}
		else
		{
		
			model.addAttribute("Error", "Password mismatch!");
			return "UserChangePassword";
		}
		
	}
}
