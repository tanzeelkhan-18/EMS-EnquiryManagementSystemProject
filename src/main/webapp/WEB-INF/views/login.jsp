<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Login</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
a {
	text-decoration: none;
	color: aliceblue;
}
</style>
</head>
<body>

	<!-- Header -->
	<c:import url="header.jsp" />
	<!-- /Header -->

	<c:if test="${loginError!=null}">
		<div class="container m-5">
			<h6 class="text-danger">${loginError}</h6>
		</div>

	</c:if>

	<c:if test="${sessionScope.name==null}">
		<div class="container m-5 p-5">
			<h1>Login Form</h1>
			<form action="authenticate" method="post">
				<div class="mb-3 col-sm-5">
					<label for="userName" class="form-label">User Name:</label> <input
						type="text" class="form-control" name="userName">
				</div>
				<div class="mb-3 col-sm-5">
					<label for="password" class="form-label">Password</label> <input
						type="password" class="form-control" name="password">
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form>
		</div>
	</c:if>

	<c:if test="${sessionScope.name!=null}">
		<c:redirect url="user/studentUi"></c:redirect>
	</c:if>

	<!-- Footer -->
	<c:import url="footer.jsp" />
	<!-- /Footer -->

</body>
</html>