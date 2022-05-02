<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script type="text/javascript">
	alert('${msg}');
	location.href="${path}"
	
	//서버로 요청하는 방식 5가지
	//서버요청필수요소:url주소랑 method 방식
	//방식1.web browser의 주소창에 직접 주소를 입력하는 방식
	//2. html에 a태그를 클릭하면 요청하는 방식
	//3. html에 form태그를 이용
	//4. js에서 location객체의 href 속성 이용하는 방식 (지금쓸거)
	//5. aja로 요청하는 방식
</script>