<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Borrowed Books Details</title>
<style>
table, th, td {
  border:1px solid black;
  border-collapse: collapse;
}
table.center {
  margin-left: auto; 
  margin-right: auto;
}
</style>
</head>
<body align="center">
	<div style="text-align: center;">
		<h1>Borrowed Books Details</h1>
	</div>
	<div>
		<table class="center" style="width:100%">
			<tr>
				<th>Book Title</th>
				<th>Book Author</th>
				<th>Book Price</th>
				<th>Book Publisher</th>
				<th>Issue Date</th>
				<th>Due Date</th>
				<th>Return Date</th>
				<th>Member Name</th>
				<th>Member Address</th>
				<th>Membership Type</th>
			</tr>
			<c:forEach items="${books}" var="book">
			    <tr>
					<td>${book.title}</td>
					<td>${book.author}</td>
					<td>$${book.price}</td>
					<td>${book.publisherBean.name}</td>
					<td>${book.issueDate}</td>
					<td>${book.dueDate}</td>
					<td>${book.returnDate}</td>
					<td>${book.memberBean.name}</td>
					<td>${book.memberBean.address}</td>
					<td>${book.memberBean.membType}</td>
				</tr>
			</c:forEach>
		</table>
		<br>
		<a href="../logout">Click here to Logout</a>
	</div>
</body>
</html>