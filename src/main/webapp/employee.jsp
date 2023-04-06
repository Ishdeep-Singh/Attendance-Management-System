<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employees</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="row">
		<div class="col-sm-6">
			<h2>Welcome to Attendance Management System</h2>
			<img src="images/Student.png" height="440" />
		</div>
		<div class="col-sm-6">
			<form>
				<div class="container">
					<h2 class="headline">Employees List</h2>
					<table id="UserAttendance" class="table table-bordered table-hover" >
						<thead class="thead-light">
							<tr>
								<th scope="col">ID</th>
								<th scope="col">Name</th>
								<th scope="col">Age</th>
								<th scope="col">Gender</th>
								<th scope="col">Address</th>
								<th scope="col">Department</th>
								<th scope="col">Email</th>
							</tr>
						</thead>
						<c:forEach items="${requestScope.records}" var="records">
							<tr>
								<td>${records.username}</td>
								<td>${records.name}</td>
								<td>${records.age}</td>
								<td>${records.gender}</td>
								<td>${records.address}</td>
								<td>${records.department}</td>
								<td>${records.email}</td>
							</tr>
						</c:forEach>

					</table>

				</div>

			</form>
		</div>
	</div>



</body>
</html>