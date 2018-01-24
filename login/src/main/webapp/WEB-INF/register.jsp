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

			<form method="POST" action="/login">
				<p>
					<label for="email">Email</label>
					<input id="email" name="email"/>
					
				</p>
				<p>
					<label for="password">Password</label>
					<input type="password" id="password" name="password"/>
				</p>
				<input type="submit" value="Login!"/>
			</form>


		<form:form method="POST" action="/register" modelAttribute="user">
        <p>
            <form:label path="firstName">First Name:
				<form:errors path="firstName"></form:errors>
			</form:label>
            <form:input path="firstName"/>
        </p>
        <p>
			<form:label path="lastName">Last Name:</form:label>
			<form:errors path="lastName"></form:errors>
            <form:input path="lastName"/>
        </p>
        <p>
            <form:label path="email">Email:
			<form:errors path="Email"></form:errors>
			</form:label>
            <form:input path="email"/>
		</p>
		
		<p>
			<form:label path="location">Location:</form:label>
			<form:errors path="location"></form:errors>
            <form:input path="location"/>
     
            <form:label path="state">
			<form:errors path="state"></form:errors>
			<form:select path="state">
				<form:option value="0" lable="select">Select</form:option>
				<form:option value="AL">Alabama</form:option>
				<form:option value="AK">Alaska</form:option>
				<form:option value="AZ">Arizona</form:option>
				<form:option value="AR">Arkansas</form:option>
				<form:option value="CA">California</form:option>
				<form:option value="CO">Colorado</form:option>
				<form:option value="CT">Connecticut</form:option>
				<form:option value="DE">Delaware</form:option>
				<form:option value="DC">District Of Columbia</form:option>
				<form:option value="FL">Florida</form:option>
				<form:option value="GA">Georgia</form:option>
				<form:option value="HI">Hawaii</form:option>
				<form:option value="ID">Idaho</form:option>
				<form:option value="IL">Illinois</form:option>
				<form:option value="IN">Indiana</form:option>
				<form:option value="IA">Iowa</form:option>
				<form:option value="KS">Kansas</form:option>
				<form:option value="KY">Kentucky</form:option>
				<form:option value="LA">Louisiana</form:option>
				<form:option value="ME">Maine</form:option>
				<form:option value="MD">Maryland</form:option>
				<form:option value="MA">Massachusetts</form:option>
				<form:option value="MI">Michigan</form:option>
				<form:option value="MN">Minnesota</form:option>
				<form:option value="MS">Mississippi</form:option>
				<form:option value="MO">Missouri</form:option>
				<form:option value="MT">Montana</form:option>
				<form:option value="NE">Nebraska</form:option>
				<form:option value="NV">Nevada</form:option>
				<form:option value="NH">New Hampshire</form:option>
				<form:option value="NJ">New Jersey</form:option>
				<form:option value="NM">New Mexico</form:option>
				<form:option value="NY">New York</form:option>
				<form:option value="NC">North Carolina</form:option>
				<form:option value="ND">North Dakota</form:option>
				<form:option value="OH">Ohio</form:option>
				<form:option value="OK">Oklahoma</form:option>
				<form:option value="OR">Oregon</form:option>
				<form:option value="PA">Pennsylvania</form:option>
				<form:option value="RI">Rhode Island</form:option>
				<form:option value="SC">South Carolina</form:option>
				<form:option value="SD">South Dakota</form:option>
				<form:option value="TN">Tennessee</form:option>
				<form:option value="TX">Texas</form:option>
				<form:option value="UT">Utah</form:option>
				<form:option value="VT">Vermont</form:option>
				<form:option value="VA">Virginia</form:option>
				<form:option value="WA">Washington</form:option>
				<form:option value="WV">West Virginia</form:option>
				<form:option value="WI">Wisconsin</form:option>
				<form:option value="WY">Wyoming</form:option>
			</form:select>				
		</form:label>
		</p>
		
		
		
        <p>
			<form:label path="password">Password:
				<form:errors path="password"></form:errors>
			</form:label>
			
            <form:password path="password"/>
        </p>
        <p>
            <form:label path="confirm">Password Confirmation:</form:label>
            <form:password path="confirm"/>
        </p>
        <input type="submit" value="Register!"/>
    </form:form>
	</body>
</html>