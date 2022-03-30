<%@page import="com.tjoeun.vo.BoardVO"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시글 수정하기</title>
<style>
	label { font-size: large; font-weight: bold;}
	#title, #contents { width:300px; margin-bottom:10px; }
	h3, form { width:fit-content; margin:10px auto; }
	form { border:1px solid black; padding:20px; }
	div:last-child { text-align:center; }
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script>
// $(function(){
// 	alert('jQuery Ready!');
// });
	function update(){
		var ok = window.confirm('정말로 이 글을 수정하시겠어요?');
		if(!ok){
			alert('정상적으로 취소했습니다');
			return false;
		}
		
			var serData = $('#updateForm').serialize(); /*cmd num title contents를 문자열로 묶어서 직렬화*/
			$.ajax({
				url:'/demo/bbs',
				method:'post',
				cache:false,
				data:serData, /* 요기까지가 데이터를 서버에 보내는 것. 요청!*/
				dataType:'json', /*서버로부터 응답받을 타입.*/
				success:function(res){ /*응답이 오면 success 이벤트가 발생한다 그리고 파라미터에 오브젝트가 들어온다.*/
					alert(res.updated ? '수정 성공' : '수정 실패');
				},
				error:function(xhr,status,err){ /* 엑스엠엘 비동기객체 상태번호코드 에러메세지 */
					alert('에러:'+err);
				}
			});

		return false;
	}
	function list(){
		var url = "/demo/bbs?cmd=list";
		location.href = url;
	}
</script>
</head>
<body>
<c:set var = "board" value="${requestScope.board}"/>
<h3>제목과 내용을 변경해주세요</h3>
<form id = "updateForm" method = "post" name = "/demo/bbs" onsubmit="return update();">
	<input type = "hidden" name = "cmd" value="update">
	<input type="hidden" name="num" value="${board.num}">
	<div><label for="title">제목</label></div>
	<div>
		<input type="text" id="title" name="title" value="${board.title}">
	</div>
	<div><label for="contents">내용</label></div>
	<div>
		<textarea id="contents" name="contents" rows="10" >${board.contents}</textarea>
	</div>
	<div>
		<button type="submit">저장</button>
		<button type="reset">취소</button>
		<button type= "button" onclick = "list();">목록보기</button> 
	</div>
</form>
</body>
</html>