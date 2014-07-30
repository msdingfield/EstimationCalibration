<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="_csrf" content="${_csrf.token}"/>
        <meta name="_csrf_header" content="${_csrf.headerName}"/>
        
        <link rel="stylesheet" href="/css/normalize.css">
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="/css/signin.css">
        
		<title>Estimation Calibration | Login</title>
	</head>
	<body>
		<c:url value="/login" var="loginUrl"/>
		
		<div class="container">
			<form action="${loginUrl}" method="post" class="form-signin">
				<c:if test="${success == 'true'}">
		   			<div class="bg-success">Your account has been created successfully!</div>
		   		</c:if>
		   	
				<h5 class="form-signin-heading pull-left">Please sign in to begin!</h5>
				<h5 class="pull-right"><a href="/signup">Create Account</a></h5>
			    <input class="form-control" type="text" placeholder="Username" name="username" required/>	
			    <input class="form-control" type="password" placeholder="Password" name="password" required/>
			    <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
			    <input type="hidden"
				    name="${_csrf.parameterName}"
				    value="${_csrf.token}"/>
			    <c:if test="${error == true}">
			    	<h4 class="bg-warning">"Opps! The email or password you entered is incorrect."</h4>
			    </c:if>
			</form>
		</div>
	</body>
</html>