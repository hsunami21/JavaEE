<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a course</title>
</head>
<body>
<h2>Add a new course</h2>
<%@ include file="banner.jsp" %><br><br>
<form action="${pageContext.request.contextPath}/add" method="post">
<table>
<tr>
<td>Course code:</td>
<td><input type="text" name="courseCode" value=""></td>
</tr><tr>
<td>Course title</td>
<td><input size="40" type="text" name="courseTitle" value=""></td>
</tr><tr>
</tr><tr>
<td>Capacity</td>
<td>
<input type="text" name="capacity" value="25" /> 
</td>
</tr><tr>
<td>Enrolled</td>
<td>
0
</td>
</tr><tr>
<td valign="top">Professor</td>
<td>Select "TBA" for to be assigned
<select name="profName">
<option value="TBA" selected> TBA </option>
<c:forEach items="${applicationScope.professors}" var="prof">
<option value="${prof.profId}">${prof}</option>
</c:forEach>
</select>
</td>
</tr><tr>
<td colspan = "2">
<font color="blue">
To create a new professor, first create the course with professor TBA.<br/> 
You can specify a new professor when you update the course.
</font>
</td>
</table>
<input type="submit" name="add" value="Add">
<input type="submit" name="cancel" value="Cancel">
</form>
</body>
</html>