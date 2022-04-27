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
		<main>
		    <div class="py-3 text-center">
		      <h2>Update your info</h2>
		    </div>
	
		<div class="row justify-content-center">
	      <div class="col-md-7 col-lg-8">
	        <div class="alert alert-success d-flex align-items-center" role="alert">
			  <div>
			    Enter your information
			  </div>
			</div>
			
			<form action="./update" method="post" class="needs-validation" enctype="multipart/form-data">
	          <div class="row g-3">
	          <div>
	            <div class="col-sm-6">
	              <label for="name" class="form-label">Name</label>
	              <input type="text" class="form-control" value="${vo.name}" id="name" name="name" required>
	            </div>
	           </div>
	            
			<div>
	            <div class="col-6">
	              <label for="email" class="form-label">Email <span class="text-muted"></label>
	              <input type="email" class="form-control" value="${vo.email}" name="email" id="email">
	            </div>
			</div>
			
	            <div class="col-6">
	              <label for="phone" class="form-label">Phone<span class="text-muted"></label>
	              <input type="text" class="form-control" value="${vo.phone}" name="phone" id="phone" >
	            </div>
				
				<div>
					<div class="col-6">
						<label for="files" class="form-label">Photo<span class="text-muted"></label>
						<input type="file" name="file" id="file" class="form-control">
					</div>
				</div>
				
				<div class="container my-4">
				<div class="col-2 d-flex">
				<button class="w-100 btn btn-success btn-lg mb-5" type="submit">Complete</button>
				</div>
				</div>
	        </div>
	        </form>
	        
			</div>
		</div>
	</div>
		
		</main>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>