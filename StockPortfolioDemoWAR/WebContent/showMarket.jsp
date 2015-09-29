<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<title>showMarket</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body bgcolor="#fefad8">
<h2>Stock Market Simulation</h2>
<%@ include file="stockListings.jsp" %> 
<h3>Your portfolio</h3>
<%@ include file="portfolio.jsp" %>
<table>
<tr><td width="150">
<form action = "${pageContext.request.contextPath}/buy" method="get">
<input type="submit" name="buy" value="Buy stock"> 
</form>
</td><td><form action = "${pageContext.request.contextPath}/stockMarket" method="get">
<input type="submit" name="update" value="Get latest values"> 
</form></td></tr>
<c:if test="${fn:length(sessionScope.portfolioManager.portfolio) > 1}">
<tr><td>
<form action = "${pageContext.request.contextPath}/sell" method="get">
<input type="submit" name="sell" value="Sell stock"> 
</form></td>
<td bgcolor="red">
<form action = "${pageContext.request.contextPath}/stockMarket" method="post">
<input type="submit" name="update" value="Start over"> 
</form>
</td></tr>
</c:if>
</table>
</body>
</html>