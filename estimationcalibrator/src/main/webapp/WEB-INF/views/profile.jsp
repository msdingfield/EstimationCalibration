<html>
	<head>
		<title>Estimation Calibrator | User Profile</title>
	</head>
	<body>
		<h1>Hello, ${userProfile.username}.</h1>
	
		<form action="/logout" method="post">
			<button type="submit">Logout</button>
		    <input type="hidden"                        
		       name="${_csrf.parameterName}"
		       value="${_csrf.token}"/> 
		</form>
	</body>
</html>
