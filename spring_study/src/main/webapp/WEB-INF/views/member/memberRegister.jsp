<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>


<div class="container my-3">
	<h4 class="mb-3">회원가입</h4><br>
	<form action="/member/memberRegister" method="post">
		<div class="mb-3">
			<label for="e" class="form-label">email</label> 
			<input type="email" name="email" class="form-control" id="e" placeholder="example@example.com">
		</div>
		<div class="mb-3">
			<label for="p" class="form-label">password</label> 
			<input type="password" name="pwd" class="form-control" id="p" placeholder="password">
		</div>
		<div class="mb-3">
			<label for="n" class="form-label">nick_name</label> 
			<input type="text" name="nickName" class="form-control" id="n" placeholder="nickName">
		</div>
		<button type="submit" class="btn btn-primary">가입하기</button>
	</form>
</div>


<jsp:include page="../layout/footer.jsp"></jsp:include>