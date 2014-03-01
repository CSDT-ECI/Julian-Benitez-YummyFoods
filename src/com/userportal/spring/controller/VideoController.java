package com.userportal.spring.controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.userportal.spring.form.Recipe;

@Controller
public class VideoController 
{
	@RequestMapping(value="allVideo")
	public String videoPage(Model model)
	{
		model.addAttribute("recipe", new Recipe());
		return "AllVideo";
	}
	
	@RequestMapping(value="userAllVideo")
	public String userVideoPage(Model model)
	{
		model.addAttribute("recipe", new Recipe());
		return "UserAllVideo";
	}
}
