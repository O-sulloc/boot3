<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header class="container-fluid ">
	<nav class="navbar navbar-expand-lg navbar-dark bg-success">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="/">Icon</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="/">Home</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link active" href="/board/list">Board</a>
	        </li>
	        <li class="nav-item dropdown">
	          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	            Dropdown
	          </a>
	          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
	            <li><a class="dropdown-item" href="#">Action</a></li>
	            <li><a class="dropdown-item" href="#">Another action</a></li>
	            <li><hr class="dropdown-divider"></li>
	            <li><a class="dropdown-item" href="#">Something else here</a></li>
	          </ul>
	        </li>
	      </ul>
		
	      
		<div class="col-4 me-2">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<c:if test="${empty member}">
					<li class="nav-item">
				      <a class="nav-link active" aria-current="page" href="/member/add">Join</a>
				    </li>
				    <li class="nav-item">
				      <a class="nav-link active" aria-current="page" href="/member/login">login</a>
					</li>
				</c:if>
				
				<c:if test="${not empty member}">
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
						  MyPage
						</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						  <li><a class="dropdown-item" href="/member/mypage">mypage update</a></li>
						</ul>
					</li>
				    <li class="nav-item">
				      <a class="nav-link active" aria-current="page" href="/member/logout">logout</a>
					</li>
				</c:if>
			</ul>
		</div>
			
	      <form class="d-flex" action="./list" method="get">
	      	<div class="col-4 me-2">
				<select name="kind" class="form-select" aria-label="Default select example">
				  <option selected value="title">title</option>
				  <option value="contents">contents</option>
				  <option value="writer">writer</option>
				</select>
			</div>
			
	        <input class="form-control me-2" value="${pager.search}" type="search" placeholder="Search" name="search" aria-label="Search">
	        <button class="btn btn-outline-light" type="submit">Search</button>
	      </form>
	      
	    </div>
	  </div>
	</nav>
</header>
