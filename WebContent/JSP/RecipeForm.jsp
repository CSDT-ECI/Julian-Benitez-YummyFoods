<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recipe</title>
</head>
<body>
<c:choose>
		<c:when test="${sessionValue==null }">
			<jsp:forward page="/index"/> 
		</c:when>
		<c:otherwise>
			we have the persisted value
		</c:otherwise>
	</c:choose>
	<a href="logout"><input type="button" value="Logout"></a>
<center>
	<form:form action="addRecipe" method="POST" enctype="multipart/form-data" modelAttribute="recipe" >
		New
		<table>
			<tr>
				<td>Name</td><td><form:input path="name"/></td>
			</tr>
			<tr>
				<td>No. of People</td><td>
					<form:select path="forPeople">
						<form:option value="1">1</form:option>
						<form:option value="2">2</form:option>
						<form:option value="3">3</form:option>
					</form:select></td>
			</tr>
			<tr>
				<td>Ingredients</td><td><form:textarea path="ingredients"/></td>
			</tr>
			<tr>
				<td>Directions</td><td><form:textarea path="directions"/></td>
			</tr>
			<tr>
				<td>Image</td><td><input type="file" name="file"></td>
			</tr>
			<tr>
				<td><input type="submit" value="add">
			</tr>
		</table>
	</form:form>
</center>
</body>
</html>