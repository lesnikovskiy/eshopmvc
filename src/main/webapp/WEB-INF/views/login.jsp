<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<style type="text/css">
	.page { width: 713px; margin: 0 auto; font:14px Arial; color:#333; }
	.error {
		width:450px;
		/*margin:0 auto;*/
		color:#FF0000;
		background: #FFEEEE;
		border: 1px solid #FF0000;
		border-radius:5px;
		padding:8px;
	}
	
	fieldset { width:480px; border-radius:5px; margin:0 auto; }
	#login-form label { display: block; float: left; width: 150px; text-align: right; clear: both; margin:3px; }
	#login-form input[type=text], #login-form input[type=password] { display: block; float: right; width: 300px; margin: 3px;}
	#login-form input[type=submit] { display: block; float: right; width:300px; margin: 3px; float:right; clear: both; }
</style>
</head>
<body>
	<div class="page">		
		<fieldset>
			<legend>Enter your credentials</legend>
			<c:if test="${!empty error}">
				<div class="error">
					Your login attempt was not successful, try again.
					Reason: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}.
				</div>
			</c:if>
			<form id="login-form" name="f" action="<c:url value='/j_spring_security_check' />" method="post">
				<label for="j_username">Username:</label>
				<input type="text" name="j_username" id="j_username" />
				<label for="j_password">Password:</label>
				<input type="password" name="j_password" id="j_password" />
				<input type="submit" value="login" />
			</form>
		</fieldset>
	</div>
</body>
</html>