<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

	<!-- jquery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<!-- include summernote css/js -->
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>

<title>Insert title here</title>
</head>
<body>
	<c:import url="../temp/header.jsp"></c:import>
	
	<div class="container mt-4">
		<div class="row mt-4">
			<div class="alert alert-success" role="alert">
			  <h4 style="text-transform: capitalize;">product Add</h4>
			</div>
		</div>
	</div>
	
	<!-- ajax로 상품리스트를 뿌려줘 이름, 가격, 수량-->
	<div class="container mt-2" id="list">

	</div>
	
	<form action="./add" method="post" enctype="multipart/form-data" id="form">
	<div class="container mt-4">
		<div class="mb-3 col-2">
		  <label for="exampleFormControlInput1" class="form-label">Product Name</label>
		  <input type="text" id="productName" name="productName" class="form-control" placeholder="productName">
		</div>
		
		<div class="mb-3 col-6">
		  <label for="exampleFormControlInput1" class="form-label">Product Price</label>
		  <input type="number" id="productPrice" name="productPrice" class="form-control" placeholder="product Price">
		</div>
		
		<div class="mb-3 col-6">
		  <label for="exampleFormControlInput1" class="form-label">Product Quantity</label>
		  <input type="number" id="productCount" name="productCount" class="form-control" placeholder="product Count">
		</div>
		
		<div class="mb-3">
		  <label for="exampleFormControlTextarea1" class="form-label">Product Detail</label>
		  <textarea class="form-control" id="productDetail" name="productDetail"></textarea>
		</div>

		<button type="button" class="btn btn-success mt-4 my-2" id="fileAdd">FileAdd</button>
		<!-- 버튼이 버튼타입이어야 함 -->
		
		<div id="fileResult" class="row mb-3">
			<!-- 제이쿼리로 파일 인풋 태그들 만들거임. -->
		</div>

		<button type="button" class="btn btn-outline-success mt-4" id="btn">등록하기</button>
	</div>
	</form>
		
	<script type="text/javascript">
		let pn = 1;
	
		$("#list").on("click", ".pager", function(){
			let checkPn = $(this).attr("data-pn");
			if(checkPn>0){
				pn=checkPn;
				getList(checkPn);				
			}else{
				//이전 또는 다음 블락이 없는 경우에 알림창
				alert("no page");
			}
		});
	
		getList(1);
		//getlist라는 함수 실행해
	
		function getList(pn){
			$.ajax({
			//페이지 로딩되자마자 ajax 실행해
				type:"GET",
				url:"./ajaxList",
				data:{
					pn:pn,
					perPage:5
					//한페이지에 글 5개씩 보여줘
				},
				success:function(data){
					//성공하면, 테이블에 글 뿌려줘
					$("#list").html(data.trim());
					//append가 아니라 html로 덮어씌우는거. 덮어씌우지 않으면 같은 전체 리스트를 계속 가져오니까 중복됨.
				}
			});
			
		}
	
		$("#btn").click(function(){
			//btn 누르면 다음의 정보를 ajax로 db에 넣어줘
			//let formData = new FormData($('#form')[0]);
			//let productFile = $('#productFile')[0];
			
			let formData = new FormData();
			let productName =$('#productName').val();
			let productPrice =$('#productPrice').val();
			let productCount =$('#productCount').val();
			let productDetail =$('#productDetail').summernote('code');
			//이 섬머노트는 이렇게도 가져올 수 있다.
			
			$(".files").each(function(idx, item){
				if(item.files.length>0){
					console.log(idx); //index 번호
					console.log(item); //<input type="files">
					console.log(item.files); //input태그의 file List
					console.log(item.files[0]); //fiels list 중 첫번째 파일 출력
					console.log("len"+item.files[0].length); //파일의 길이
					console.log(item.files[0].name); //files list 중 첫번째 파일의 이름 출력
					//formData.append("파라미터명", 값);
					formData.append("files", item.files[0]);
					//파일즈라는 파라미터에, item에 들어간 파일을 반복문으로 돌려서 한개씩 넣어.
				}
			}); //each 끝
				
			formData.append("productName", productName);
			formData.append("productPrice", productPrice);
			formData.append("productCount", productCount);
			formData.append("productDetail", productDetail);
			//formdata에 데이터 추가
			
			
			
			 $.ajax({
				type: "POST",
				url: "./add",
				processData: false,
			    contentType: false,
				data: formData, 
					/* {
					productName:productName,
					productPrice:productPrice,
					productCount:productCount,
					productDetail:productDetail
				},   */
				success: function(result){
					if(result ==1){
						//만약 컨트롤러에서 가져온 result가 1이라면 (성공했다면)
						alert("상품 등록 완료");
						//알림창 띄워주고
						getList();
						//그리고 입력한 글들을 상단의 list에 추가하는 getList 함수 호출
						$('#productName').val("");
						$('#productPrice').val("");
						$('#productCount').val("");
						$('#productDetail').summernote('code',"");
						//글을 넘겨주고 나서 인풋창을 공백으로 초기화해줘. 안그럼 그 전에 썼던 글자들 그대로 남아있음
					}else{
						alert("실패");
					}
				},
				error:function(){
					alert("상품 등록이 실패");
				}
			});
			
		});
	
		//summernote
		$('#productDetail').summernote({
			height: 400,
			// 창 expand 되도록 height 지정
		});
		//id가 productDetail인 걸 찾아서 summernote를 적용해줘
	
		let count = 0;
		
		$("#fileAdd").click(function () {
			if(count>4){
				alert("최대 5개만 가능");
				return;
			}
			let result = '<div class="input-group my-1">'
			result = result + '<input name="files" type="file" class="form-control files" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04" aria-label="Upload">'
			result = result + '<button class="btn btn-outline-secondary del" type="button" id="inputGroupFileAddon04">X</button>'
			result = result + '</div>';
			
			$("#fileResult").append(result);
			
			count++;
		});
		
		$("#fileResult").on("click", ".del", function(){
			$(this).parent().remove();
			count--;
			//this? 지금 선택한게 누구야? .del 이게 this인 거지
			//얘의 부모를 선택한 후 지워
			//그리고 count도 -1 해.
		});
	</script>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>