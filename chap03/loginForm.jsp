<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String target = (String)session.getAttribute("target");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>회원 로그인</title>
<style>
	label { display:inline-block; width:60px; }
	span { display:inline-block; width: 100px; }
	input[type=text] { width:80px; }
	button { margin-top:0.5em; width:72px; }
	#loginForm { border:1px solid black; width:fit-content; 
		padding:0.5em; margin:0 auto;
		/*background-color: #baebfe;*/
		background-color: rgb(186, 235, 254);
	}
	h3 { width:fit-content; margin:0 auto;
		margin-bottom: 0.5em;
	}
	/*div { width:fit-content; margin:0 auto; }*/
	.div-btn { width:fit-content; margin:0 auto; }
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	$(function(){
		//alert('jQuery Ready!');
	});
	
	function login()
	{
		var uid_val = $('#uid').val();
		var pwd_val = $('#pwd').val();
		
		if(uid_val=='' || pwd_val=='') {
			alert('아이디, 암호는 필수입력 항목입니다');
			return false;
		}
		
/* 		if(uid_val=='') {
			alert('아이디는 필수 입력항목입니다');
			return false;
		}
		if(pwd_val=='') {
			alert('암호는 필수 입력항목입니다');
			return false;
		} */
		
		var serData = $('#loginForm').serialize();

		$.ajax({
			url:'/demo/user',
			method:'post',
			cache:false,
			data:serData,
			dataType:'json',
			success:function(res){
				if(res.ok){
					alert('로그인 성공');
					var target = '<%=target%>';
					if (target != '') {
						location.href = target;
					}
				}else{
					alert('로그인 실패');
				}
			},
			error:function(xhr,status,err){
				alert('에러:'+err);
			}
		});
		return false;
	}
</script>
</head>
<body>
<h3> 로그인 폼</h3>
<form id="loginForm" action="/demo/user" method="post"  onsubmit="return login();">
	<input type="hidden" name="cmd" value="login" >
	<div><label for="uid">아이디 </label>
		<input type="text" id="uid" name="uid">
	</div>
	<div><label for="pwd">암 호 </label>
		<input type="text" id="pwd" name="pwd">
	</div>
	<div class="div-btn">
		<button type="submit">로그인</button>
		<button type="reset">취 소</button>
	</div>
</form>
</body>
</html>