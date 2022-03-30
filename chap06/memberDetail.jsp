<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원 상세정보</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">

	function deleteit(num){
		
		if(!confirm('정말 삭제 하시겠습니까?')){
			return ;
		}
		var obj = {};
		obj.cmd = 'delete';
		obj.empno = num; //el을 주어도 된다.
		$.ajax({
			url : '/demo/mem',
			method : 'post',
			cache : false,
			data : obj,
			dataType : 'json',
			success : function(res){
				alert(res.deleted ? '삭제성공' : '삭제실패');
				location.href = "/demo/mem?cmd=list";
			},
			error : function(xhr, status, err){
				alert('에러 : ' + err);
			}
		});
	}
</script>
</head>
<body> <!-- <main></main> : <div></div> 태그와 같다. -->
<c:if test = "${detail.deptno==0 }">
	<c:set var = "deptno" value =""/>
</c:if>
<c:if test = "${detail.deptno!=0 }">
	<c:set var = "deptno" value ="${detail.deptno }"/>
</c:if>
	<main> <!-- Semantic tag : header, nav, footer, article, section, aside 기능은 div태그와 동일하며 이름만 다르다. -->
		<h3>사원 상세정보</h3>
		<div><label>번호</label>${detail.empno}</div>		
		<div><label>이름</label>${detail.ename}</div>		
		<div><label>부서</label>${detail.deptno}</div>		
		<div><label>입사</label>${detail.hiredate}</div>		
		<div><label>급여</label>${detail.sal}</div>		
	</main>
	<button><a href = "mem?cmd=edit&num=${detail.empno}">수정</a></button>
	<button type = "button" onclick = "deleteit(${detail.empno});">삭제</button>
	<a href = "javascript:deleteit(${detail.empno});">삭제</a> <!--링크태그 자바스크립 메소드실행법.  -->  
</body>
</html>