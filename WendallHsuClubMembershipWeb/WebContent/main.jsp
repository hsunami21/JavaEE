<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>main</title>
</head>
<body>
<form method="get" action="${pageContext.request.contextPath}/login">
<p>Welcome <span style="font-weight:bold">${sessionScope.userName}</span>
<input type="submit" name="submit" value="Sign out">
<input type="submit" name="submit" value="Quit club">
</p>
</form>

<hr>
<p>For a real club, this page would show club news, have links to activities and much more. <br/>
For testing purposes lists all current members of the club.</p>
<c:forEach items="${members }" var="name" >
${name} <br/>
</c:forEach>
</body>
</html>