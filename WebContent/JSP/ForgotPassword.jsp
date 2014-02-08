<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forgot Password</title>
</head>

<body>
<center>
Reset Password
	<form:form action="resetPassword" method="post" modelAttribute="user">
		<table>
			<tr><td>Email ID</td><td><form:input path="userEmailId"/></td></tr>	
		</table>
		<input type="submit" value="Reset Password"/>
	</form:form>
</center>
</body>
</html>