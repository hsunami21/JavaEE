<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Clock web welcome page</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<h1>Welcome to Wendall's Clock</h1>
<form action="${pageContext.request.contextPath}/clock" method="get">
Click to see the current date and time
<input type="submit" name="getTime" value="Get Time">
</form>
</body>
</html>