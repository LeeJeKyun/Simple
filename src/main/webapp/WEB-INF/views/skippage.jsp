<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

</script>
</head>
<body>

<form action="http://kauth.kakao.com/oauth/token" method="post">

<input type="text" name="code" value="${code }">
<input type="text" name="grant_type" value="authorization_code">
<input type="text" name="redirect_uri" value="http://127.0.0.1:8888/kakao">
<input type="text" name="client_id" value="9e72a89439807704c06d91bdfb11064b">

<button id="submit">전송</button>
</form>

</body>
</html>