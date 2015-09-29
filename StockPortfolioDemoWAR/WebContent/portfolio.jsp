<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="total" value="0"/>
<table border = "1" >
<thead>
<tr>
<td width="100" bgcolor="yellow">Holding</td>
<td width="100" bgcolor="yellow">Quantity</td>
<td width="100" bgcolor="yellow">Value</td>
</tr>
</thead>
<c:forEach items="${sessionScope.portfolioManager.portfolio}" var="sh">
<tr><td>
${sh.symbol}
</td><td>
<c:if test="${sh.symbol=='Cash'}" >&nbsp;</c:if>
<c:if test="${sh.symbol!='Cash'}">${sh.quantity}</c:if>
</td><td>
<c:choose>
<c:when test="${sh.symbol=='Cash'}" >
	<c:set var="worth">${sh.buyValue}</c:set>
</c:when>
<c:otherwise>   
	<c:set var="worth">${sh.buyValue}</c:set>
   <c:forEach items = "${requestScope.Market}" var="stock">
   <c:if test="${stock.symbol==sh.symbol}">
    <c:set var="worth">${stock.value}</c:set>
    </c:if>
   </c:forEach> 
	<c:set var="worth">${sh.quantity * worth }</c:set>
</c:otherwise>
</c:choose>
<c:set var="total" value="${total+worth}"/>
<fmt:formatNumber type="currency" >${worth}</fmt:formatNumber>
</td>
</tr>
</c:forEach>
<tr>
<td><b>Total</b></td>
<td> &nbsp;</td>
<td><b><fmt:formatNumber type="currency" >${total}</fmt:formatNumber></b></td>
</tr>
</table>
