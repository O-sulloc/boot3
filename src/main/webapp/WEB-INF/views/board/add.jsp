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
			  <h4 style="text-transform: capitalize;">${board} write</h4>
			</div>
		</div>
	</div>
	
	<form action="./add" method="post" enctype="multipart/form-data">
	<div class="container mt-4">
		<div class="mb-3 col-2">
		  <label for="exampleFormControlInput1" class="form-label">Writer</label>
		  <input type="text" value="${member.id }" readonly="readonly" name="writer" class="form-control" id="exampleFormControlInput1" placeholder="Writer">
		</div>
		<div class="mb-3 col-6">
		  <label for="exampleFormControlInput1" class="form-label">Title</label>
		  <input type="text" name="title" class="form-control" id="exampleFormControlInput1" placeholder="Write Title In Here">
		</div>
		<div class="mb-3">
		  <label for="exampleFormControlTextarea1" class="form-label">Contents</label>
		  <textarea class="form-control" name="contents" id="contents" rows="3" placeholder="Write Contents In Here"></textarea>
		</div>

		<button type="button" class="btn btn-success mt-4 my-2" id="fileAdd">FileAdd</button>
		<!-- 버튼이 버튼타입이어야 함 -->
		
		<div id="fileResult" class="row mb-3">
			<!-- 제이쿼리로 버튼 태그들 만들거임. -->
		</div>

		<button type="submit" class="btn btn-outline-success mt-4">등록하기</button>
	</div>
	</form>
	
	<script type="text/javascript">
		//summernote
		$('#contents').summernote({
			height: 400,
			placeholder:'write here..',
			callbacks:{
				onImageUpload: function(files){
					//files는 업로드한 이미지 파일들을 담는 객체를 의미한다.
					let formData = new FormData();
					//변수명은 자유
					formData.append("files", files[0]);
					//업로드한 파일 하나하나를 formData에 append하겠다.
					
					$.ajax({
						type:"POST",
						url: "./summerFileUpload",
						processData: false,
						contentType: false,
						data: formData,
						success: function(data){
							$("#contents").summernote('editor.insertImage', data.trim());
						}
					}); //ajax
				}, //onImageUpload
				onMediaDelete: function(files){
					//summernote에서 이미지 delete하면 hdd에서도 삭제되도록
					
					let fileName =$(files[0]).attr("src");
					//그러면 파일이 저장된 경로명을 알아야 되는데, 그건 img 태그의 src에 들어가 있음.
					//그니까 src 속성의 값을 가져와
					console.log(fileName);
					$.ajax({
						type:"GET",
						url:"./summerFileDelete",
						data:{
							fileName:fileName
						},
						success:function(data){
							console.log(data);
						}
					});
				}//onMediaDelete 끝
			}
		});
	
		let count = 0;
		
		$("#fileAdd").click(function () {
			if(count>4){
				alert("최대 5개만 가능");
				return;
			}
			let result = '<div class="input-group my-1">'
			result = result + '<input name="files" type="file" class="form-control" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04" aria-label="Upload">'
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