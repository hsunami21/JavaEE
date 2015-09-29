<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h3>Stocks currently trading</h3>
<table border = "1" >
<thead><tr>
<td width="100" bgcolor="yellow">Symbol</td><td width="100" bgcolor="yellow">Unit price</td>
</tr></thead>
<c:forEach items = "${requestScope.Market}" var="stock">
<tr><td>
<c:out value="${stock.symbol}" />
</td><td>
${stock.price}
</td>
</tr>
</c:forEach>
</table>
