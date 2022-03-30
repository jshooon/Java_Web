<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원정보 추가</title>
<style>
	
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script>
	function add(){
		var empno = $('#empno').val();
		var ename = $('#ename').val();
		if(empno == '' || ename == ''){
			alert('사원번호와 , 이름은 필수 입력 사항입니다.');
			return false;
		}
		if(!confirm('입력된 정보로 추가 하시겠습니까?')){
			return false;
		}
		var serData = $('#addForm').serialize();
		
		$.ajax({
			url : '/demo/mem',
			method : 'post',
			cache : false,
			data : serData,
			dataType : 'json',
			success : function(res){
				var msg = '';
				if(res.add){
					msg = '추가 성공!';
				}else{
					msg = '추가 실패!';
					if(res.cause != ''){
						msg += ',  원인 : ' + res.cause;
					}
				}
				alert(msg);
				location.href = "/demo/mem?cmd=list";
			},
			error : function(xhr, status, err){
				alert('에러 : ' + err);
				location.href = "/demo/mem?cmd=list";
			}
		});
		return false;
	}
</script>
</head>
<body>
	<h3>사원정보 추가</h3>
<form id = "addForm" method = "post">
	<input type = "hidden" name ="cmd" value = "add">
<div><label for ="empno">번호</label><input type = "text" id = "empno" name = "empno"></div>
<div><label for = "ename">이름</label><input type = "text" id = "ename" name = "ename"></div>
<div><label for = "deptno">부서</label><input type = "text" id = "deptno" name = "deptno"></div>
<div><label for = "hiredate">입사</label><input type = "text" id = "hiredate" name = "hiredate"></div>
<div><label for = "sal">급여</label><input type = "text" id = "sal" name = "sal"></div>
<button type = "button" onclick = "add();">저장</button>
<button type = "reset">취소</button>
</form>
</body>
</html>