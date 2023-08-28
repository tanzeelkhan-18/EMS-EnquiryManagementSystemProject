<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Home Page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
a{
	text-decoration: none;
	color: aliceblue;
}
</style>
</head>
<body>

	<!-- Header -->
	<c:import url="header.jsp"/>
	<!-- /Header -->

	<c:if test="${sessionScope.name==null}">
		<div class="container m-5 p-5">
			<h1>Welcome to Enquiry Management System</h1>
			<h3>Please Login or Register</h3>
			<br><br>
		</div>
	</c:if>

	<c:if test="${sessionScope.name!=null}">
		<c:redirect url="../user/studentUi"></c:redirect>
	</c:if>

	<!-- Footer -->
	<c:import url="footer.jsp"/>
	<!-- /Footer -->

</body>
</html>