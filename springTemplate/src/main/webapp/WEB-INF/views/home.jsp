<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
<head>
	<title>Home</title>
</head>
<body>
${evo.firstName} :
${evo.lastName}
<%-- ${param.lastName} requestparam의 값을 보려면--%>
<h1>
	Hello world!  
	
</h1>

<P>  The time on the server is ${serverTime}. </P>
<a href="empList.do">사원목록</a>
</body>
</html>
