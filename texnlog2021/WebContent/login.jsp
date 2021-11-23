<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<%@ include file="styling/navbar.jsp" %>  
</head>
<link rel="stylesheet" href="styling/as.css">
<body>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);

if(session.getAttribute("uname")==null){ //if session exists redirect to Index otherwise print a login form
	%>
	<h1 style=color:white>Login:</h1><br><br>
	<table>
	<form method="post" action="loginServlet"> 
	<tr><td> Name:</td>
	<td><input type=text value=Username name=uname required> </td></tr>
	<tr><td> Password:</td>
	<td><input type=password value=Password name =password required> </td></tr>
	<tr><td><input type=submit value=login></td></tr>
	</form> <%
} else{
	response.sendRedirect("redirectionjsp.jsp");
}%>
<tr><td><a style=color:black; href=Register.jsp>Not have an account? Click here!</a></td></tr>
</table>
<%@ include file="styling/footer.html" %>  

</body>
</html>