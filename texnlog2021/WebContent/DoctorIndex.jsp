<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<title>Home</title>
<%@ include file="styling/navbar.jsp" %> 
<link rel="stylesheet" href="styling/as.css">
</head>
<body>
<%
response.setHeader("Cache-Control","no-cache"); //index page for everyone
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
if(session.getAttribute("uname")==null)
	response.sendRedirect("redirectionjsp.jsp");
	%>
<table>
<tr>
<td><h2>Welcome <%=session.getAttribute("uname")%></h2></td>
<td><h2> Your amka is:<%=session.getAttribute("id")%></h2></td>
</tr>
<tr>
<form action=addPatient.jsp>
<td><h2> Declare new Patient: </h2></td>
<td><input type=submit value="Click Here"> </td>
</form>
</tr>
<tr>
<form action=addAppointment.jsp>
<td><h2> Add appointment: </h2></td>
<td><input type=submit value="Click Here"> </form></td>
</tr>
<tr>
<form method=post action=viewPatientsHistoryServlet>
<td><h2> View patient's history: </h2></td>
<td><label for=id value="Patient's Amka">Patient's Amka</label></td>
<td><input type="number" id="id" name="id" required></td>
<td><input type=submit value="View"></td>
 </form>
 </tr>
</table>
<%@ include file="styling/footer.html" %>  
</body>
</html>