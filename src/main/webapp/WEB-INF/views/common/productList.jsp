<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th scope="col">Num</th>
			<th scope="col">Name</th>
			<th scope="col">Price</th>
			<th scope="col">Count</th>
			<th scope="col">Sale</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="vo">
			<tr>
				<td>${vo.productNum }</td>
				<td>${vo.productName}</td>
				<td>${vo.productPrice}</td>
				<td>${vo.productCount}</td>
				<td>${vo.sale}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<!-- pagination -->
		<div class="row mt-4">
			<nav aria-label="...">
				<ul class="pagination justify-content-md-center">
					<li class="page-item"><a data-pn="${pager.pre? pager.startNum-1: 0}" class="page-link pager" href="#">Previous</a>

					<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
						<li class="page-item"><a data-pn="${i}" class="page-link pager" href="#">${i}</a></li>
					</c:forEach>

					<li class="page-item"><a data-pn="${pager.next? pager.lastNum+1: 0}" class="page-link pager" id="next" href="#">Next</a>
					<!-- 만약 다음 페이지가 있따면? +1 없으면 0넣어줘 -->
				</ul>
			</nav>
		</div>
<!-- pagination end -->