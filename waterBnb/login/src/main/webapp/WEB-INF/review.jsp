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

		<p>Pool of: ${pool.address}</p>
			<form:form method="POST" action="/pools/${pool.id}/review" modelAttribute="review">
			<p>
					<form:label path="poolReview">Pool Review:
					<form:errors path="poolReview"></form:errors>
					<form:textarea path="poolReview" cols="30" rows="10"/></form:label>
			</p>
			<p>
					<form:label path="rating">
					<form:errors path="rating"></form:errors>
					<form:select path="rating">
						<form:option value="${1}">1</form:option>
						<form:option value="${2}">2</form:option>
						<form:option value="${3}">3</form:option>
						<form:option value="${4}">4</form:option>
						<form:option value="${5}">5</form:option>
					</form:select>
					</form:label>
			</p>
			<input type="submit" value="Submit Review">
		</form:form>
	</body>
</html>