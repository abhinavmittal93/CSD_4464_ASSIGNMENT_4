<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<form:form action="login" method="post" modelAttribute="login">
		<table class="center">
			<tr>
				<td>Username</td>
				<td>
					<form:input path="username" />
					<form:errors path="username" cssClass="error" />
				</td>
			</tr>
			<tr>
				<td>Password</td>
				<td>
					<form:password  path="password" />
					<form:errors path="password" cssClass="error" />
				</td>
			</tr>
		</table><br>
		<input type="submit" value="Login" />
	</form:form>
</body>
</html>