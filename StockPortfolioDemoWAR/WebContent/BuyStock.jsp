<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>BuyStock</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body bgcolor="#fefad8">
<h2>Buy Stock Page</h2>
<h3>Your portfolio has a cash balance of 
<fmt:formatNumber type="currency" >${sessionScope.portfolioManager.cash}</fmt:formatNumber> 
</h3>
Please enter the quantity to the right of a stock and click <b>Buy</b> in the table below or 
<form action="${pageContext.request.contextPath}/buy" method="post">
<input type="submit" name="command" value="cancel"> to go back.
</form>
<h3>Stocks currently trading</h3>
<table border="1">
	<thead>
		<tr>
			<th bgcolor="yellow">Symbol</th>
			<th bgcolor="yellow">Unit price</th>
			<th bgcolor="yellow">Buy Quantity<br/>One trade only</th>
		</tr>
	</thead>
	<c:forEach items="${requestScope.Market}" var="stock">
		<tr>
			<td><c:out value="${stock.symbol}" /></td>
			<td><c:out value="${stock.price}" /></td>
			<td>
				<form action="${pageContext.request.contextPath}/buy" method="post">
					<input type="hidden" value="${stock.symbol}" name="symbol" />
					<input type="text" size ="4" name="quantity" />
					<input type="submit" name="command" value="Buy" />
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>