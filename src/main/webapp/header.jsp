<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="component/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<link href="component/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<header id = "head" class = "header">
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">Attendance Management System</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item active">
				<a class="nav-link" href="LoginValidation">Attendance</a>
				</li>
				<li class="nav-item">
				<form action = "Employees" method = "POST">
					<input type="hidden" name="param1" value="param1Value">
					<button class="nav-link" value = "Employees">Employees</button>
				</form>
				</li>
				
				<li class="nav-item"><a class="nav-link" href="error" disabled>Error</a></li>
				
			</ul>
		</div>
	</nav>
	</header>
</body>
</html>