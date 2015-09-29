<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isErrorPage="true"%>
<html>
<head>
<title>JSPerror</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<h2>An Exception has occurred</h2>
<h3>requestScope </h3>
<p>Exception type: ${exception.class.name } </p>
<p>${exception.message }</p>
<h3>pageContext </h3>
<p>Exception type: ${pageContext.exception.class.name } </p>
<p>${pageContext.exception.message }</p>
<form action = "${pageContext.request.contextPath}/stockMarket" method="get">
<input type="submit" name="update" value="Continue"> 
</form>

</body>
</html>