<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<c:if test="${sessionValue==null }">
<jsp:forward page="/index"></jsp:forward>
</c:if>
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
		<form:form action="doSearch?page=0" modelAttribute="recipe" method="post">
			<form:input path="name" id="search"/>
			<input type="submit" value="" id="searchbtn">
		</form:form>
	</div>
	
	
	<div class="body">
		<div>
			<div class="header">
		<nav id="nav_wrapper">
				<ul class="sf-menu" id="suckerfishnav">
					<li><a href="home">Home</a></li>
					<li class="haschildren"><a href="#" >A-Z Recipe</a>
						<ul>
							<li><a href="allRecipe?page=0" >All Recipe</a></li>
						</ul>
					</li>
					<li class="haschildren"><a href="#" >My Recipe</a>
						<ul>
							<li><a href="userAddRecipe" >Post</a></li>
							<li><a href="userRecipe?page=0" >View/Edit</a></li>		
						</ul>
					</li>
					
					<li class="current_page_ancestor"><a href="userAllVideo">Videos</a>
					</li>
					<li class="haschildren"><a href="#" >Settings</a>
						<ul>
							<li><a href="#" >Profile</a></li>
							<li><a href="changePassword" >Change Password</a></li>
							<li><a href="doLogout" >Logout</a></li>
						</ul>
					</li>
				</ul>
			</nav>
			</div>
				<div id="content">
					<div>
						<ul>
							<c:forEach items="${recipeList }" var="recipe">
							 		<li>
										<a href="recipe?recipeId=${recipe.recipeId }"><img src="image?recipeId=${recipe.recipeId }" alt="Image" width="160" height="160"></a>
										<div>
											<h3><a href="recipe?recipeId=${recipe.recipeId }">${recipe.name }</a>&nbsp;|&nbsp;<a href="editRecipe?recipeId=${recipe.recipeId }">Edit</a></h3>
											<p>
											${recipe.directions }... <a href="recipe?recipeId=${recipe.recipeId }">More</a>
											<br><font color="red">Current Rating: ${recipe.currentRating }</font>
										</p>
										
										</div>
									</li>
								
							</c:forEach>
						</ul>
						<div align="right">
							<c:if test="${PageValue1>1 }">
								<a href="userRecipe?page=-1">Previous </a>
							</c:if>
							<a href="userRecipe?page=${PageValue1-1 }">${PageValue1 }</a> 
							<a href="userRecipe?page=${PageValue2-1 }">${PageValue2 }</a> 
							<a href="userRecipe?page=${PageValue3-1 }">${PageValue3 }</a>
						</div>
					</div>
				</div>
			</div>
		<div>
			<div>
				<h3>Cooking Video</h3>
				<iframe width="200" height="200" src="//www.youtube.com/embed/ngN4TWgL3sY" frameborder="0" allowfullscreen></iframe>
				
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
				&copy; Copyright 2014. All rights reserved
			</p>
		</div>
	</div>
</body>
</html>