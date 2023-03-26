<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="component/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<link href="component/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<title>Index</title>
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
					<h2 class="headline">User Attendance Details</h2>
					<table id="UserAttendance" class="table table-bordered table-hover">
						<thead class="thead-light">
							<tr>
								<th scope="col">Username</th>
								<th scope="col">Current date</th>
								<th scope="col">Entry Time</th>
								<th scope="col">Exit Time</th>
								<th scope="col">Total Hours</th>
							</tr>
						</thead>
						<c:forEach items="${requestScope.records}" var="records">
							<tr>
								<td>${records.username}</td>
								<td>${records.currentDate}</td>
								<td>${records.entryTime}</td>
								<td>${records.exitTime}</td>
								<td>${records.totalHours}</td>
							</tr>
						</c:forEach>

					</table>

				</div>

			</form>

			<form action="MarkAttendance" method="POST">
				<br>
				<h4>Mark your Attendance</h4>
				<div align="center">
					<button type="submit" class="btn btn-primary" id="punchIn"
						name="punchIn">Punch-In</button>
					<input type="hidden" name="uname" value="<%= request.getAttribute("uname") %>">
					<button type="submit" class="btn btn-primary" id="punchOut"
						name="punchOut">Punch-Out</button>
				</div>
			</form>

		</div>
	</div>



</body>
</html>