package com.userportal.spring.controller;

import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.userportal.spring.form.Recipe;
import com.userportal.spring.form.User;
import com.userportal.spring.service.RecipeService;
import com.userportal.spring.service.UserService;

@Controller
public class RecipeController 
{
	@Autowired
	RecipeService recipeService;

	@Autowired
	private UserService userService;
	
	public void getRecipeByUser(int page,List<Recipe> paginationRecipeList,HttpSession session,Model model)
	{
		int page1=0,page2=0,page3=0;
		for(int i=0;i<paginationRecipeList.size();i++)
		{
			int length=paginationRecipeList.get(i).getDirections().length();
			if(length<275)
			{
				
			}
			else
			{
				paginationRecipeList.get(i).setDirections(paginationRecipeList.get(i).getDirections().substring(0,275));
			}
			
		}
		model.addAttribute("recipeList", paginationRecipeList);
	
		if(page==-1)
		{
			page1=(Integer) session.getAttribute("PageValue1");
			page2=(Integer) session.getAttribute("PageValue2");
			page3=(Integer) session.getAttribute("PageValue3");
			page1=page1-1;
			page2=page2-1;
			page3=page3-1;
		}
		else if(page<-1)
		{
			page1=1;
			page2=2;
			page3=3;
		}
		else if(page>=0)
		{
			page1=page+1;
			page2=page1+1;
			page3=page2+1;
		}
		session.setAttribute("PageValue1", page1);
		session.setAttribute("PageValue2", page2);
		session.setAttribute("PageValue3", page3);
	}
	
	@RequestMapping(value="/userAddRecipe")
	public String recipe(Model model)
	{
		model.addAttribute("recipe",new Recipe());
		return "UserRecipeForm";
	}
	
	@RequestMapping(value="/userRecipe")
	public String viewRecipe(@RequestParam(value = "page", required = false) Integer page,Model model,HttpSession session)
	{
		String userId=(String)session.getAttribute("sessionValue");
		List<Recipe> paginationRecipeList=recipeService.getRecipeForPaginationByUserId(page, userId);
		getRecipeByUser(page,paginationRecipeList,session,model);
		return "UserRecipe";
	}
	
	@RequestMapping(value="/userSubmitRecipe", method=RequestMethod.POST)
	public String addRecipe(@ModelAttribute("recipe")Recipe recipe,User user,HttpServletRequest request,@RequestParam("file") MultipartFile file)
	{
		try 
        {
			Blob blob = Hibernate.createBlob(file.getBytes());
        	recipe.setFileName(file.getOriginalFilename());
            recipe.setPic(blob);
            recipe.setContentType(file.getContentType());
        	recipe.setPic(blob);
        	user=userService.getUserById((String) request.getSession(false).getAttribute("sessionValue"));
        	recipe.setUser(user);
        	recipeService.add(recipe);
        } 
        
        catch (IOException e) 
        {
            e.printStackTrace();
        }
         
		return "UserHome";
	}
	
	@RequestMapping(value="/recipe")
	public String viewRecipeById(Model model,HttpServletRequest request,HttpSession session)
	{
		int recipeId=Integer.parseInt(request.getParameter("recipeId"));
		String sessionValue=(String)session.getAttribute("sessionValue");
		List<Recipe> recipeDetails=new ArrayList<Recipe>();
		recipeDetails.add(recipeService.getRecipeById(recipeId));
		model.addAttribute("recipeDetails", recipeDetails);
		if(sessionValue==null)
		{
			return "ViewRecipe";
		}
		else
		{
			User user=userService.getUserById(sessionValue);
			if(user.getRecipeRated()!=null)
			{
				if(user.getRecipeRated().contains(","+recipeId+"="))
				{
					
					model.addAttribute("recipeAlreadyRated", "true");
				}
				else
				{
					model.addAttribute("recipeAlreadyRated", "false");
				}
			}
			else
			{
				model.addAttribute("recipeAlreadyRated", "false");
			}
			String temp=""+recipeId;
			int begin=0,end=0;
			
			begin=user.getRecipeRated().indexOf(","+recipeId+"=")+temp.length()+2;
			end=begin+1;
			model.addAttribute("userOldRating", user.getRecipeRated().substring(begin,end));
			return "UserViewRecipe";
		}
		
	}
	
	@RequestMapping(value="allRecipe")
	public String allRecipe(@RequestParam(value = "page", required = false) Integer page,Model model,HttpServletRequest request,HttpSession session)
	{
		String sessionValue=(String)request.getSession(false).getAttribute("sessionValue");
		List<Recipe> paginationRecipeList=recipeService.getRecipeForPagination(page);
		getRecipeByUser(page, paginationRecipeList, session, model);
		if(sessionValue==null)
		{
			return "AllRecipe";
		}
		else
		{
			return "UserAllRecipe";
		}
		
	}
	
	
	
	@RequestMapping(value="/home")
	public String home(Model model)
	{
		return "UserHome";
	}
	
	@RequestMapping(value="/doSearch")
	public String doSearch(@ModelAttribute("recipe")Recipe recipe,@RequestParam("page")Integer page,Model model, HttpServletRequest request,HttpSession session)
	{
		String temp="";
		String recipeName=recipe.getName();
		if(temp.equals(recipeName)||recipeName==null)
		{
			if(request.getSession(false).getAttribute("sessionValue")==null)
			{
				return"redirect:allRecipe?page=0";
			}
			else
			{
				return"redirect:userAllRecipe?page=0";
			}
		}
		else
		{
			System.out.println("Recipe Name:"+recipeName);
			List <Recipe> searchRecipeList=recipeService.getRecipeByName(recipeName,page);
			getRecipeByUser(page, searchRecipeList, session, model);
			if(request.getSession(false).getAttribute("sessionValue")==null)
			{
				return"SearchRecipe";
			}
			else
			{
				return "UserSearchRecipe";
			}
			
		}
		
	}

	@RequestMapping(value="/assignUserRating")
	public @ResponseBody String assignUserRating(Model model,HttpServletRequest request)
	{
		
		String userId=(String)request.getSession(false).getAttribute("sessionValue");
		int recipeId=Integer.parseInt((String)request.getParameter("recipeId"));
		float userRating=Float.parseFloat((String)request.getParameter("userRating"));
		Recipe recipe=recipeService.getRecipeById(recipeId);
		User user=userService.getUserById(userId);
		float rating;
		String recipeAlreadyRated=(String)request.getParameter("recipeAlreadyRated");
		if(recipeAlreadyRated.equals("true"))
		{
			float currentRating=0;
			rating=0;
			float oldRating=Float.parseFloat((String)request.getParameter("oldRating"));
			currentRating=recipe.getCurrentRating();
			rating=(float)(currentRating*(recipe.getNoOfPeopleRated()-1)+currentRating-oldRating+userRating)/recipe.getNoOfPeopleRated();
			recipe.setCurrentRating(rating);
			recipeService.update(recipe);
			String temp=""+recipeId;
			int begin=0,end=0;
			begin=user.getRecipeRated().indexOf(","+recipeId+"=")+1;
			end=begin+4+temp.length();
			String newRecipeRated=user.getRecipeRated().substring(0, begin)+recipeId+"="+userRating+user.getRecipeRated().substring(end);
			user.setRecipeRated(newRecipeRated);
			userService.update(user);
			model.addAttribute("userOldRating", userRating);
			return "Rating changed!!";
		}
		rating=(float) ((recipe.getCurrentRating()*recipe.getNoOfPeopleRated()+userRating)/(recipe.getNoOfPeopleRated()+1.0));
		recipe.setCurrentRating(rating);
		recipe.setNoOfPeopleRated(recipe.getNoOfPeopleRated()+1);
		recipeService.update(recipe);
		if(user.getRecipeRated()!=null)
		{
			user.setRecipeRated(user.getRecipeRated()+recipeId+"="+userRating+",");
		}
		else
		{
			user.setRecipeRated(","+recipeId+"="+userRating+",");
		}
		
		userService.update(user);
		String status="Rating Assigned";
		model.addAttribute("oldRating", userRating);
		return status;
				
	}
	@RequestMapping(value="recipeForRating")
	public String recipeForRating(Model model,HttpSession session)
	{
		Integer recipeId=Integer.parseInt((String)session.getAttribute("recipeIdForRating"));
		String userId=(String)session.getAttribute("sessionValue");
		User user=userService.getUserById(userId);
		if(user.getRecipeRated()!=null)
		{
			if(user.getRecipeRated().contains(","+recipeId+"="))
			{
				
				model.addAttribute("recipeAlreadyRated", "true");
				String temp=""+recipeId;
				int begin=0,end=0;
				begin=user.getRecipeRated().indexOf(","+recipeId+"=")+temp.length()+2;
				end=begin+1;
				model.addAttribute("userOldRating", user.getRecipeRated().substring(begin,end));
				System.out.println("user has rated this recipe with rating:"+user.getRecipeRated().substring(begin,end));
			}
			else
			{
				model.addAttribute("recipeAlreadyRated", "false");
				session.setAttribute("recipeAlreadyRated", "false");
			}
			
		}
		else
		{
			session.setAttribute("recipeAlreadyRated", "false");
		}
		List<Recipe> recipeDetails=new ArrayList<Recipe>();
		recipeDetails.add(recipeService.getRecipeById(recipeId));
		model.addAttribute("recipeDetails", recipeDetails);
		return "UserViewRecipe";
	}
}
