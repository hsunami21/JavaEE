<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update course</title>
</head>
<body><body>
<%@ include file="banner.jsp" %>
<h2>Enter new values to update a course</h2>
<form action="${pageContext.request.contextPath}/update" method ="post">
<table>
<tr>
<td>Course code</td>
<td><b>${sessionScope.course.courseCode}</b></td>
</tr><tr>
<td>Course title</td>
<td>
<input size="40" type="text" name="courseTitle" value="${sessionScope.course.courseTitle}" /> 
</td>
</tr><tr>
<td>Capacity</td>
<td>
<input type="text" name="capacity" value="${sessionScope.course.capacity}" /> 
</td>
</tr><tr>
<td>Enrolled</td>
<td>
${sessionScope.course.enrolled} 
</td>
</tr><tr>
<td colspan = "2">
<font color="blue">
To change professor, clear the box below and select the new professor from the dropdown list.<br/>
To assign a professor not on the drop down list, enter a first name and last name in the box.<br/>
The name in the box overrides the dropdown list.
</font>
</td>
</tr><tr>
<td> Professor name</td>
<td>
<input size="40" type="text" name="profName" value="${sessionScope.course.professor}" />
</td>
</tr><tr>
<td>Select Professor</td>
<td>
<select name="profId" >
<option value="TBA" >Not assigned</option>
<c:forEach items="${applicationScope.professors}" var="prof">
<c:choose>
  <c:when test ="${prof.profId == sessionScope.professor.profId }">
     <option value="${prof.profId}" selected="selected" >${prof}</option>
  </c:when>
  <c:otherwise>  
     <option value="${prof.profId}" >${prof}</option>
  </c:otherwise>
  </c:choose>
</c:forEach>
</select>
</td></tr>
</table>
<input type="submit" name="submit" value = "Update" />
<input type="reset" name="reset" value ="Reset form" />
<input type="submit" name="submit" value="Cancel" />
</form>
</body>
</html>