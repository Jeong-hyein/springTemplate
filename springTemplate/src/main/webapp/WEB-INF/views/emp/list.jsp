<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title></title>
</head>
<body>
<c:forEach items="${empList}" var="emp">
	first_name: ${emp.first_name}
	last_name: ${emp.last_name}
	email: ${emp.email}
	hire_date: ${emp.hiredate} 
	<br/>
</c:forEach>
</body>
</html>