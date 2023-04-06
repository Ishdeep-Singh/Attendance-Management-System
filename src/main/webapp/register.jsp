<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New User</title>
<link href="component/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<link href="component/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	
	<div class="row">
		<div class="col-sm-6">
			<img src="images/Student.png" height="440" align="middle" />
		</div>
		<% if(request.getAttribute("rowsAffectedUser")!=null && request.getAttribute("rowsAffectedEmployee")!=null){ %>
		<h2>User registered successfully</h2>
		<% } else if(request.getAttribute("error")!=null) { %>
		<h2>ID already exists. Try entering new one.</h2>
		<% } else {%>
		<div class="col-sm-4">
			<h1 align="center">Register</h1>
			<form action="RegisterUser" method="POST">
				<label class="form-label">Name</label><br> <input type="text"
					name="name" /> <br> <label class="form-label">Email</label><br>
				<input type="text" name="email" /> <br> <label
					class="form-label">ID</label><br> <input type="text"
					name="uname" required /> <br> <label class="form-label">Password</label><br>
				<input type="password" name="password" required /> <br> <label
					class="form-label">Age</label><br> <input type="text"
					name="age" /> <br> <label class="form-label">Gender</label> <br>
				<input type="text" name="gender" /> <br> <label
					class="form-label">Address</label> <br> <input type="text"
					name="address" /> <br> <label class="form-label">Department</label>
				<br> <input type="text" name="department" /> <br> <br>
				<input class = "btn btn-primary" type="submit" value=Submit />
			</form>

		</div>
		<% } %>

	</div>
</body>
</html>