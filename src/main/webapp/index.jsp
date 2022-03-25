<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index</title>
</head>
<body align="center">
	<c:if  test="${!isLoggedIn}">
		<a href="login">Login</a><br><br>
	</c:if>
	<a href="register">Register</a><br><br>
	<a href="member">Manage Member</a><br><br>
	<a href="publisher">Manage Publisher</a><br><br>
	<a href="book">Manage Books</a><br><br>
	<a href="book/borrowed">Borrowed Books</a><br><br>
	<c:if  test="${isLoggedIn}">
		<a href="logout">Logout</a><br><br>
	</c:if>
</body>
</html>