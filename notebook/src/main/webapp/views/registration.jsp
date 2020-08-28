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
    <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
            <form class="login-self" action="http://localhost:8080/notebook/register" method="POST">
                <p>Email</p>                
                <input type="text" name="username" placeholder="Email hoặc username" required pattern="(?=.*[a-z])(?=.*[0-9]).{8,}" 
                     spellcheck = "false" title ="Phải có ít nhất 8 kí tự gồm chữ và số" 
                     style="margin-top:20px">
                <p>Mật khẩu</p>
                <input type="password" name="password" placeholder="Mật khẩu" required pattern="(?=.*[a-z])(?=.*[0-9]).{8,}" 
                     spellcheck = "false" title ="Phải có ít nhất 8 kí tự gồm chữ và số"
                     style="margin-top:20px">
                 <% 
                    if (request.getParameter("error")!=null){
                    	out.print("<p style='color:red'>Tên đăng nhập hoặc mật khẩu đã tồn tại</p>");
                    }
                  %>
                <button type="submit" class = "signup-btn" >Sign up</button>
            </form>
        </div>
    </div>
    <script src="/views/resource/js/script.js"></script>
    <script >
        
    </script>
</body>
</html>