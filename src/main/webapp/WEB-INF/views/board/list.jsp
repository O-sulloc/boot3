<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	
	<div class="container mt-4">
		<div class="row mt-4">
			<div class="alert alert-success" role="alert">
				<h4 style="text-transform: capitalize;">${board} List</h4>
			</div>
		</div>
		
		<div class="row mt-4">
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
				      <td>${vo.title }</td>
				      <td>${vo.writer }</td>
				      <td>${vo.hit }</td>
				      <td>${vo.regDate }</td>
				    </tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		
		<div class="row justify-content-end">
			<a type="button" class="col-1 btn btn-outline-success" href="./add">글쓰기</a>
		</div>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>