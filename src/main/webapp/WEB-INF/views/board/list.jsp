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
					<div class="col-4 me-1">
						<select name="kind" class="form-select"
							aria-label="Default select example">
							<option selected value="title">title</option>
							<option value="contents">contents</option>
							<option value="writer">writer</option>
						</select>
					</div>
					
					<div class="col-6 me-1">
						<input class="form-control col-2" value="${pager.search}"
							type="search" placeholder="Search" name="search"
							aria-label="Search">
					</div>
					<button type="submit" class="btn btn-outline-success">Search</button>

				</form>
			</div>

			<div class="col-1">
				<a type="button" class="btn btn-outline-success" href="./add">?????????</a>
			</div>
		</div>


		<!-- pagination -->
		<div class="row mt-4">
			<nav aria-label="...">
				<ul class="pagination justify-content-md-center">
					<li class="page-item"><a class="page-link"
						href="./list?pn=${pager.pre?pager.startNum-1:1}&kind=${pager.kind}&search=${pager.search}">Previous</a>
						<!-- ?????? ????????? ?????????
		      (??????????true?????? ????????? ??????1: false?????? ????????? ??????2)
		      pager.pre??? true??????(previous??? ????????? ???????????? ?????????) startNum-1??????
		      pager.pre??? false??????(?????? ???????????? 1???????????? ??? ?????? ????????? ??? ??? ?????????) ??? ???????????? ????????? ??? ?????????
		      --></li>

					<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
						<!-- ?????????????????????. pager?????? startNum??? 1??? ?????????. lastnum??? ????????????-->
						<li class="page-item"><a class="page-link"
							href="./list?pn=${i}&kind=${pager.kind}&search=${pager.search}">${i}</a></li>
					</c:forEach>

					<li class="page-item"><a class="page-link"
						href="./list?pn=${pager.next?pager.lastNum+1:pager.lastNum}&kind=${pager.kind}&search=${pager.search}">Next</a>
						<!-- true???, next????????? +1??????
		      		flase???, next????????? ????????? ??????????????? ??? ??? ????????? ?????? --></li>
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