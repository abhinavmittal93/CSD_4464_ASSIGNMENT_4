<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<style type="text/css">
	table.center {
	  margin-left: auto; 
	  margin-right: auto;
	}
	html, body {
	    height: 100%;
	    align-items: center;
	}
	.error{color:red}
</style>
</head>
<body align="center">
	<h1>Register</h1>
	<hr>
	<div>
		<form:form action="register" method="post" modelAttribute="login">
			<table class="center">
				<tr>
					<td>Username</td>
					<td>
						<form:input path="username" />
					</td>
					<td>
						<form:errors path="username" cssClass="error" />
					</td>
				</tr>
				<tr>
					<td>Password</td>
					<td>
						<form:password  path="password" />
					</td>
					<td>
						<form:errors path="password" cssClass="error" />
					</td>
				</tr>
			</table><br>
			<input type="submit" value="Register" />
		</form:form>
		<br>
		<a href="login">Click here to Login</a>
	</div>
</body>
</html>