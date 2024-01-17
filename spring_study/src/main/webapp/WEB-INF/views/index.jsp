<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="layout/header.jsp"></jsp:include>
<jsp:include page="layout/nav.jsp"></jsp:include>


<div class="container my-3">

	<h1>spring board!</h1>
	
	<P>  The time on the server is ${serverTime}. </P>

</div>


<jsp:include page="layout/footer.jsp"></jsp:include>