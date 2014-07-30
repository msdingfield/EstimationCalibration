<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="estimation-calibrator" lang="en" ng-app="EstimationCalibrator">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="_csrf" content="${_csrf.token}"/>
        <meta name="_csrf_header" content="${_csrf.headerName}"/>
        
        <link rel="stylesheet" href="/css/normalize.css">
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="/css/common.css">
        
		<title>Estimation Calibrator</title>
	</head>
	<body>

		<div ng-view></div>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.0-beta.15/angular.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.0-beta.15/angular-route.js"></script>
		<script src="/js/ui-bootstrap-tpls.0.11.0.min.js"></script>
		<script src="/js/autofill-event.js"></script>
		<script src="/js/estimation-calibrator.js"></script>
		<script type="text/javascript">
			
			
			window._csrfToken = "${_csrf.token}";
			window._csrfHeaderName = "${_csrf.headerName}";
			window._csrfParameterName = "${_csrf.parameterName}";
			
		</script>
	</body>
</html>
