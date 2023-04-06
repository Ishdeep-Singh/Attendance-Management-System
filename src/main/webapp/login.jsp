<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="component/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<link href="component/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<title>Login</title>
</head>
<body>
	
	<div class = "row">
		<div class = "col-sm-6">
			<img src="images/Student.png" height = "440" align = "middle"/>
		</div>
		<div class = "col-sm-4">
			<form method = "POST" action="LoginValidation">
			<h1 align = "center">Login</h1>
				<div align = "left">
					<label class = "form-label">ID</label>
					<input class = "form-control" type = "text" id = "uname" name = "uname" placeholder = "Username">
				</div>
				<br>
				<div align = "left">
					<label class = "form-label">Password</label>
					<input class = "form-control" type = "password" id = "password" name = "password" placeholder = "Password">
				</div>
				<br>
				<div align = "center">
					<input type = "submit" class = "btn btn-success" id = "submit" name = "submit" value = "Submit">
					<input type = "submit" class = "btn btn-danger" id = "reset" name = "reset" value = "Reset">
				</div>
			
			</form>
			<form action = "RegisterUser" method = "GET">
			<br>
			<div align = "center">
			<input type = "submit" class = "btn btn-primary" id = "newuser" name = "newuser" value = "New User">
			</div>
			</form>
		</div>
		
	</div>
</body>
</html>