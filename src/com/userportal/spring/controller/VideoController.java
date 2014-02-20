package com.userportal.spring.controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VideoController 
{
	@RequestMapping(value="allVideo")
	public String videoPage()
	{
		return "AllVideo";
	}
	
	@RequestMapping(value="userAllVideo")
	public String userVideoPage()
	{
		return "UserAllVideo";
	}
}
