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
		<h1>Welcome.</h1>
		<a href="/logout">logout</a>
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
				<c:forEach items="${yourPools}" var ="pooly">
					<tr>
						<td>${pooly.address}</td>
						<td>${pooly.size}</td>
						<td>${pooly.cost}</td>
						<td><a href="/pools/${pooly.id}">edit</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


		<fieldset>
			<legend>New Pool</legend>

			<form:form method="POST" action="/dashboard/newPool" modelAttribute="pool">
			<p>
					<form:label path="address">Address:
					<form:errors path="address"></form:errors>
					<form:input path="address"/></form:label>
			</p>
			<p>
					<form:label path="description">Description:
					<form:errors path="description"></form:errors>
					<form:input type="description" path="description"/></form:label>
				</p>

				<p>
				<form:label path="cost">Cost:
				<form:errors path="cost"></form:errors>
				<form:input type="number"  path="cost"/>
				</form:label>
				</p>
				
			<p>
					<form:label path="size">
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

		</fieldset>
	</body>
</html>