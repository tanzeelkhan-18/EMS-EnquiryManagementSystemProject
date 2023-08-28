<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Admin Dashboard</title>
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
	color: #33b5e5;
}
</style>
</head>
<body>

	<!-- Header -->
	<c:import url="header.jsp" />
	<!-- /Header -->

	<c:if test="${sessionScope.adminName==null}">
		<c:redirect url="myhome" />
	</c:if>

	<c:if test="${sessionScope.adminName!=null}">
		<div class="container">
			<h4 align="right" style="padding-top: 20px; padding-right: 20px;">Welcome:
				${sessionScope.adminName}</h4>
			<!-- Enquiries -->
			<br>
			<h1 align="center">Manage Enquiries</h1>
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
						<c:forEach var="enquiry" items="${adminEnquiries}" varStatus="v">
							<tr>
								<th scope="row">${v.count}</th>
								<td>${enquiry.enqId}</td>
								<td>${enquiry.courseName}</td>
								<td>${enquiry.enquiry}</td>
								<td>${enquiry.enquiryDate}</td>
								<td><a href="../enquiry/editEnquiry?enqId=${enquiry.enqId}">Edit</a>
									| <a href="../enquiry/deleteEnquiry?enqId=${enquiry.enqId}"
									onclick="if(!(confirm('Are you sure you want to delete'))) return false">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- /Enquiries -->
		</div>
	</c:if>


	<!-- Footer -->
	<c:import url="footer.jsp" />
	<!-- /Footer -->

</body>
</html>