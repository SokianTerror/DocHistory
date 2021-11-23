<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
/* Chrome, Safari, Edge, Opera */
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

/* Firefox */
input[type=number] {
  -moz-appearance: textfield;
}
</style>
<meta charset="UTF-8">
<title>Register</title>
<link rel="stylesheet" href="styling/as.css">
<%@ include file="styling/navbar.jsp" %>
</head>
<body>
<h1 style=color:white>Register:</h1>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);

if(session.getAttribute("uname")==null){ //if session exists redirect to Patient Index otherwise print a login form
	%><br><br>
	<table>
	<form method="post" action="registerServlet"> 
	<tr>
	<td><label for="amka"> Amka:</label></td>
	<td><input type=number  name=amka required></td> </tr>
	<tr><td><label for="username"> Username:</label></td>
	<td><input type=text value=username name=username required></td></tr>
	<tr><td><label for="password"> Password:</label></td>
	<td><input type=password value=Password name =password required> </td></tr>
	<tr><td><input type=submit value=register></td></tr>
	</form><%
} else{
	response.sendRedirect("PatientIndex.jsp");
}%>
<tr><td><a style=color:black; href=login.jsp>Already have an account? Click here!</a></td></tr>
</table>
<%@ include file="styling/footer.html" %>  
</body>
</html>