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
<title>Add Appointment</title>
<%@ include file="styling/navbar.jsp" %>
</head>
<body>
<h1 style=color:white>Add Appointment:</h1><br><br>

<form method=post action=addAppointmentServlet>
<table>
<tr><td><label for="date">Date of Appointment:</label></td>
  <td><input type="date" id="date" name="date" value="today" required></td> </tr>
  <tr>
  <td><label for="amka">Patient's Amka:</label> </td>
  <td><input type="number" id="amka" name="amka" required></td> </tr>
  <tr>
  <td><label for="disease">Disease:</label></td>
  <td><input type="text" id="disease" name="disease" required></td></tr>
  <tr>
  <td><label for="cure">Cure:</label></td>
  <td><input type="cure" id="cure" name="cure" required></td></tr>
  <tr>
  <td><label for="medex">Medical examination:</label></td>
  <td><input type="medex" id="medex" name="medex" required></td></tr>
  <tr>
  <td><label for="dod">Date that disease started:</label></td>
 <td> <input type="date" id="dod" name="dod" required></td></tr>
  <tr><td><input type="submit"></td></tr>
</table>
</form>
</tr></table>
</div>
<%@ include file="styling/footer.html" %>  
</body>
</html>