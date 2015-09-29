<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="JSPerror.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<title>StockError</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body bgcolor="#fefad8">
<h3>An error has occurred</h3>
<p>Exception type: ${requestScope.exception } </p>
<p>Reason: ${requestScope.exception.message }</p>
<form action = "${pageContext.request.contextPath}/stockMarket" method="get">
<input type="submit" name="update" value="Continue"> 
</form>
</body>
</html>