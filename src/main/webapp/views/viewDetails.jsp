<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Details Page</title>
<style type="text/css">
	h1{color:blue;}
	th{background-color: orange; color: white;text-align: center;}
	td{text-align: center;}
	a{text-decoration: none;}
</style>
</head>
<body>
	<div align="center">
		<h1>View Plan Details</h1>
		<table border="2" width="80%" cellpadding="2">
			<tr>
				<th>PLAN_Id</th>
				<th>PLAN_Name</th>
				<th>PLAN_STATUS</th>
				<th>START_DATE</th>
				<th>END_DATE</th>
				<th>BENEFIT_AMOUNT</th>
			</tr>
			<c:forEach var="p" items="${list}">
				<tr>
					<td>${p.plan_id}</td>
					<td>${p.plan_name}</td>
					<td>${p.plan_status}</td>
					<td>${p.plan_sdate}</td>
					<td>${p.plan_edate}</td>
					<td>${p.benefit_amt}</td>
				</tr>
			</c:forEach>

		</table>
		<br>
		<h3>
			<a href="download/excelreport">Excel</a> &nbsp;&nbsp;&nbsp;&nbsp; 
			<a href="download/pdfreport">PDF</a>
		</h3>
	</div>
</body>
</html>