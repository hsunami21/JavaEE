<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<c:forEach var="names" items="${candidates}" varStatus="status">
	  <tr>
	      <td>${names}</td>
	      <td style="text-align:right">${votes[status.index]}</td>
	  </tr>
	</c:forEach>
	<tr>
		<td style="text-align:right">Total:</td>
		<td style="text-align:right">${total }</td>
	</tr>
</table>

<p>The number of votes cast is <span style="font-weight:bold">${voteCount }</span>.</p>

</body>
</html>