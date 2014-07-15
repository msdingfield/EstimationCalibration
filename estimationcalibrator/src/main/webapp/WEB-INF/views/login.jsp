<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Estimation Calibration</title>
</head>
<body>
   	<c:if test="${param.error != null}">        
       	<p>
           	Invalid username and password.
       	</p>
   	</c:if>
   	<c:if test="${param.logout != null}">       
       	<p>
           	You have been logged out.
       	</p>
   	</c:if>
   	<c:if test="${signupSuccess == 'true'}">
   		<p>
   			Your profile has been created successfully!
   		</p>
   	</c:if>
   	
	<p>Please sign in to begin!</p>
	
	<c:url value="/login" var="loginUrl"/>
	<form action="${loginUrl}" method="post">
	    <p>
	        <label for="username">Username</label>
	        <input type="text" id="username" name="username"/>	
	    </p>
	    <p>
	        <label for="password">Password</label>
	        <input type="password" id="password" name="password"/>	
	    </p>
	    <input type="hidden"                        
	        name="${_csrf.parameterName}"
	        value="${_csrf.token}"/>
	    <button type="submit" class="btn">Log in</button>
	</form>
	<a href="signup">Not a user, sign up!</a>
</body>
</html>