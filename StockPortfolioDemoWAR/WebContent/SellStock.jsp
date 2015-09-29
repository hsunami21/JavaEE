<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>SellStock</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body bgcolor="#fefad8">
<h2>Sell Stock Page</h2>
<h3>Your portfolio has a cash balance of 
<c:set var="cash">${sessionScope.portfolioManager.cash}</c:set>
<fmt:formatNumber type="currency" >${cash}</fmt:formatNumber> 
</h3>
Please enter the quantity to the right of a stock and click <b>Sell</b> in the table below or 
<form action="${pageContext.request.contextPath}/sell" method="post">
<input type="submit" name="command" value="cancel"> to go back.
</form>
<table border="1">
	<thead>
		<tr>
			<th bgcolor="yellow">Holding</th>
			<th bgcolor="yellow">Quantity @ <br/>Unit price</th>
			<th bgcolor="yellow">Current value</th>
			<th bgcolor="yellow">Sell Quantity<br/>One trade only</th>
		</tr>
	</thead>
	<c:set var="total">0</c:set>
	<c:forEach items="${sessionScope.portfolioManager.portfolio}" var="sh">
		<c:if test="${sh.symbol != 'Cash'}">
		<tr>
			<td>${sh.symbol}</td>
			<td>${sh.quantity} @
						<c:forEach items="${requestScope.Market}" var="stock">
							<c:if test="${sh.symbol == stock.symbol}">
								<c:set var ="worth">${stock.value}</c:set>
								<fmt:formatNumber type="currency" >${worth}</fmt:formatNumber>
							</c:if>
			 			</c:forEach>
			 </td><td>			
				<fmt:formatNumber type="currency" >${sh.quantity * worth}</fmt:formatNumber>
	<c:set var="total">${total + sh.quantity * worth }</c:set>				
			</td>
			<td>
					<form action="${pageContext.request.contextPath}/sell" method="post">
						<input type="hidden" name="symbol" value="${sh.symbol}" />
						<input type="text" size = "4" name="quantity" />
						<input type="submit" name="command" value="Sell" />
					</form>
			</td>
		</tr>
		</c:if>
	</c:forEach>
	<tr><td bgcolor="yellow"><b>Total</b></td>
	<td bgcolor="yellow"><fmt:formatNumber type="currency" >${total+cash}</fmt:formatNumber><br/>including cash</td>
	<td bgcolor="yellow"><fmt:formatNumber type="currency" >${total}</fmt:formatNumber><br/>stocks only</td>
	<td bgcolor="yellow">&nbsp;</td> </tr>
</table>
</body>
</html>