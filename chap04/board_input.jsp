<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판 글 쓰기</title>
<style>
	div { margin:5px; }
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type = "text/javascript">
	function save(){
		
		var serData = $('#inputForm').serialize();
		/* alert(serData); */
		$.ajax({
			url:'/demo/bbs',	/* 요청 서버가 돌아간다*/
			method : 'post', 	/* 전송 에이젝스는 포스트방식만지원.*/
			cache : false,	 	/* 전송 */ /* 브라우저가 기억하고 있는내용 */
			data : serData,  	/* 서버가 돌아가고 데이터를 전송 */
			dataType : 'json', 	/* 오는방식은 json오브젝트로 변경한다. */
			success : function(res){ /* 서버로 부터 응답을 변수로 받는다. */
				if(res.saved){	/*블리언표현방식*/
					alert('게시판 글 저장 성공');
				}else{
					alert('글 저장 실패');
				}
				location.href = "/demo/bbs?cmd=list";
			},
			error : function(xhr,status,err){
				alert('에러:'+err);
			}
		});
		return false;
	}
</script>


</head>
<body>
<h3>게시판 글 쓰기</h3>												<!-- 세이브 값이 트루가나오면 보낸다. 펄스면 보내지 않는다. -->
<form  id = "inputForm" action="/demo/bbs" method="post" onsubmit = "return save();"> <!-- 전송기능 무효화 -->
	<input type="hidden" name="cmd" value="save">
<div><label for="title">글 제목</label>
	<input type="text" id="title" name="title">
</div>
<div>
	<label for="contents" >글 내용</label>
	<textarea id="contents" name="contents" placeholder="글 입력..."
			rows="5" cols="35"></textarea>
</div>
<div>
	<button type="submit">저장</button>
	<button type="reset">취소</button>
</div>
</form>
</body>
</html>