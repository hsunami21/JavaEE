<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>login</title>
</head>
<body>
<h2>Course Catalog Sign-in Page</h2>
<p>No authentication is required but please enter your name:
<font color="red"> ${requestScope.message}</font></p>
<form name="login"
		action="${pageContext.request.contextPath}/login" method="post">
<table border = 0>
<tr>
<td> User name:</td>
<td><input type="text" id="userName" name="userName"/></td>	
</tr><tr>
<td>&nbsp;</td>
<td><input name="signin" value="Sign in" type="submit"></td>
</tr>
</table>
</form>
</body>
</html>