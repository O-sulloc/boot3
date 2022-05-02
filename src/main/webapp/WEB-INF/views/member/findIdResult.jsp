<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
	      <h2>Find Id Result</h2>
	    </div>
	
		<div class="row justify-content-center">
	      <div class="col-md-7 col-lg-8">

	            <div class="col-6">
	              <c:choose>
	              	<c:when test="${not empty idResult}">
	              		<spring:message code="member.info.findId" arguments="${idResult.id}"></spring:message>
	              	</c:when>
	              	<c:otherwise>
	              		<spring:message code="member.info.noId"></spring:message>
	              	</c:otherwise>
	              </c:choose>
	            </div>

	          <button class="w-100 btn btn-success btn-lg mb-5" type="button" onclick="#">Login</button>
	        </div>
	      </div>
  		</main>
    </div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>