
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/header.jsp" />

<script type="text/javascript">
$(function() {
	$("#fileUpdate").click(function(){
		$("#filelist").toggleClass("displayNone")
		$("#fileup").toggleClass("displayNone")
	})
})
</script>

<style type="text/css">
textarea {
	resize: none;
	width: 900px;
	height: 600px;
}
td {
 	border: 1px solid #aaa; 
}
th {
	border: 1px solid #bbb;
	background-color: #ddd;
}
table {
	border: 1px solid #ccc;
	border-collapse: collapse;
}
#boardno{
	background-color: #ddd;
	border: none;
	font-weight: bold;
	width: 40px;
	font-size: 16px;
	color: black;
}
.displayNone{
	display: none;
}

</style>
</head>
<body>

<h1>게시글 수정하기</h1>
<hr>

<form action="/board/update" method="post" enctype="multipart/form-data">
<table>

<tr>
	<th>
		글번호 : <input type="text" name="boardno" value="${board.boardno }" id="boardno" readonly="readonly">
	</th>
	<th>제목 : ${board.title }</th>
	<th>아이디 : ${board.userid }(NINKNAME)</th>
	<th>조회수 : ${board.hit }</th>
</tr>

<tr>
	<td colspan="4"><textarea name="content">${board.content }</textarea></td>
</tr>
<tr>
	<td colspan = "4" style="text-align: center;">
		<div id="filelist">
			<c:forEach items="${upBoardFile }" var="boardFile">
				<span style="color: #333;">${boardFile.originname }</span>
			</c:forEach>
		</div>
		<div id="fileup" class="displayNone">
			<input type="file" name="upfile" multiple="multiple" >
		</div>
			<button id="fileUpdate" type="button">파일 수정</button>
	</td>
</tr>
<tr>
	<td colspan="2">
		<a href="<%=request.getContextPath()%>/board/list"><button type="button">목록</button></a>
		<button type="submit">수정완료</button>
	</td>
	<td>추천수 : </td>
	<td>작성일 : ${board.writeDate }</td>
</tr>

</table>
</form>

<c:import url="../layout/footer.jsp" />
