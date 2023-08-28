<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Student Dashboard</title>
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
}
</style>
</head>
<body>

	<!-- Header -->
	<c:import url="header.jsp" />
	<!-- /Header -->

	<c:if test="${sessionScope.name==null}">
		<c:redirect url="myhome" />
	</c:if>

	<c:if test="${sessionScope.name!=null}">
		<h4 align="right" style="padding-top: 20px; padding-right: 20px;">Welcome:
			${sessionScope.name}</h4>
	</c:if>

	<c:if test="${sessionScope.name!=null}">
		<!-- Body -->
		<!-- Enquiry Form -->
		<h1 align="center">Enquiry Form</h1>
		<div class="container">
			<form action="../enquiry/addEnquiry" method="post">
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
					<textarea class="form-control" name="enquiry" rows="4"></textarea>
				</div>
				<button type="submit" class="btn btn-primary">Enquire!!</button>
			</form>
		</div>
		<!-- /Enquiry Form -->
		<!-- Enquiries -->
		<h1 align="center">Enquiries</h1>
		<br>
		<div class="container">
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Sr. No.:</th>
						<th scope="col">Enquiry ID:</th>
						<th scope="col">Course Name:</th>
						<th scope="col">Enquiry:</th>
						<th scope="col">Enquiry Date:</th>
						<th scope="col">Edit | Delete:</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="enquiry" items="${enquiries}" varStatus="v">
						<tr>
							<th scope="row">${v.count}</th>
							<td>${enquiry.enqId}</td>
							<td>${enquiry.courseName}</td>
							<td>${enquiry.enquiry}</td>
							<td>${enquiry.enquiryDate}</td>
							<td><a href="../enquiry/editEnquiry?enqId=${enquiry.enqId}">Edit</a> | <a
								href="../enquiry/deleteEnquiry?enqId=${enquiry.enqId}"
								onclick="if(!(confirm('Are you sure you want to delete'))) return false">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!-- /Enquiries -->
		<div class="container" style="height: 50px;"></div>
		<!-- /Body -->
	</c:if>

	<!-- Footer -->
	<c:import url="footer.jsp" />
	<!-- /Footer -->

</body>
</html>