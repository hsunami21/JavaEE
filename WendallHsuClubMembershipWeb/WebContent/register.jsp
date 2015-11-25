<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>register</title>
</head>
<body>
<h1>Wendall Hsu Club</h1>
<p>Please complete the form and click <span style="font-weight:bold">Register</span> to join the club.
<fieldset>
<p><font color="red">${requestScope.message}</font></p>
<form method="post" action="${pageContext.request.contextPath}/register">
<table border="0">
		<tr>
			<td>User name</td>
			<td><input type="text" name="userName" size="12" maxlength="12"></td>
		</tr>
		<tr>
			<td>First name</td>
			<td><input type="text" name="firstName" size="20" maxlength="20"></td>
		</tr>
		<tr>
			<td>Last name</td>
			<td><input type="text" name="lastName" size="20" maxlength="20"></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" name="password" size="12" maxlength="12"></td>
		</tr>
</table>
<input type="submit" name="login" value="Register">
</form>
</fieldset>

</body>
</html>