<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>

<h2>내가 쓴 게시글</h2>
<c:if test="${ses.id eq writer }">
<table class="table">
		<thead>
			<tr>
				<th scope="col">#</th>
				<th scope="col">title</th>
				<th scope="col">writer</th>
				<th scope="col">read Count</th>
				<th scope="col">comment_qty</th>
				<th scope="col">file_qty</th>
				<th scope="col">Mod At</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="bvo">
				<tr>
					<th scope="row">${bvo.bno}</th>
					<td><a href="/board/boardDetail?bno=${bvo.bno}">${bvo.title}</a></td>
					<td>${bvo.writer}</td>
					<td>${bvo.readCount}</td>
					<td>${bvo.cmtQty}</td>
					<td>${bvo.hasFile}</td>
					<td>${bvo.modAt}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:if>

<jsp:include page="../layout/footer.jsp"></jsp:include>