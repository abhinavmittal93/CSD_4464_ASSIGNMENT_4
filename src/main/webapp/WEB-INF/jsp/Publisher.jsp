<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Publisher</title>
<style>

table.center {
  margin-left: auto; 
  margin-right: auto;
}
</style>
</head>

<body align="center">
	<div style="text-align: center;">
		<h1>Manage Publisher</h1>
	</div>
	<div>
		<form:form action="publisher/save" method="post">
			<table class="center">
				<tr>
					<td>Action</td>
					<td>
						<select id="action" name="action" required="required">
							<option value="ADD">Add a Publisher</option>
							<option value="UPDATE">Update a Publisher</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>Publisher ID [Fill in case of update Only]</td>
					<td><input type="number" name="pubId" /></td>
				</tr>
				<tr>
					<td>Name</td>
					<td><input type="text" name="name" required="required" /></td>
				</tr>
				<tr>
					<td>Address</td>
					<td><input type="text" name="address" required="required" /></td>
				</tr>
			</table><br>
			<input type="submit" value="Submit" />
		</form:form>
		<br>
		<a href="logout">Click here to Logout</a>
	</div>
</body>
</html>