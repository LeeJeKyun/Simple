<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="./layout/header.jsp" />


<c:choose >
	<%-- session에 login프로퍼티가 비어있으면(로그아웃상태이면) --%>
	<c:when test="${empty login }">
		<a href="<%=request.getContextPath() %>/member/join"><button type="button">회원가입</button></a>
		<a href="<%=request.getContextPath() %>/member/login"><button type="button">로그인</button></a>
	</c:when>
	<c:otherwise>
		<a href="<%=request.getContextPath() %>/board/list"><button type="button">게시판목록</button></a>
		<a href="<%=request.getContextPath() %>/member/logout"><button type="button">로그아웃</button></a>
	</c:otherwise>
</c:choose>

<c:import url="./layout/footer.jsp" />
