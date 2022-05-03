<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
	<c:import url="../temp/header.jsp"></c:import>
	
	<div class="container">
		<div class="row">
			<c:import url="../common/productList.jsp"></c:import>
			<form action="./manage" method="get" id="frm">
				<input type="hidden" name="pn" id="pn" value="${pager.pn}">
			</form>
		</div>
		
		<div class="col-1">
			<a type="button" class="btn btn-outline-success" href="./add">글쓰기</a>
		</div>
			
	</div>
	
	
	<!-- jquery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript">
	
		$(".detail").click(function(){
			let num = $(this).attr("data-num");
			location.href="./manageDetail?productNum="+num;
		})
	
		$(".pager").click(function(){
		//pager라는 class명 클릭하면, 익명함수 시행
			let pn=$(this).attr("pn");
		$("#pn").val(pn);
		$("#frm").submit();
		//frm 버튼 누르면 강제로 submit되게
		});
	</script>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>