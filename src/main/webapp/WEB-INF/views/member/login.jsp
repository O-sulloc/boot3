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
	
    <div class="container mt-5 text-center">
			<main class="form-signin">
			  <form action="./login" method="post">
			    <h1 class="h3 mb-3 fw-normal">Please sign in</h1>
			
			    <div class="row justify-content-center">
					<div class="col-3">
					    <div class="form-floating mt-2">
					      <input type="text" name="id" class="form-control" id="floatingInput" placeholder="ID">
					      <label for="floatingInput">ID</label>
					    </div>
					    <div class="form-floating">
					      <input type="password" name="pw" class="form-control" id="floatingPassword" placeholder="Password">
					      <label for="floatingPassword">Password</label>
					    </div>
					
					    <div class="checkbox mb-3 mt-2">
					      <label>
					        <input type="checkbox" value="remember-me"> Remember me
					      </label>
					    </div>
					    <button class="w-100 btn btn-lg btn-success mb-2" type="submit">Sign In</button>
					    <button class="w-100 btn btn-lg btn btn-outline-danger" onclick="location.href='/member/join'" type="submit">Join Now!</button>
				    </div>
				</div>
				
			  </form>
			</main>
		</div>
	

	

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>