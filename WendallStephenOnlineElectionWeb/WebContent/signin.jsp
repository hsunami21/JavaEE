<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Voting Page</title>
</head>
<body>
<h1>Please click to vote for one of:</h1>

<form action="${pageContext.request.contextPath}/vote" method="post">
<table>
	<tr>
		<td><input type="button" value="Aakash Khan"></td>
		<td><input type="button" value="Marion Wells"></td>
		<td><input type="button" value="Damien Yu"></td>
	</tr>
</table>
</form>
</body>
</html>