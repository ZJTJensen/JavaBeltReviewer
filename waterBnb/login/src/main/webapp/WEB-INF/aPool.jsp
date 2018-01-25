<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Index</title>
		<link rel="stylesheet" type="text/css" href="/css/style.css">	
		<script src="/js/main.js"></script>
	</head>

	<body>
		<a href="/logout">Logout</a>
		<a href="/">Home</a>
		<p>${pool.address}</p>
		<p>${pool.description}</p>



		<p>Email: ${pool.host.email}</p>
		<p>Name: ${pool.host.firstName} ${pool.host.lastName}</p>
		<c:if test='${user.userType.equals("Guest")}'>
		<p>Size: ${pool.size}</p>
		<p>Cost: ${pool.cost}</p>
		</c:if>
		<c:if test='${user.userType.equals("Host")}'>
		<form:form method="POST" action="/pools/${pool.id}/edit" modelAttribute="pool">
			<p>
				<form:label path="cost">Cost:
				<form:errors path="cost"></form:errors>
				<input type="number"  path="cost"/>
				</form:label>
				</p>
				
			<p>
					<form:label path="size">Pool Size:
					<form:errors path="size"></form:errors>
					<form:select path="size">
						<form:option value="Small">Small</form:option>
						<form:option value="Medium">Medium</form:option>
						<form:option value="Large">Large</form:option>
					</form:select>
					</form:label>
				
			</p>
			<input type="submit" value="Add Listing">
		</form:form>
	</c:if>
	</body>


	<h1>Review (${rating}/5)</h1> 

	<c:if test='${user.userType.equals("Guest")}'>
		<a href="/pools/${pool.id}/review">Leave a review</a>
	</c:if>
	<fieldset>
		<table>
			<c:forEach items = "${pool.reviews}" var = "review">
				<p>${review.user.firstName} ${review.user.lastName}: ${review.poolReview}</p>
				<p>****************************************</p>
			</c:forEach>
		</table>
	</fieldset>
</html>