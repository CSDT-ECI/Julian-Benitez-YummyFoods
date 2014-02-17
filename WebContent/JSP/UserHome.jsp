<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta charset="UTF-8">
	<title>Recipe - Food &amp; Recipes s</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>">
</head>
<body>
	<div class="header">
		<div>
			<a href="index"><img src="<c:url value="/resources/images/logo.png" />" alt="Logo"></a>
		</div>
		<form action="index.html">
			<input type="text" value="Search from our 10,000+ Recipes around the world" id="search">
			<input type="submit" value="" id="searchbtn">
		</form>
	</div>
	<div class="body">
		<div>
			<div class="header">
				<ul>
				<li class="current">
						<a href="home">Home</a>
					</li>
					
					<li>
						<a href="allRecipe?page=0">A to Z Recipes</a>
					</li>
					<li>
						<a href="featured.html">Featured Recipes</a>
					</li>
					<li>
						<a href="videos.html">Videos</a>
					</li>
					
					<li>
						<a href="doLogout">Logout</a>
					</li>
					
				</ul>
			</div>
			<div class="body">
				<div id="content">
					<div>
						<div>
						<br><a href="userAddRecipe">Add Recipe</a><br>
							<a href="userAllRecipe?page=0">View Recipe</a><br>
						</div>
					</div>
					
				</div>
			</div>
		</div>
		<div>
			<div>
				<h3>Cooking Video</h3>
				<a href="videos.html"><img src="images/cooking-video.png" alt="Image"></a>
				<span>Vegetable &amp; Rice Topping</span>
			</div>
			<div>
				<h3>Featured Recipes</h3>
				<ul id="featured">
				<c:forEach items="${sessionList}" var="recipe">
					<li>
					<a href="recipe?recipeId=<c:out value="${recipe.recipeId}"/>"><img src="image?recipeId=<c:out value="${recipe.recipeId}"/>" width="80" height="80" alt="Image"></a>
						<div>
							<h2><a href="recipe?recipeId=<c:out value="${recipe.recipeId}"/>"><c:out value="${recipe.name}"/></a></h2>
							<span>by: ${recipe.user.userId}</span>
						</div>
					</li>
					</c:forEach>
					
				</ul>

			</div>
			<div>
				<h3>Blog</h3>
				<ul id="blog">
					<li>
						<a href="blog.html">This is just a place holder, so you can see what the site would look like.</a>
						<span class="date">Jan 9, by Liza</span>
					</li>
					<li>
						<a href="blog.html">This is just a place holder, so you can see what the site would look like.</a>
						<span class="date">Feb 16, by Myk</span>
					</li>
					<li>
						<a href="blog.html">This is just a place holder, so you can see what the site would look like.</a>
						<span class="date">March 15, by Xaxan</span>
					</li>
				</ul>
			</div>
			<div>
				<h3>Get Updates</h3>
				<a href="https://www.facebook.com/pulkit.sharva" target="_blank" id="facebook">Facebook</a>
				<a href="http://www.youtube.com/user/pulkitb4Mv" target="_blank" id="youtube">Youtube</a>
				<a href="https://plus.google.com/117192008917331015868/posts" target="_blank" id="googleplus">Google&#43;</a>
				<a href="http://stackoverflow.com/users/2219920/pulkit" target="_blank" id="stackoverflow">Stack Overflow</a>
				<a href="https://github.com/pulkitsharva" target="_blank" id="github">Github</a>
			</div>
		</div>
	</div>
	<div class="footer">
		<div>
			<p>
				&copy; Copyright 2012. All rights reserved
			</p>
		</div>
	</div>
</body>
</html>