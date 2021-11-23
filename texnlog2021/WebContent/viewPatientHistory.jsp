<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cancel Appointment</title>
<%@ include file="styling/navbar.jsp" %>  
<link rel="stylesheet" href="styling/as.css">

</head>
<body>
<%response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
if(session.getAttribute("uname")==null)
	response.sendRedirect("redirectionjsp.jsp");
	 %>
<%@ include file="styling/footer.html" %>  
</body>
</html>