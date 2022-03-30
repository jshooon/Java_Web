<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>회원가입 폼</title>
<style>
	h3 {border : 1px solid black; width : fit-content; margin : 0 auto; margin-bottom: 0.5em;}
	form > div {margin-bottom : 0.5em;} /* > : form의 자식 태그들을 표시한다. */
	form > div > label {font-weight: bolder; border : 1px solid black; 
						display : inline-block; width : 100px;}
	select {display : inline-block; border : 1px solid black;  width : 100px;}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script>
      $(function () {
         $('#range_out').val(5);
      });
      function showRangeVal(num) {
         $('#range_out').val(num);
      }
</script>
</head>
<body>

<h3>회원 가입</h3>
<form action="/demo/join" method="get">

<div><label>아이디</label></div>
<div>
<input type="text" name="uid">
<div><button>중복확인</button></div>
</div>

<div><label>암호</label></div>
<div>
<input type="password" name="pass" required="required"> <!-- required : 값이 비어있다면, 서버로 폼이 전달 되지 않는다 -->
</div>

<div><label>암호 확인</label></div>
<div>
<input type="password" name="pass">
</div>

<div><label>취미</label></div>
<div>
   <input type="checkbox" name="hobby" value="game" checked> 게임
   <input type="checkbox" name="hobby" value="fishing"> 낚시
   <input type="checkbox" name="hobby" value="movie"> 영화
   <input type="checkbox" name="hobby" value="travel"> 여행
</div>

<div><label>성별</label></div>
<div>
   <input type="radio" name="gender" value="m" checked>남  
   <input type="radio" name="gender" value="f" >여
</div>

<div><label>과목 선택</label></div>
<div>
	<select name = "subject">
		<option selected>Java</option>
		<option>HTML</option>
		<option>CSS</option>
		<option>JavaScript</option>
		<option>MySQL</option>
		<option disabled>Python</option>
	</select>
</div>

<div><label>색상 선택</label></div>
<div>
   <input type="color" name="mycolor">
</div>

<div><label>생년월일</label></div>
<div>
   <input type="date" name="birth">
</div>

<div><label>Email</label></div>
<div>
   <input type="email" name="email">
</div>

<div><label>이력서</label></div>
<div>
   <input type="file" name="resume">
</div>

<div><label>Image</label></div>
<div>
   <input type="image" name="image" width = "100" height="100" src="../images/image.png">
</div>

<div><label>지원횟수</label></div>
<div>
   <input type="number" name="num" min = "0" max = "10">
</div>

<div><label>게임레벨</label></div>
<div>
   <input type = "range" name = "level" min="0" max = "10" 
   oninput="showRangeVal(this.value);"> <!-- console.log(this.value) 자바의 시스템 아웃과 같다. -->
   <output id="range_out"></output>
</div>

<div>
   <input type="submit" value="저장">
   <input type="reset" value="취소">
</div>

</form>

</body>
</html>