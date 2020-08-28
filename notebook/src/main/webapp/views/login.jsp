<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <meta name="google-signin-client_id"
        content="143136051530-ag9juol91ooufdgc0cju5b8aac92bsc3.apps.googleusercontent.com">
    <title>Notebook</title>
    <link rel="stylesheet" href="views/resource/css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <script src="https://apis.google.com/js/platform.js" ></script>
</head>

<body>
    <div class="full-screen">
        <div class="login-box">
            <i class="fas fa-user-circle"></i>
            <div class="head-login-box">
                <h1>Notebook</h1>
                <p>Ghi mọi thứ quan trọng</p>
            </div>
            <form class="login-self" >
                <p class= "label">Email</p>                
                <input spellcheck="false" class="username" type="text" placeholder="Email hoặc username" required pattern="(?=.*[a-z])(?=.*[0-9]).{8,}" 
                                                title ="Phải có ít nhất 8 kí tự gồm chữ và số">
                <p class ="label">Mật khẩu</p>
                <input spellcheck="false" class="password" type="password" placeholder="Mật khẩu" required pattern="(?=.*[a-z])(?=.*[0-9]).{8,}" 
                                                title ="Phải có ít nhất 8 kí tự gồm chữ và số">
                <p class="error"></p>
                <button  type="submit" class ="sign-in-btn">Đăng nhập</button>
            </form>
            <div class="login-gg">
                <div class="g-signin2" data-onsuccess="onSignIn"></div>
            </div>
            <div class="has-account">
                <p>Bạn chưa có tài khoản ?</p>
                <a href="<%=request.getContextPath() %>/register">Tạo tài khoản</a>
            </div>
        </div>
    </div>

    <script>
        function onSignIn(googleUser) {
            var profile = googleUser.getBasicProfile();
            var imageUrl = profile.getImageUrl();
			var username = profile.getName();
			var emailAddress = profile.getEmail();
			var user = {
					name :username,
					image : imageUrl,
					email : emailAddress
			}
			var jSon = JSON.stringify(user);
            var xml = new XMLHttpRequest();
            xml.open ("POST","<%=request.getContextPath() %>/api-login-email",false);
            xml.setRequestHeader("Content-type", "application/json");
            xml.send(jSon);
            var id = xml.responseText;
            location.href= '<%=request.getContextPath() %>/home?id=' + id + '&kind=2';
        }
        $('form').submit (function (){
        	var username = document.getElementsByClassName('username')[0].value;
            var pw =  document.getElementsByClassName('password')[0].value;
             var user = {
                 name: username,
                 password: pw
             }
             var jSon = JSON.stringify(user);
             var xml = new XMLHttpRequest();
             xml.open ("POST","<%=request.getContextPath() %>/api-login-normal",false);
             xml.setRequestHeader("Content-type", "application/json");
             xml.send(jSon);
             var regex = /http/;
             var message = xml.responseText; 
             if (regex.test(message) == true){
            	 var URL = message.substring(6,message.length-1);
                 location.href=URL;
             } else {
            	 document.getElementsByClassName('error')[0].innerHTML = message ; 
             }
             return false;
        })
    </script>
    <script src="views/resource/js/script.js"></script>
</body>

</html>