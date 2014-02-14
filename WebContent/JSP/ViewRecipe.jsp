<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recipe</title>
</head>
NEW PAGE
<body>
<c:choose>
		<c:when test="${sessionValue==null }">
			<jsp:forward page="/index"/> 
		</c:when>
		<c:otherwise>
			we have the persisted value
		</c:otherwise>
	</c:choose>
	
	<c:forEach items="${recipeList }" var="recipe">
	Name:	${recipe.name }<br>
	For People: 	${recipe.forPeople }<br>
	Ingredients		${recipe.ingredients }<br>
	Directions		${recipe.directions }<br>
	Img				<img src="images/?recipeId=${recipe.recipeId }" width="100" height="100"/><br>
	
	</c:forEach>
	
</body>
</html>