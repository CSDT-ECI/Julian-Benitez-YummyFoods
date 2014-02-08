<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>
<form:form action="newUserAdd" method="POST" modelAttribute="user">
		<center>
		<table>
			<tr><td>Name:</td><td><form:input path="userName"/></td><td><font color="red"><c:out value="${userNameError}" /></font></td></tr>
			<tr><td>Email ID:</td><td><form:input path="userEmailId"/></td><td><font color="red"><c:out value="${userEmailIdError}" /></font></td></tr>
			<tr><td>User Id:</td><td><form:input path="userId"/></td><td><font color="red"><c:out value="${userIdError}" /></font> </td></tr>
			<tr><td>Password:</td><td><form:password path="userPassword"/></td><td><font color="red"><c:out value="${userPasswordError}" /><c:out value="${userPasswordMismatchError}"/></font></td></tr>
			<tr><td>Confirm Password:</td><td><form:password path="userConfirmPassword"/></td><td><font color="red"><c:out value="${userConfirmPasswordError}" /></font></td></tr>
			
			<tr><td></td><td><input type="submit" value="Create"/></td></tr>
	
		</table>
		</center>
	</form:form>



</body>
</html>