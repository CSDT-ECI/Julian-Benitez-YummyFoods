<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
function updateAlert()
{
	var updated=document.getElementById("updated").value;
	if(updated=='done')
	{
		alert("Updated Succesfully!!");	
	}
}
</script>
<c:if test="${sessionValue==null }">
<jsp:forward page="/index"></jsp:forward>
</c:if>
<head>
	<meta charset="UTF-8">
	<title>Recipe - Food &amp; Recipes</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>">
</head>
<body onLoad="updateAlert()">

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
					
					<li class="current_page_ancestor"><a href="allVideo">Videos</a>
					</li>
					<li class="haschildren"><a href="#" >Settings</a>
						<ul>
							<li><a href="editProfile" >Profile</a></li>
							<li><a href="changePassword" >Change Password</a></li>
							<li><a href="doLogout" >Logout</a></li>
						</ul>
					</li>
				</ul>
			</nav>
		</div>
			
				<div id="content">
					<div>
						<div>
						<center>
						<br><br><br>
	<form:form action="doEditRecipe" method="POST" enctype="multipart/form-data" modelAttribute="recipe" >
		<form:hidden path="recipeId"/>
		<c:if test="${newUpdate=='done' }">
			<input type="hidden" id="updated" value="${newUpdate }" />
		</c:if>
		<table>
			<tr>
				<td align="left">Name</td><td align="left"><form:input path="name"/></td>
			</tr>
			<tr>
				<td align="left">No. of People</td>
				<td align="left">
					<form:select path="forPeople">
						<form:option value="1">1</form:option>
						<form:option value="2">2</form:option>
						<form:option value="3">3</form:option>
						<form:option value="4">4</form:option>
						<form:option value="5">5</form:option>
					</form:select></td>
			</tr>
			<tr>
				<td align="left">Ingredients</td><td align="left"><form:textarea path="ingredients" rows="6" cols="50"/></td>
			</tr>
			<tr>
				<td align="left">Directions</td><td align="left"><form:textarea path="directions" rows="6" cols="50"/></td>
			</tr>
			<tr>
				<td align="left">Image</td><td align="left"><img src="image?recipeId=${recipeId }" alt="Image" width="160" height="160"></td>
			</tr>
			<tr><td></td>
				<td align="left"><input type="file" name="newFile"></td>
			</tr>
		
		</table>
		<br><br>
		<input type="submit" value="Update">
	</form:form>
	</center>
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