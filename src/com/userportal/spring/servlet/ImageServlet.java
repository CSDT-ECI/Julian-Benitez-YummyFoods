package com.userportal.spring.servlet;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.userportal.spring.controller.LoginController;
import com.userportal.spring.form.Recipe;
import com.userportal.spring.service.RecipeService;
import com.userportal.spring.service.RecipeServiceImpl;


public class ImageServlet extends HttpServlet
{
	private static final int DEFAULT_BUFFER_SIZE = 1024000; // 10KB.
	@Autowired
	RecipeService recipeService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Integer recipeId=Integer.parseInt((String)request.getParameter("recipeId"));
		List<Recipe>sessionRecipeList=(List<Recipe>) request.getSession(false).getAttribute("sessionList");
		List<Recipe>sessionFullRecipeList=(List<Recipe>) request.getSession(false).getAttribute("sessionFullList");
		for(int i=0;i<sessionFullRecipeList.size();i++)
		{
			Blob image=sessionFullRecipeList.get(i).getPic();
			if(image==null)
			{
				return;
			}
			else if(sessionFullRecipeList.get(i).getRecipeId().equals(recipeId))
			{
				 BufferedOutputStream output = null;
				try
			     {
					  response.setBufferSize(DEFAULT_BUFFER_SIZE);
				     response.setContentType(sessionFullRecipeList.get(i).getContentType());
				     response.setContentLength((int) image.length());
				     
				     //response.setHeader("Content-Disposition", "inline; filename=\"" + sessionRecipeList.get(i).getFileName() + "\"");
				     output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);
				     output.write(image.getBytes(1, (int) image.length()));

			     }
			     catch (Exception e)
			     {
			    	 System.err.println("Some error is coming:"+e.getMessage());
			    	 e.printStackTrace();
			     }
			     finally 
			     {
			        output.close();
			        return;
			      }
			}
		}
	
		for(int i=0;i<sessionRecipeList.size();i++)
		{
			Blob image=sessionRecipeList.get(i).getPic();
			if(image==null)
			{
				return;
			}
			else if(sessionRecipeList.get(i).getRecipeId().equals(recipeId))
			{
				 BufferedOutputStream output = null;
				try
			     {
					  response.setBufferSize(DEFAULT_BUFFER_SIZE);
				     response.setContentType(sessionRecipeList.get(i).getContentType());
				     response.setContentLength((int) image.length());
				     //response.setHeader("Content-Disposition", "inline; filename=\"" + sessionRecipeList.get(i).getFileName() + "\"");
				     output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);
				     output.write(image.getBytes(1, (int) image.length()));

			     }
			     catch (Exception e)
			     {
			    	 System.err.println("Some error is coming:"+e.getMessage());
			    	 e.printStackTrace();
			     }
			     finally 
			     {
			        output.close();
			        return;
			      }
			}
		}
		//List<Recipe>sessionRecipeList=(List<Recipe>) session.getAttribute("sessionList");
		System.out.println(sessionRecipeList.get(0).getName());
		//System.out.println("Pic Name"+request.getParameter("id"));


	 }

}
