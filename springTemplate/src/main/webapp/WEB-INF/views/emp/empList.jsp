<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title></title>
</head>
<body>
<h3>사원목록</h3>
<c:forEach items="${empList}" var="emp">
	<img src="download?name=${emp.profile}" style="width:80px" onerror="this.src='resources/common.jpg'"><br/>
	${emp.firstName} 
	${emp.lastName} 
	${emp.employeeId} <br/>
	<a href="report.do" target="_blank">PDF</a> <br/><br/> <!-- target="_blank": 새탭, 같이 보게는? -->
</c:forEach>

</body>
</html>