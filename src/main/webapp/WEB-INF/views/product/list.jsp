<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"	crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
	<c:import url="../temp/header.jsp"></c:import>
	
<div class="container mt-4">

	<div class="row mt-4">
		<c:forEach items="${list}" var="vo">
			<div class="card my-3 me-2 col-4" style="width: 18rem;">
				<img src="../resources/upload/product/${vo.productFilesVOs[0].fileName}" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">${vo.productName}</h5>
			    	<%-- <p class="card-text">${vo.productDetail}</p> --%>
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">price: ${vo.productPrice}</li>
					<li class="list-group-item">qty: ${vo.productCount}</li>
				</ul>
			</div>
		</c:forEach>
	</div>
	
		<div class="row justify-content-between container mt-4">
			<div class="col-4">
				<form class="d-flex" action="./list" method="get">
					<div class="col-4 me-1">
						<select name="kind" class="form-select"
							aria-label="Default select example">
							<option selected value="name">Name</option>
							<option value="contents">Contents</option>
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
	<!-- jquery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<!-- bootstrap -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>