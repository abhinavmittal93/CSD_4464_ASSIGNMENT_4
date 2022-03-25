<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
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
	<h1>Login</h1>
	<hr>
	<div>
		<span style="color: green;"><c:out value= "${message}"/></span>
		<span style="color: red;"><c:out value= "${errorMessage}"/></span>
		<br>
		<form:form action="login" method="post" modelAttribute="login">
			<table class="center">
				<tr>
					<td>Username</td>
					<td>
						<input type="text" name="username" required/>
					</td>
				</tr>
				<tr>
					<td>Password</td>
					<td>
						<input type="password" name="password" required/>
					</td>
				</tr>
			</table><br>
			<input type="submit" value="Login" />
		</form:form>
		<br>
		<a href="register">Click here to Register</a>
	</div>
</body>
</html>