<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!-- Header -->
<nav class="navbar navbar-expand-sm navbar-dark bg-dark sticky-top">
	<div class="container-fluid">
		<c:if
			test="${(sessionScope.name!=null) and (sessionScope.adminName==null)}">
			<a class="navbar-brand" href="../user/studentUi"><b>Enquiry
					Management System</b></a>
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link active"
					href="../user/studentUi">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="../user/logout">Logout</a></li>
			</ul>
		</c:if>
		<c:if
			test="${(sessionScope.name==null) and (sessionScope.adminName==null)}">
			<a class="navbar-brand" href="myhome"><b>Enquiry Management
					System</b></a>
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link active" href="myhome">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="loginPage">Login</a></li>
				<li class="nav-item"><a class="nav-link" href="register">Register</a></li>
				<li class="nav-item"><a class="nav-link" href="adminLoginPage">Admin
						Login</a></li>
			</ul>
		</c:if>
		<c:if
			test="${(sessionScope.name==null) and (sessionScope.adminName!=null)}">
			<a class="navbar-brand" href="adminUi"><b>Enquiry Management
					System</b></a>
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link active" href="adminUi">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="adminLogout">Logout</a></li>
			</ul>
		</c:if>
	</div>
</nav>
<!-- /Header -->