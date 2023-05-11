<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.1.0/kakao.min.js"
	  integrity="sha384-dpu02ieKC6NUeKFoGMOKz6102CLEWi9+5RQjWSV0ikYSFFd8M3Wp2reIcquJOemx" crossorigin="anonymous"></script>
<script>
  Kakao.init('f020491197bfb81f37566adfe6725a03'); // 사용하려는 앱의 JavaScript 키 입력
</script>

<script>
  function loginWithKakao() {
    Kakao.Auth.authorize({
      redirectUri: 'http://localhost:8888/kakao',
    });
  }

  // 아래는 데모를 위한 UI 코드입니다.
  displayToken()
  function displayToken() {
    var token = getCookie('authorize-access-token');

    if(token) {
      Kakao.Auth.setAccessToken(token);
      Kakao.Auth.getStatusInfo()
        .then(function(res) {
          if (res.status === 'connected') {
            document.getElementById('token-result').innerText
              = 'login success, token: ' + Kakao.Auth.getAccessToken();
          }
        })
        .catch(function(err) {
          Kakao.Auth.setAccessToken(null);
        });
    }
  }

  function getCookie(name) {
    var parts = document.cookie.split(name + '=');
    if (parts.length === 2) { return parts[1].split(';')[0]; }
  }
  
  function naverLogin() {
	  location.href='https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=a4rcAlMYkSCWs6VhXPqs&redirect_uri=http://localhost:8888/naver&state=STATE_STRING'
  }
  
</script>

</head>
<body>

<form action="./login" method="post">

아이디 <input type="text" name="userid">
패스워드 <input type="password" name="userid">

<button>전송</button> 	

<a id="kakao-login-btn" href="javascript:loginWithKakao()">
	  <img src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" width="222"
	    alt="카카오 로그인 버튼" />
	</a>
	<p id="token-result"></p><br>
<button onclick="naverLogin()" type="button">네이버 로그인 버튼</button>

</form>

</body>
</html>