<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.manage.FlightModel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		Object obj = request.getAttribute("flight");
		FlightModel flight = (FlightModel)obj;
		out.print("From " + "<b>" +flight.getFromLocation()+"</b>");
		out.print(" To "+"<b>" +flight.getToLocation() +"</b>" + "<br>");
		out.print(" There are " +"<b>" +flight.getTotalSeats()+"</b> seats available");
	
		if (flight.getTotalSeats() > 0) {
			
			%>
				<br>
				<a href="/mywebapp/book?id=<%=flight.getId()%>">Book</a>
			
			<%
		}else {
			out.print("No seats available");
		}
		%>

	
</body>
</html>