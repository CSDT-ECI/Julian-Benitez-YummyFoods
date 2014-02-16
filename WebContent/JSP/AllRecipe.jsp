<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- Website template by freewebsitetemplates.com -->
<html>
<head>
	<meta charset="UTF-8">
	<title>A to Z Recipes - Food &amp; Recipes Web</title>
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />">
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
					<li>
						<a href="index.html">Home</a>
					</li>
					<li class="current">
						<a href="recipes.html">A to Z Recipes</a>
					</li>
					<li>
						<a href="featured.html">Featured Recipes</a>
					</li>
					<li>
						<a href="videos.html">Videos</a>
					</li>
					<li>
						<a href="about.html">About</a>
					</li>
					<li>
						<a href="blog.html">Blog</a>
					</li>
				</ul>
			</div>
			<div class="body">
				<div id="content">
					<div>
						<ul>
							<c:forEach items="${recipeList }" var="recipe">
								<li>
									<a href="recipe?recipeId=${recipe.recipeId }"><img src="image?recipeId=${recipe.recipeId }" alt="Image" width="160" height="160"></a>
									<div>
										<h3><a href="recipe?recipeId=${recipe.recipeId }">${recipe.name }</a></h3>
										<p>
										${recipe.directions } this could be long stiasd k ja shd k a hdahk sdh s dha k d s h ka h dkha skd h kas h dk j a shdkhaskdhaskhdkashdkhaskjdhkasjhdk.
									</p>
									</div>
								</li>
							</c:forEach>
						</ul>
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
					<li>
						<a href="recipes.html"><img src="images/sandwich.jpg" alt="Image"></a>
						<div>
							<h2><a href="recipes.html">Ham Sandwich</a></h2>
							<span>by: Anna</span>
						</div>
					</li>
					<li>
						<a href="recipes.html"><img src="images/biscuit-and-coffee.jpg" alt="Image"></a>
						<div>
							<h2><a href="recipes.html">Biscuit &amp; Sandwich</a></h2>
							<span>by: Sarah</span>
						</div>
					</li>
					<li>
						<a href="recipes.html"><img src="images/pizza.jpg" alt="Image"></a>
						<div>
							<h2><a href="recipes.html">Delicious Pizza</a></h2>
							<span>by: Rico</span>
						</div>
					</li>
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
				<a href="http://freewebsitetemplates.com/go/facebook/" target="_blank" id="facebook">Facebook</a>
				<a href="http://freewebsitetemplates.com/go/twitter/" target="_blank" id="twitter">Twitter</a>
				<a href="http://freewebsitetemplates.com/go/youtube/" target="_blank" id="youtube">Youtube</a>
				<a href="http://freewebsitetemplates.com/go/flickr/" target="_blank" id="flickr">Flickr</a>
				<a href="http://freewebsitetemplates.com/go/googleplus/" target="_blank" id="googleplus">Google&#43;</a>
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