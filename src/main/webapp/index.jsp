<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring MVC</title>
</head>
<body>
	<c:set var="root" value="${pageContext.request.contextPath}" />

	<h3>Spring MVC</h3>
	<a href="${root}/test/test.do">테스트</a>

	<h3>방명록</h3>
	<a href="${root}/guest/write.do">방명록 작성</a>

	<h3>게시판</h3>
	<a href="${root}/board/list.do">게시판 목록</a>
	<a href="${root}/board/write.do">게시판 쓰기</a>


	<h3>회원관리</h3>
	<a href="${root}/member/register.do">회원가입</a>&nbsp;&nbsp;
	<a href="${root}/member/login.do">로그인</a>
</body>
</html>