<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Books</title>
<style>

table.center {
  margin-left: auto; 
  margin-right: auto;
}
</style>
</head>

<body align="center">
	<div style="text-align: center;">
		<h1>Manage Books</h1>
	</div>
	<div>
		<form:form action="book/save" method="post">
			<table class="center">
				<tr>
					<td>Action</td>
					<td>
						<select id="action" name="action" required="required">
							<option value="ADD">Add a Book</option>
							<option value="UPDATE">Update a Book</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>Book ID [Fill in case of update Only]</td>
					<td><input type="number" name="bookId" /></td>
				</tr>
				<tr>
					<td>Title</td>
					<td><input type="text" name="title" required="required" /></td>
				</tr>
				<tr>
					<td>Author</td>
					<td><input type="text" name="author" required="required" /></td>
				</tr>
				<tr>
					<td>Price</td>
					<td><input type="number" name="price" required="required" /></td>
				</tr>
				<tr>
					<td>Available</td>
					<td>
						<select id="available" name="available" required="required">
							<option value="Y">Yes</option>
							<option value="N">No</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>Publisher</td>
					<td>
						<select id="pubId" name="publisherBean.pubId" required="required">
							<c:forEach var="publisher" items="${publishers}">
								<option value="${publisher.getPubId()}">${publisher.getName()} - ${publisher.getAddress()}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</table><br>
			<input type="submit" value="Submit" />
		</form:form>
	</div>
	
	<div>
		<h2>Issue Book</h2>
		<form:form action="book/issue" method="post">
			<table class="center">
				<tr>
					<td>Book</td>
					<td>
						<select id="bookId" name="bookId" required="required">
							<c:forEach var="book" items="${books}">
								<c:set var="isAvailable">${book.available}</c:set>
								<c:if test='${isAvailable == "Y"}'>
									<option value="${book.getBookId()}">${book.getTitle()} - ${book.getAuthor()} - $${book.getPrice()}</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>Member</td>
					<td>
						<select id="membId" name="memberBean.membId" required="required">
							<c:forEach var="member" items="${members}">
								<option value="${member.getMembId()}">${member.getName()}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</table><br>
			<input type="submit" value="Issue Book" />
		</form:form>
	</div>
	
	<div>
		<h2>Delete Book</h2>
		<form:form action="book/delete" method="post">
			<table class="center">
				<tr>
					<td>Book</td>
					<td>
						<select id="bookId" name="bookId" required="required">
							<c:forEach var="book" items="${books}">
								<c:set var="isAvailable">${book.available}</c:set>
								<c:if test='${isAvailable == "Y"}'>
									<option value="${book.getBookId()}">${book.getTitle()} - ${book.getAuthor()} - $${book.getPrice()}</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
				</tr>
			</table><br>
			<input type="submit" value="Delete Book" />
		</form:form>
	</div>
</body>
</html>