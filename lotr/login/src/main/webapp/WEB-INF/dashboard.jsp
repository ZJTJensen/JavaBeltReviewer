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
		<a href="/logout">logout</a>
		<h1>Welcome ${user.username}.</h1>
		<p>Welcome to your awesome magical ring finder, put the ring onn, only good
			thisngs will happen. maybe it will make you live forever, go invisible, turn your
			inhernt hunger for riches or power into an insatiable cure that eventually dooms your
			entire species.
		</p>

		<h3>Rings of power</h3>


		<form method ="POST"action="/add">
		<select name="ring">
			<c:forEach items = "${notYourRings}" var = "ring">
				<option value="${ring.id}">${ring.name}</option>
			</c:forEach>
		</select>
		<input type="submit" value="Add Ring">
	</form>

	<c:if test="${user.getAdmin()== 1}">
		<a href="/jewler">Ring Creator (powerul Ainur only)</a>
		<a href="/makeFate">Person/Team Creator (powerul Ainur only)</a>
	</c:if>


	<table>
		<thead>
			<tr>
				<th>Rings you have found</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items = "${yourRings}" var = "ring">
			<tr>
				<td>${ring.name}</td>
				<td><a href="/delete/${ring.id}">Lose the Rind (Delete)</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
		
	</body>
</html>