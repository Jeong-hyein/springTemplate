<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<h1>사원등록</h1>
	<img src="./images/11.jpg">
	<form action="insertEmp">
	<!-- vo의 필드명이랑 같아야한다. -->
	<input name="firstName">
	<input name="lastName">
	<button>등록</button>
	</form>

<!-- tiles-layout의, content에 들어갈 내용만 적으면 된다. -->