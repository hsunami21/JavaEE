<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrator Page</title>
</head>
<body>
<h1>Administrator Page</h1>
<table>
	<tr>
		<td>Candidate</td>
		<td>Votes</td>
	</tr>
	<tr>
		<td>Aakash Khan</td>
		<td style="text-align:right">${requestScope.candidate1 }</td>
	</tr>
	<tr>
		<td>Marion Wells</td>
		<td style="text-align:right">${requestScope.candidate2 }</td>
	</tr>
	<tr>
		<td>Damien Yu</td>
		<td style="text-align:right">${requestScope.candidate3 }</td>
	</tr>
	<tr>
		<td style="text-align:right">Total:</td>
		<td style="text-align:right">${requestScope.total }</td>
	</tr>
</table>

<p>The number of votes cast is <span style="font-weight:bold">${requestScope.total }</span>.</p>

</body>
</html>