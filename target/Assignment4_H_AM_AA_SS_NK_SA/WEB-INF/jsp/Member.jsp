<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Members</title>
<style>

table.center {
  margin-left: auto; 
  margin-right: auto;
}

.error{color:red}  
</style>
</head>

<body align="center">
	<div style="text-align: center;">
		<h1>Manage Members</h1>
	</div>
	<div>
		<form:form action="member/save" method="post" modelAttribute="member">
			<table class="center">
				<tr>
					<td>Action</td>
					<td>
						<select id="action" name="action" required="required">
							<option value="ADD">Add a Member</option>
							<option value="UPDATE">Update a Member</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>Member ID [Fill in case of update Only]</td>
					<td><input type="number" name="membId" /></td>
				</tr>
				<tr>
					<td>Name</td>
					<td>
						<form:input path="name" />
					</td>
					<td>
						<form:errors path="name" cssClass="error"/>
					</td>
				</tr>
				<tr>
					<td>Address</td>
					<td>
						<form:input path="address" />
					</td>
					<td>
						<form:errors path="address" cssClass="error"/>
					</td>
				</tr>
				<tr>
					<td>Membership Type</td>
					<td>
						<select id="membType" name="membType" required="required">
							<option value="">Select</option>
							<option value="ADULT">Adult Card</option>
							<option value="CHILD">Child Card</option>
							<option value="STUDENT">Student Card</option>
						</select>
					</td>
				</tr>
			</table><br>
			<input type="submit" value="Submit" />
		</form:form>
	</div>
</body>
</html>