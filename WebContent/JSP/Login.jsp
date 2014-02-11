<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<center>
Welcome to the new world Pulkit Sharva!!!!
<br><c:out value="${ResetPassword}"></c:out>
	<form:form action="login" method="POST" modelAttribute="login">
		<table>
			<tr><td>User Id:</td><td><form:input path="userId"/></td><td><font color="red"><c:out value="${userIdError}" /></font> </td></tr>
			<tr><td>Password:</td><td><form:input path="userPassword"/></td><td><font color="red"><c:out value="${userPasswordError}" /></font></td></tr>
			<tr><td></td><td><input type="submit" value="Login"/></td></tr>
			<tr><td><a href="newUser">New User</a></td><td><a href="forgotPassword">Forgot Password</a></td></tr>
		</table>
	</form:form>
</center>
</body>
</html>