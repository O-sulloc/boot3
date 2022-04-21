<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
	<c:import url="../temp/header.jsp"></c:import>

	<div class="container mt-4">
		<div class="row mt-4">
			<div class="alert alert-success" role="alert">
				<h4 style="text-transform: capitalize;">${board}List</h4>
			</div>
		</div>

		<div class="row mt-2">
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th scope="col">No</th>
						<th scope="col">Title</th>
						<th scope="col">Writer</th>
						<th scope="col">Date</th>
						<th scope="col">Hit</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${list}" var="vo">
						<tr>
							<td>${vo.num }</td>
							<td><a class="text-decoration-none link-success" href="./detail?num=${vo.num}">${vo.title}</td>
							<td>${vo.writer }</td>
							<td>${vo.hit }</td>
							<td>${vo.regDate }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="row justify-content-between">
			<div class="col-4">
				<form class="d-flex" action="./list" method="get">
					<div class="col-4 me-2">
						<select name="kind" class="form-select"
							aria-label="Default select example">
							<option selected value="title">title</option>
							<option value="contents">contents</option>
							<option value="writer">writer</option>
						</select>
					</div>

					<input class="form-control col-2 me-2" value="${pager.search}"
						type="search" placeholder="Search" name="search"
						aria-label="Search">
					<button type="submit" class="btn btn-outline-success">Search</button>

				</form>
			</div>

			<div class="col-1">
				<a type="button" class="btn btn-outline-success" href="./add">글쓰기</a>
			</div>
		</div>


		<!-- pagination -->
		<div class="row mt-4">
			<nav aria-label="...">
				<ul class="pagination justify-content-md-center">
					<li class="page-item"><a class="page-link"
						href="./list?pn=${pager.pre?pager.startNum-1:1}&kind=${pager.kind}&search=${pager.search}">Previous</a>
						<!-- 삼항 연산자 사용법
		      (조건식?true일때 실행될 코드1: false일때 실행될 코드2)
		      pager.pre가 true라면(previous로 이동할 페이지가 있다면) startNum-1하고
		      pager.pre가 false라면(현재 페이지가 1페이지라 더 이상 앞으로 갈 수 없다면) 앞 페이지로 이동할 수 없도록
		      --></li>

					<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
						<!-- 페이지뿌려주기. pager에서 startNum에 1을 넣어둠. lastnum도 계산해둠-->
						<li class="page-item"><a class="page-link"
							href="./list?pn=${i}&kind=${pager.kind}&search=${pager.search}">${i}</a></li>
					</c:forEach>

					<li class="page-item"><a class="page-link"
						href="./list?pn=${pager.next?pager.lastNum+1:pager.lastNum}&kind=${pager.kind}&search=${pager.search}">Next</a>
						<!-- true면, next누를때 +1해줘
		      		flase면, next누를떄 마지막 페이지로만 갈 수 있도록 해줘 --></li>
				</ul>
			</nav>
		</div>
		<!-- pagination end -->
		
	</div>
	<!--  -->

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
</body>
</html>