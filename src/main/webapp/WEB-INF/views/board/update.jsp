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
	
	<div class="container mt-4">
		<div class="row mt-4">
			<div class="alert alert-success" role="alert">
			  <h4 style="text-transform: capitalize;">${board} update</h4>
			</div>
		</div>
	</div>
	
	<form action="./update" method="post" enctype="multipart/form-data">
	<div class="container mt-4">
	<input type="hidden" name="num" value="${vo.num }">
		<div class="mb-3 col-2">
		  <label for="exampleFormControlInput1" class="form-label">Writer</label>
		  <input type="text" value="${vo.writer}" name="writer" class="form-control" id="exampleFormControlInput1" placeholder="Writer">
		</div>
		<div class="mb-3 col-6">
		  <label for="exampleFormControlInput1" class="form-label">Title</label>
		  <input type="text" value="${vo.title}" name="title" class="form-control" id="exampleFormControlInput1" placeholder="Write Title In Here">
		</div>
		<div class="mb-3">
		  <label for="exampleFormControlTextarea1" class="form-label">Contents</label>
		  <textarea class="form-control" name="contents" id="exampleFormControlTextarea1" rows="3" placeholder="Write Contents In Here">${vo.contents}</textarea>
		</div>
		
		<div class="row mb-3">
			<label for="files" class="col-sm-2 col-form-label">File</label>
			<div class="col-sm-10">
				<input type="file" name="files" id="files" class="form-control">
			</div>
		</div>
		<div class="row mb-3">
			<label for="files" class="col-sm-2 col-form-label">File</label>
			<div class="col-sm-10">
				<input type="file" name="files" id="files" class="form-control">
			</div>
		</div>

		<button type="submit" class="btn btn-outline-success mt-4">Update</button>
	</div>
	</form>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>