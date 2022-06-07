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
	      <h2>MyPage</h2>
	    </div>
	
	<div class="row justify-content-center">
      <div class="col-md-7 col-lg-8">
        <div class="alert alert-success d-flex align-items-center" role="alert">
		  <div>
		    You can check or update your info here.
		  </div>
		</div>
		
          <div class="row g-3">
          <div>
            <div class="col-sm-6">
              <label for="name" class="form-label">Name</label>
              <input type="text" class="form-control" value="${vo.name}" id="name" readonly="readonly" name="name" placeholder="name" required>
              <div class="invalid-feedback">
                Name is required.
              </div>
            </div>
           </div>
			
			<div>
			<div class="col-sm-6">
              <label for="id" class="form-label">ID</label>
              <input type="text" class="form-control" value="${member.id}" readonly="readonly" id="id" placeholder="ID" name="id" required>
              <div class="invalid-feedback">
                ID is required.
              </div>
            </div>
            </div>
            
		<div>
            <div class="col-6">
              <label for="email" class="form-label">Email <span class="text-muted"></label>
              <input type="email" class="form-control" value="${vo.email}" readonly="readonly" name="email" id="email" placeholder="you@example.com">
              <div class="invalid-feedback">
                Please enter a valid email address for shipping updates.
              </div>
            </div>
		</div>
		
            <div class="col-6">
              <label for="phone" class="form-label">Phone<span class="text-muted"></label>
              <input type="text" class="form-control" value="${vo.phone}" readonly="readonly" name="phone" id="phone" placeholder="+82) 000-0000-0000">
              <div class="invalid-feedback">
                Please enter a valid phone number for shipping updates.
              </div>
            </div>
			
			<div>
				<div class="col-6">
					<label for="files" class="form-label">Photo<span class="text-muted"></label>
					<a href="./fileDown?fileNum=${vo.memberFilesVO.fileNum}">${vo.memberFilesVO.oriName}</a>
				</div>
			</div>
			
		<hr class="my-4">
		<div class="container my-2">
			<div class="col-2 d-flex">
				<a href="update" role="button" class="btn btn-outline-success mx-1">update</a>
				<a href="delete" role="button" class="btn btn-outline-danger mx-1">delete</a>
			</div>
        </div>
        
        <div class="container my-2">
			<div class="col-2 d-flex">
				<button id="btn22">장바구니 삭제</button>
			</div>
        </div>
      </div>
  	</main>
    </div>

	<!-- jquery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript">
		
		$("#btn22").click(function(){
			$.ajax({
				type:"delete",
				url:"../cart/1",
				success:function(data){
					console.log(data.trim());
				}
			});
		});
	
		function getList(){
			$.ajax({
				type:"get",
				url:"../cart/2",
				success:function(data){
					console.log(data);
					let r = "<table>";
					//console.log(data[0].cartNum);
				}
			});
		} //getlist end
		
	</script>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>