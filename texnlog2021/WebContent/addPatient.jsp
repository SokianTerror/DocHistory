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
<link rel="stylesheet" href="styling/as.css">
<meta charset="UTF-8">
<title>Add Patient</title>
<%@ include file="styling/navbar.jsp" %>  
</head>
<body>
<%
response.setHeader("Cache-Control","no-cache"); //index page for everyone
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
if(session.getAttribute("uname")==null ){
	response.sendRedirect("redirectionjsp.jsp");
}%>
<h1 style=color:white>Add Patient:</h1><br><br>
<table>
<form method=post action="addPatientServlet">
<tr><td>
  <label for="amka">Amka:</label></td>
  <td><input type="number" id="amka" name="amka" required></td></tr>
  <tr>
  <td><label for="fn">Fullname:</label></td>
  <td><input type="text" id="fn" name="fn" required></td>
  <tr>
  <td><label for="date">Birth Date:</label></td>
  <td><input type="date" id="date" name="date" required></td>
  <tr>
  <td><label for="email">Email:</label></td>
  <td><input type="email" id="email" name="email" required></td></tr>
  <tr>
  <td><label for="tel">Telephone:</label></td>
  <td><input type="text" id="tel" name="tel" minlength="10" maxlength="10" required></td></tr>
  <tr><td>
  <input type="submit" value="Submit"></td></tr>
</form>
</table>
<%@ include file="styling/footer.html" %>  

</body>
</html>