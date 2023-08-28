<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Update Bookings Details</title>
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
	color: green;
}
</style>
</head>
<body>

	<!-- Header -->
	<c:import url="header.jsp" />
	<!-- /Header -->
	<br>

	<h1 align="center">Update Enquiry</h1>
	<div class="container">
		<form action="updateEnquiry" method="post">
			<div class="mb-3 col-sm-5">
				<label for="enqId" class="form-label">Enquiry ID:</label> <input
					type="text" class="form-control" name="enqId" value='${enq.enqId}'
					readonly="readonly">
			</div>
			<div class="mb-3 col-sm-5">
				<label for="courseName" class="form-label">Course Name:</label> <select
					class="form-select" name='courseName'>
					<option value="None">None</option>
					<option value="BSc.">BSc.</option>
					<option value="MSc.">MSc.</option>
					<option value="BTech.">BTech.</option>
					<option value="MTech.">MTech.</option>
					<option value="Chemistry">Chemistry</option>
					<option value="Physics">Physics</option>
					<option value="Mathematics">Mathematics</option>
					<option value="Biology">Biology</option>
				</select>
			</div>
			<div class="mb-3 col-sm-5">
					<label for="enquiry" class="form-label">Enquiry:</label>
					<textarea class="form-control" name="enquiry" rows="4" placeholder="${enq.enquiry}"></textarea>
				</div>
			<button type="submit" class="btn btn-primary">Update</button>
		</form>
	</div>

	<!-- Footer -->
	<c:import url="footer.jsp" />
	<!-- /Footer -->

</body>
</html>