<%@page import="com.tjoeun.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판</title>
<style>									
	table { border : 1px solid black; border-spacing : 0; margin : 0 auto;} /* border-spacing 테두리 공백 사라짐 */
 	table>caption {font-size : x-large; margin-bottom: 10px;} /*table>caption 테이블의 자식인 캡션은 이라는 뜻  */
 	th {border : 1px solid balck; border-bottom: 3px double black; background-color : navy;} /*background-coldr배경색 */
	td { border-bottom:1px dashed black; border-right:1px dashed black;}
	th,td { padding : 5px 10px;} /* 상하 5px 좌우 10px ,는 나열 */ 
	td:nth-child(2) { width : 200px;} /* td인데 두번째 td라는 뜻 동격이라는 뜻; */
	td:last-child { border-right: none;} /* 테두리가 중첩되기때문에 삭제 */
	tr:last-child >td{ border-bottom: none;} /* 마지막 tr 의 td 바닥을 없애라 */
	#label_list {display:inline-block; margin : 0 auto; margin-top: 0.5em;}
</style>
</head>
<body> <!-- c:set이라는 것은 page영역에 저장한다는 뜻이다. 그래서 다른영역에서 찾지않고 바로 사용되기때문에 작성한다. 그리고 이름을 줄일 수 있기 때문에. -->
<c:set var = "list" value = "${requestScope.list}"/> <!-- scope = "page"로 디폴트로 지정 되기때문에 따로 안써도된다. -->
<table class = "list">
<caption>게시판 리스트</caption>
<tr><th>번호</th><th>제목</th><th>작성자</th><th>날짜</th></tr>
<c:forEach var = "list" items = "${list}"> <!-- items에 requestScope.list를 해준다면 위에 c:set을 안해줘도 된다. -->
	<tr>
		<td>${list.num}</td>
		<td><a href = "/demo/bbs?cmd=detail&num=${list.num}">${list.title}</a></td>
		<td>${list.author}</td>
		<td>${list.sqldate}</td>
	</tr>
</c:forEach>
</table>
<div><label id = "label_list">[<a href = "/demo/bbs?cmd=inputForm">게시글 추가하기</a>]</label></div>
</body>
</html>