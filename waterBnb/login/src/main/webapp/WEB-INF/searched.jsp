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
			<c:if test="${id != null}">
					<a href="/logout">Logout</a>
					</c:if>
					<c:if test="${id == null}">
						<a href="/register">Sign in/ Sign up</a>
					</c:if>
			
					<h1>Find a pool near you.</h1>
					<form action="/search">
						<p>
							<label for="location">New Location:
							<input id="location" name="location"/>
							<input type="submit" value="Search!"/></label>
						</p>
					</form>
					<table>
							<thead>
								<tr>
									<th>Address</th>
									<th>Pool Size</th>
									<th>Cost Per Night</th>
									<th>Details</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${foundPools}" var ="pool">
									<tr>
										<td>${pool.address}</td>
										<td>${pool.size}</td>
										<td>${pool.cost}</td>
										<c:if test="${id != null}">
										<td>
											<a href="/pools/${pool.id}">view</a></td>
									</tr>
								</c:if>
								</c:forEach>
							</tbody>
						</table>
				
	</body>
</html>