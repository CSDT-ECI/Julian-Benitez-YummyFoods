<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Home Page for registered user sir
	<font color="red">
	<c:choose>
		<c:when test="${sessionValue==null }">
			<jsp:forward page="/index"/> 
		</c:when>
		<c:otherwise>
			we have the persisted value ${sessionValue }
		</c:otherwise>
	</c:choose>
	</font>
	<a href="logout"><input type="button" value="Logout"></a>
	
	<br><a href="user/recipe">Add Recipe</a><br>
	<a href="viewRecipe">View Recipe</a><br>
</body>
</html>