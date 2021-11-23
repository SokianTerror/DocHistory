<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Redirecting...</title>
</head>
<body>
<p> welcome </p>
<%
if(session.getAttribute("uname")==null)
	response.sendRedirect("index.jsp");
else
	response.sendRedirect("DoctorIndex.jsp");

%>
</body>
</html>