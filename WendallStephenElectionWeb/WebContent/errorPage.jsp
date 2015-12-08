<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
<title>errorPage</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

<h1>Election error</h1>
<p>Unfortunately, there is a problem: ${requestScope.message}</p>
<hr /><p>Please <a href="index.jsp">try again</a>.</p>
</body>
</html>