<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Item</title>
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
			<legend>Fill out fields</legend>
			<form:form commandName="product" id="edit-product-form" method="post" enctype="multipart/form-data" 
				action="${pageContext.request.contextPath}/save">				
				<form:label path="name">Product name:</form:label>
				<form:input path="name" />				
				<form:label path="price">Price:</form:label>
				<form:input path="price" />
				<form:label path="category">Category:</form:label>
				<form:select path="category">
					<c:forEach var="cat" items="${categories}">
						<form:option value="${cat.id}">${cat.name}</form:option>
					</c:forEach>
				</form:select>
				<form:label path="file">Select product image:</form:label>
				<input type="file" name="file" />
				<input type="submit" value="add product" />
			</form:form>
		</fieldset>
	</div>	
</body>
</html>