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

<style type="text/css">
	.detail{
		cursor: pointer;
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<c:import url="../temp/header.jsp"></c:import>
	
<div class="container mt-4">

	<div class="row mt-4">
		<c:forEach items="${list}" var="vo">
			<div class="card my-3 me-2 col-4 detail" data-num=${vo.productNum} style="width: 18rem;" >
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
	<!-- jquery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript">
		$(".detail").click(function(){
			let num = $(this).attr("data-num");
			location.href="./detail?productNum="+num
		})
	</script>
	<!-- bootstrap -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>