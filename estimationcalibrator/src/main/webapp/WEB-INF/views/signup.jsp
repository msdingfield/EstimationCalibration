<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" ng-app="SignUp">
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

		<title>Estimation Calibration | Sign Up</title>
	</head>
	<body>
		<div class="container">
			<form class="form-signin" ng-controller="SignupForm" role="form">
				<h5 class="form-signin-heading pull-left">Please signup.</h5>
				<h5 class="pull-right"><a href="/signin">Sign in</a></h5>
			    <input class="form-control" ng-class="usernameClass" type="text" placeholder="Username" ng-model="credential.username" required/>	
			    <input class="form-control" ng-class="passwordClass" type="password" placeholder="Password" ng-model="credential.password" required/>
			    <button class="btn btn-lg btn-primary btn-block" ng-click="create(credential)">Create Account</button>
			    <h4 ng-class="messageClass">{{ message }}</h4>
			</form>
		</div>
		
		<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.0-beta.15/angular.js"></script>
		<script src="/js/autofill-event.js"></script>
		
		<script type="text/javascript">
			var _csrfToken = "${_csrf.token}";
			var _csrfHeaderName = "${_csrf.headerName}";
			
			var signUp = angular.module('SignUp',[]);
			
			signUp.controller('SignupForm', function($scope, $http, $location) {
				$scope.messageClass = "hidden";
				
				// Setup CSRF token
				$http.defaults.headers.post[_csrfHeaderName] = _csrfToken;
				
				// Handle submit button click
				$scope.create = function(credential) {
					if (!credential || !credential.password || !credential.username) {
						return;
					}
					
					$http.post('/signup', credential).
				    	success(function(data, status, headers, config) {
				    		window.location.href = "/login?success";
				      	}).
				      	error(function(data, status, headers, config) {
				      		if (status == 409) {
					        	$scope.message = "Opps! That username is already in use!";
					        	$scope.messageClass = "bg-warning";
				      		} else if (status >= 500) {
				      			$scope.message = "We're sorry.  There seems to be a problem with our service."
				      			$scope.messageClass = "bg-danger";
				      		}
				      	});
				}
			});
			
		</script>
	</body>
</html>