<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Cookie[] username = request.getCookies();
	String user = null;
	String sessionID = null;
	if (username != null) {

		for (Cookie cookie : username) {
			if (cookie.getName().equals("user"))
				user = cookie.getValue();
			if (cookie.getName().equals("JSESSIONID"))
				sessionID = cookie.getValue();

		}

	}
%>



<h3>
		Hi,
		<%
		out.print(user);
	%>
	</h3>

	<form action="available" method="get">
		<input type="text" placeholder="From" name="from" list="from" required>
		<datalist id="from">
			<option value="Boston">
			<option value="New York">
			<option value="New Jersey">
			<option value="San Francisco">	
		</datalist>

		<input type="text" placeholder="To" name="to" list="to" required>
		<datalist id="to">
			<option value="Boston">
			<option value="New York">
			<option value="New Jersey">
			<option value="San Jose">
			<option value="San Francisco">
			
		</datalist>
		<input type="submit" value="Check" />
	</form>

	<form action="logout" method="get">

		<input type="submit" value="Log out">

	</form>

</body>
</html>