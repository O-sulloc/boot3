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
	
	<div class="container mt-3">
	  <main>
	    <div class="py-5 text-center">
	      <h2>Welcome to join us!</h2>
	    </div>
	
	<div class="row justify-content-center">
      <div class="col-md-7 col-lg-8">
        <div class="alert alert-success d-flex align-items-center" role="alert">
		  <div>
		    Enter your information
		  </div>
		</div>
		
        <form action="./add" method="post" class="needs-validation" novalidate>
          <div class="row g-3">
          <div>
            <div class="col-sm-6">
              <label for="name" class="form-label">Name</label>
              <input type="text" class="form-control" id="name" name="name" placeholder="name" required>
              <div class="invalid-feedback">
                Name is required.
              </div>
            </div>
           </div>
			
			<div>
			<div class="col-sm-6">
              <label for="id" class="form-label">ID</label>
              <input type="text" class="form-control" id="id" placeholder="ID" name="id" required>
              <div class="invalid-feedback">
                ID is required.
              </div>
            </div>
            </div>
			
			<div class="col-sm-6">
              <label for="pw" class="form-label">Password</label>
              <input type="password" class="form-control" id="pw" placeholder="password" name="pw" required>
              <div class="invalid-feedback">
                Password is required.
              </div>
            </div>
            
		<div>
            <div class="col-6">
              <label for="email" class="form-label">Email <span class="text-muted"></label>
              <input type="email" class="form-control" name="email" id="email" placeholder="you@example.com">
              <div class="invalid-feedback">
                Please enter a valid email address for shipping updates.
              </div>
            </div>
		</div>
		
            <div class="col-6">
              <label for="phone" class="form-label">Phone<span class="text-muted"></label>
              <input type="text" class="form-control" name="phone" id="phone" placeholder="+82) 000-0000-0000">
              <div class="invalid-feedback">
                Please enter a valid phone number for shipping updates.
              </div>
            </div>

          <hr class="my-4">

          <button class="w-100 btn btn-success btn-lg mb-5" type="submit">Join!</button>
        </div>
        </form>
        </div>
      </div>
  	</main>
    </div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>