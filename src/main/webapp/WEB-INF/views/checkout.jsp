<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Checkout</title>
<style type="text/css">
	.page { width: 713px; margin: 0 auto; font:14px Arial; color:#333; }
	fieldset { width:480px; border-radius:5px; }
	#edit-product-form label { display: block; float: left; width: 150px; text-align: right; clear: both; margin:3px; }
	#edit-product-form input[type=text], #edit-product-form input[type=file], #edit-product-form select { display: block; float: right; width: 300px; margin: 3px;}
	#edit-product-form input[type=submit] { display: block; float: right; width:300px; margin: 3px; float:right; clear: both; }
</style>
</head>
<body>
	<div class="page">
		<fieldset>
			<legend>Make order</legend>
			<form:form id="edit-product-form" commandName="order" method="post" action="${pageContext.request.contextPath}/checkout">
				<form:label path="firstName">First name:</form:label>
				<form:input path="firstName"/>
				<form:label path="middleName">Middle name:</form:label>
				<form:input path="middleName"/>
				<form:label path="lastName">Last name:</form:label>
				<form:input path="lastName"/>
				<form:label path="email">Email:</form:label>
				<form:input path="email"/>
				<form:label path="phone">Phone:</form:label>
				<form:input path="phone"/>
				<form:label path="address1">Address 1:</form:label>
				<form:input path="address1"/>
				<form:label path="address2">Address 2:</form:label>
				<form:input path="address2"/>
				<form:label path="zipCode">Zip code:</form:label>
				<form:input path="zipCode"/>
				<form:label path="comment">Comment:</form:label>
				<form:input path="comment"/>
				<input type="submit" value="Order Now"/>
			</form:form>
		</fieldset>
	</div>
</body>
</html>