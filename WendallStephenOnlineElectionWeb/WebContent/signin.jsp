<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<c:forEach items="${candidates }" var="name" >
	<td><input type="submit" name="Vote" value="${name}"/> </td>
	</c:forEach>
</tr>
</table>
</form>
</body>
</html>