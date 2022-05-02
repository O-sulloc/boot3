<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    
<title>Insert title here</title>
</head>
<body>
	<!-- header -->
	<c:import url="./temp/header.jsp"></c:import>
	
	<!-- carousel -->
	<div class="container mt-5">
		<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
		  <div class="carousel-indicators">
		    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
		    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
		    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
		  </div>
		  <div class="carousel-inner">
		    <div class="carousel-item active">
		      <img src="https://fncent.com/files/2022/02/23/95c2c0687206848f8f867241a1115b1a180649.jpg" class="d-block w-100" alt="...">
		    </div>
		    <div class="carousel-item">
		      <img src="https://fncent.com/files/2022/02/23/875ea9925cc4988746519d241fd2b78a180649.jpg" class="d-block w-100" alt="...">
		    </div>
		    <div class="carousel-item">
		      <img src="https://fncent.com/files/2022/02/23/d24f5e0235f6cd574b1ffdb0d5689aed180649.jpg" class="d-block w-100" alt="...">
		    </div>
		  </div>
		  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
		    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    <span class="visually-hidden">Previous</span>
		  </button>
		  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
		    <span class="carousel-control-next-icon" aria-hidden="true"></span>
		    <span class="visually-hidden">Next</span>
		  </button>
		</div>
	</div>
	
	<div class="container">
		<c:if test="${not empty member}">
			<c:forEach items="${member.roleVOs }" var="vo">
				<h4>${vo.roleName}</h4>
			</c:forEach>
		</c:if>
		
	</div>
	
	<div class="container">
		<input type="text" id="v1">
		<input type="checkbox" class="num" name="num" value="a">
		<input type="checkbox" class="num" name="num" value="b">
		<input type="checkbox" class="num" name="num" value="c">
		<input type="checkbox" class="num" name="num" value="d">
		<button id="btn1">GET</button>
		<button id="btn2">POST</button>
		<button id="btn3">ajax</button>
	</div>
	
	<div class="container">
		<h1>springmessage</h1>
		<h1><spring:message code="hello" var="m"></spring:message></h1>
		<h1><spring:message code="test" text="기본메세지"></spring:message></h1>
		<!-- test라는건 등록을 안 해놓은 키인데? test 코드 오면 '기본메세지'를 출려갷줘 -->
		
		<h1>${m}</h1>
		<h1>${m}</h1>
		<h1><spring:message code="board.title"></spring:message></h1>
	</div>
	
	<div class="container">
		<c:if test="${not empty member }">
			<h1><spring:message code="member.info" arguments="${member.id}"></spring:message></h1>
		</c:if>
	</div>
	
	<!-- jquery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript">
		$("#btn3").click(function(){
			let ar = [1,2,3];
			
			let nums = [];
			$(".num").each(function(idx, item){
				//index번호로 반복문 꺼내오고 item에 담아
				if($(item).prop("checked")){
					//item에 checked라는 속성이 트루르라면
					console.log($(item).val());
					nums.push($(item).val());
				}
			})

			
			let v =$('#v1').val();
			$.ajax({
				type: "POST",
				url: "./arrayTest",
				traditional:true,
				data:{
					msg:v,
					numbers: ar
				},
				success: function(d){
					console.log(d.trim());
				},
				error:function(){
					alert('error');
				}
			});
		});
		
		$("#btn2").click(function(){
			let v =$('#v1').val();
			$.post("./postTest", {msg:v}, function(data){
				console.log(data.trim());
			});
		});
	
		$('#btn1').on("click", function(){
			//버튼을 클릭하면 
			let v =$('#v1').val();
			console.log(v);
			//콘솔에 v를 출력해줘
			//v에는 v1에 입력한 값이 들어가 있음 
			$.get("./getTest?msg="+v, function(data){
				//btn1을 누르면 ./getTest url로 비동기 get방식을 실행시켜달라고 요청함.
				//homecontroller에 getTest가 발동됨
				//이제 파라미터로 v의 값을 넘겨주고 싶음. url뒤에 물음표로 처리하면된다.
				//"./getTest?이름1="+값1+"&이름2="+값2 이렇게
				

				//.gettest?msg로 요청을 보내면, 익명 함수인 function()이 응답을 받음
				//근데 그 응답으로 받는 데이터(값, text,여기서는 v)는 콜백함수인 function()의 매개변수로 받을 수 있다.
				//즉, function()에 data라는 매개변수를 선언하고 그걸로 v1의 값 v를 담을 수 있는거?
				//그걸 다시 홈컨트롤러의 common/getresult가 응답으로 받아줌
				//getResult의 모든 html이 data에 담기는거 ㅇㅇ
				console.log(data.trim());
				//출력해보면 알 수 있음
				//공백없애려고 trim썼다.
				
			});
		});
	</script>
	
	<!-- bootstrap -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>	
</body>
</html>