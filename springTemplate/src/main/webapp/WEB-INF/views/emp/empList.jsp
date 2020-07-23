<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<title></title>

<script>
$(function(){
	$(".empId").on("click", function() {
		var empid = $(this).html(); // this: 클릭한거를 가르킴
		var url = "getEmp/" + empid;
		//$("#getEmpDiv").load(url) -> html 페이지에 바로 나오게한다 
		//두번째는 서버로 넘겨줄 값: employeeId? + empid도 가능
/*  		$.getJSON("getEmpAjax", {employeeId:empid}, function(result) { //result는 controller의 리턴값
			$(".row").find(".col").eq(0).html(result.employeeId); //row를 찾고 그중 col을 찾고 그중 첫번째
			$(".row").find(".col").eq(1).html(result.firstName);
			$(".row").find(".col").eq(2).html(result.email);
			$(".row").find(".col").eq(3).html(result.hireDate);
		}) ; */
		$.ajax({
			url: "getEmpAjax",			//url
			data: {employeeId:empid},
			method: 'get',    			//getJson에서 get
			dataType: 'json',			//getJson에서 Json
/* 			success: function(result) { //result는 controller의 리턴값, 콜백함수
				$(".row").find(".col").eq(0).html(result.employeeId); //row를 찾고 그중 col을 찾고 그중 첫번째
				$(".row").find(".col").eq(1).html(result.firstName);
				$(".row").find(".col").eq(2).html(result.email);
				$(".row").find(".col").eq(3).html(result.hireDate);
			},
			//아작스에서만 할수있는 옵션
			error: function() {
				alert("error");
			},
			//동기여부(비동기: false)
			async: false,
			//캐쉬여부: 실행된 페이지 저장해두는거(서버 안갔다옴), 할거면:true
			cache: false */
		}).done(function(result) { //정상실행
			$(".row").find(".col").eq(0).html(result.employeeId); 
			$(".row").find(".col").eq(1).html(result.firstName);
			$(".row").find(".col").eq(2).html(result.email);
			$(".row").find(".col").eq(3).html(result.hireDate);
			console.log(result);
		})
		  .fail(function(){})	  //에러 났을떄
		  .always(function(){});  //항상 
	});
});
</script>

</head>
<body>
<h3>사원목록</h3>
<div id="getEmpDiv">
	<div class="row">
		<div class="col">${emp.employeeId}</div>
		<div class="col">${emp.firstName}</div>
		<div class="col">${emp.email}</div>
		<div class="col">${emp.hireDate}</div>
	</div>
</div> <!-- 이 자리에 결과값이 들어온다 -->

<c:forEach items="${empList}" var="emp">
	<img src="download?name=${emp.profile}" style="width:80px" onerror="this.src='resources/common.jpg'"><br/>
	${emp.firstName} 
	${emp.lastName} 
	<a href="#" class="empId">${emp.employeeId}</a> <br/>
	<a href="report.do" target="_blank">PDF</a> <br/><br/> <!-- target="_blank": 새탭, 같이 보게는? -->
</c:forEach>

</body>
</html>