<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error</title>
<link href="component/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<link href="component/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="row">
		<div class="col-sm-4">
			<form>
				<h2>
					<div align="center">
						<%
						if (request.getAttribute("error") != null) {
						%>
						<%=request.getAttribute("error")%><br />
						<%
						}
						%>
					</div>
				</h2>
			</form>
		</div>
	</div>
</body>
</html>