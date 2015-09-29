<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>index</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body bgcolor="#fefad8">
<h2>Very simplified stock trading game</h2>
<p>This over simplified stock market simulation lets you buy and sell stocks. 
Initially you have $1000 and no stocks. 
The program generates a selection of eight (8) hypothetical stocks 
with share prices between $0.00 and $200.00. 
Then you can buy what you can afford and sell what you own. </p>
<p>Stock prices are held in memory and change regularly to simulate market fluctuations. 
(It's all based on random numbers.)</p>
<p>After very transaction you see your portfolio and how your net worth has changed. </p>
<form action = "${pageContext.request.contextPath}/stockMarket" method=get>
<p>Click <input type="submit" name="start" value="Begin"> to start the game.</p>
</form>
</body>
</html>