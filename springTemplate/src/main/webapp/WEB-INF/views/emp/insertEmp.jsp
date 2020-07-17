<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<h1>사원등록</h1>
		<!-- type="file"이 들어가면 post로 해줘야함 -->
		<form action="insertEmp" method="post" enctype="multipart/form-data"> 
			<!-- vo의 필드명이랑 같아야한다. -->
			firstName <input name="firstName"> <br>
			lastName <input name="lastName"> <br>
			email <input name="email"> <br>
			jobid <input name="jobId"> <br>
			hireDate <input name="hireDate"> <br>
			이미지 <input type="file" name="uploadFile"/><br>
			<button>등록</button>
		</form>
	<img src="./images/1500957391032.jpg">

<!-- tiles-layout의, content에 들어갈 내용만 적으면 된다. -->