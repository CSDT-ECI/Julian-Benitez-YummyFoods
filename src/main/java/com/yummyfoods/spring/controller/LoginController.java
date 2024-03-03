package com.yummyfoods.spring.controller;


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
import com.yummyfoods.spring.form.Login;
import com.yummyfoods.spring.form.Recipe;
import com.yummyfoods.spring.service.LoginService;
import com.yummyfoods.spring.service.RecipeService;
import com.yummyfoods.spring.validator.LoginValidator;

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
	public String index(Model model,HttpSession session)
	{
		if(session.getAttribute("sessionList")==null)
		{
			session.setAttribute("sessionList", recipeService.getFeaturedList());
			session.setAttribute("sessionFullList", recipeService.getAllRecipe());
		}
		
		model.addAttribute("login", new Login());
		model.addAttribute("recipe", new Recipe());
		return "main";
	}
	

	@RequestMapping(value="/doLogin", method=RequestMethod.POST)
	public String doLogin(@ModelAttribute("login")@Valid Login login,BindingResult result, Model model,HttpServletRequest request,HttpSession session)
	{
		model.addAttribute("recipe", new Recipe());
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
						return "redirect:recipeForRating";
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
	public String login(Model model,HttpSession session)
	{
		if(session.getAttribute("sessionFullList")==null)
		{
			session.setAttribute("sessionList", recipeService.getFeaturedList());
			session.setAttribute("sessionFullList", recipeService.getAllRecipe());
		}
		model.addAttribute("login", new Login());
		model.addAttribute("recipe", new Recipe());
		return "Login";
	}
	
	@RequestMapping(value="loginForRating")
	public String loginForRecipe(@RequestParam(value="recipeId")String recipeId,HttpServletRequest request,Model model)
	{
		model.addAttribute("recipe", new Recipe());
		HttpSession session=request.getSession();
		session.setAttribute("recipeIdForRating", recipeId);
		model.addAttribute("login",new Login());
		return "Login";
	}
	
	@RequestMapping(value="error")
	public String error()
	{
		return "Error";
	}
	
	
		
}

