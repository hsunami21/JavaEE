<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<title>club home page</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="BlueHills.css" type="text/css">
</head>
<body>
<h3>Welcome to the Wendall Hsu Club</h3>
<p><b>Student Life</b> is an club that organizes activities and social events.</p>
<fieldset>
<p>If you are a member, please use the form below to sign in.</p>
<p><font color="red">${requestScope.message}</font></p>
<form method="post" action="${pageContext.request.contextPath}/login">
<table border="0">
		<tr>
			<td>User name</td>
			<td><input type="text" name="userName" size="12" maxlength="12"></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" name="password" size="12" maxlength="12"></td>
		</tr>
</table>
<input type="submit" name="login" value="Login">
</form>
</fieldset>
<fieldset>
<p>If you not a member, please <a href="${pageContext.request.contextPath}/register.jsp">Register to join</a>.</p>
</fieldset>
</body>
</html>