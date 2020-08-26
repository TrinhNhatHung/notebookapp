<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta name="google-signin-client_id"
	content="143136051530-ag9juol91ooufdgc0cju5b8aac92bsc3.apps.googleusercontent.com">
<title>Notebook</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="views/resource/css/styleindex.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script src="https://apis.google.com/js/platform.js" ></script>
</head>

<body>
	<div class="row">
		<div class="col-sm-2 nav">
			<div class="dropdown">
				<div class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
					onclick="handlerOnclick(event)">
					<img src="" alt="">
					<p>asdfgghj</p>
					<i class="fas fa-angle-down"></i>
				</div>
				<div class="clearfix"></div>
				<div class="dropdown-menu">
					<p class="para">Tài khoản</p>
					<div class="clearfix"></div>
					<div class="account">
						<i class="fas fa-check"></i> <img src="" alt="">
						<p>asdfghasa</p>
					</div>
					<div class="clearfix"></div>
					<div class="seperate"></div>
					<ul class="feature">
						<li><a href="#">Cài đặt</a></li>
						<li><a href="#">Trợ giúp</a></li>
					</ul>
					<div class="seperate"></div>
					<div class="login-gg" style="display: none;">
						<div class="g-signin2" data-onsuccess="onSignIn"></div>
					</div>
					<a href="http://localhost:8080/notebook/login"
						onclick="signOut();" class="signout-btn">Đăng xuất</a>
				</div>
			</div>
			<div class="search">
				<i class="fas fa-search"></i> <input type="text"
					placeholder="Tìm kiếm">
			</div>
			<div class="new-note" onclick="handlerOnclickNewNote (event)">
				<i class="fas fa-plus"></i>
				<p>Ghi chú mới</p>
			</div>
			<ul class="list-group">
				<li class="list-group-item item1"
					onclick="handlerOnclickMyNote(event)"><i class="fas fa-book"></i><span>Sổ
						tay của tôi</span></li>
				<li class="list-group-item item2"
					onclick="handlerOnclickMyBin(event)"><i
					class="fas fa-trash-alt"></i><span>Thùng rác</span></li>
				<li class="list-group-item item3"><i class="fas fa-bolt"></i><span>Nâng
						cấp</span></li>
			</ul>
		</div>
		<div class="col-sm-3 nav-notebook" style="display: none;">
			<div class="header-nav-note">
				<h5>Sổ tay của tôi</h5>
				<p class="number-of-note"><span>0</span> Ghi chú</p>
			</div>
			<div class="body-nav-note"></div>
		</div>
		<div class="col-sm-7 impl-notebook" style="display: none;">
			<div class="header-impl-note">
				<button class="delete" onclick="handlerOnclickDeleteNote(event)">Xóa</button>
				<button class="save"   onclick="handlerOnclickSaveNote(event)">Lưu</button>
			</div>
			<textarea name="" class="title-area" placeholder="Tiêu đề"
				spellcheck="false"></textarea>
			<textarea name="" class="title-content"
				placeholder="Bài viết mới" spellcheck="false"></textarea>
		</div>
		<div class="col-sm-3 nav-bin" style="display: none;">
			<div class="header-nav-bin">
				<h5>Thùng rác</h5>
				<p class="number-of-note"><span>0</span> Ghi chú</p>
			</div>
			<div class="body-nav-bin"></div>
		</div>
		<div class="col-sm-7 impl-bin" style="display: none;">
			<div class="header-impl-bin">
				<button class="delete" onclick="handlerOnclickDeleteBin(event)">Xóa khỏi thùng rác</button>
			</div>
			<h4 class="title-area"></h4>
			<p class="title-content"></p>
		</div>
	</div>
	<script src="/views/resource/js/scriptindex.js"></script>
	<script>
		document.getElementsByClassName("nav-notebook")[0].setAttribute("style", "display:inline-block;");
		function handlerOnclick(event) {
			document.getElementsByClassName("btn")[0].setAttribute("style",
					"background-color: #1a1a1a;")
		}

		function handlerOnclickNewNote(event) {
			$(".item2,.item3,.item1").removeClass("active");
			document.getElementsByClassName("nav-bin")[0].setAttribute("style",
					"display:none;");
			document.getElementsByClassName("nav-notebook")[0].setAttribute(
					"style", "display:inline-block;");
			var noteElement = document.createElement("article");
			var param0 = document.createElement('p');
            $(param0).addClass('article-id');
            $(param0).css('display','none');
            noteElement.appendChild(param0);
			var h6 = document.createElement("h6");
			h6.innerHTML = "Chưa có tiêu đề";
			var param1 = document.createElement('p');
			$(param1).addClass('article-content');
			var param2 = document.createElement("p");
			$(param2).addClass('article-time');
			var today = new Date();
			param2.innerHTML = today.getDate() + "-" + (today.getMonth() + 1)
					+ "-" + today.getFullYear();
			noteElement.appendChild(h6);
			noteElement.appendChild(param1);
			noteElement.appendChild(param2);
			$(noteElement).addClass('article-added');
			var element = document.getElementsByClassName("body-nav-note")[0];
			element.insertBefore(noteElement, document.querySelector('.body-nav-note article'));
			var note = {
				 title : "Chưa có tiêu đề",
				 content: "",
				 timeNote:  today.getFullYear()  + "/" +  (today.getMonth() + 1)
					+ "/" + today.getDate()
			}
			var noteJson =  JSON.stringify(note);
			var kind = '<%=session.getAttribute("kind")%>';
			var id = '<%=session.getAttribute("id")%>'
			noteJson += '-' + kind + '-' + id ;
		    var xml = new XMLHttpRequest();
            xml.open ("POST","http://localhost:8080/notebook/api-home-note",false);
            xml.setRequestHeader("Content-type", "application/json");
            xml.send(noteJson);
            param0.innerHTML = xml.responseText;
			$(noteElement).trigger('click');
			var numberString = $('.header-nav-note .number-of-note span').html();
	        var number = Number.parseInt(numberString);
	        $('.header-nav-note .number-of-note span').text(number + 1);
		}

		function handlerOnclickMyNote(event) {
			document.getElementsByClassName("impl-notebook")[0].setAttribute(
					"style", "display:none;");
			document.getElementsByClassName("impl-bin")[0].setAttribute(
					"style", "display:none;");
			document.getElementsByClassName("nav-bin")[0].setAttribute("style",
					"display:none;");
			document.getElementsByClassName("nav-notebook")[0].setAttribute(
					"style", "display:inline-block;");
			$(".item2,.item3").removeClass("active");
			$(".item1").addClass("active");
		    var node = document.querySelector('.body-nav-note .article-added');
			$(node).trigger('click');
		}

		function handlerOnclickMyBin(event) {
			document.getElementsByClassName("impl-bin")[0].setAttribute(
					"style", "display:none;");
			document.getElementsByClassName("impl-notebook")[0].setAttribute(
					"style", "display:none;");
			document.getElementsByClassName("nav-notebook")[0].setAttribute(
					"style", "display:none;");
			document.getElementsByClassName("nav-bin")[0].setAttribute("style",
					"display:inline-block;");
			$(".item1,.item3").removeClass("active");
			$(".item2").addClass("active");
			var node = document.querySelector('.body-nav-bin .article-added');
			$(node).trigger('click');
		}
		function handlerOnclickSaveNote(event){
			 var id = $('.note-article-active .article-id').html();
			 var title = $('.note-article-active h6').html();
			 var content = $('.note-article-active .article-content').html();
			 var titleArea = $('.title-area').val();
			 var contentArea = $('.title-content').val();
			 if (title != titleArea || content != contentArea ) {
				 var today = new Date();
				 var timeNote = today.getFullYear()  + "/" +  (today.getMonth() + 1)
					+ "/" + today.getDate();
				 var note = {
					 id :id,
					 title :titleArea,
					 content :contentArea,
					 timeNote :timeNote
				 }
				 var noteJson = JSON.stringify(note);
				 var xml = new XMLHttpRequest();
		         xml.open ("PUT","http://localhost:8080/notebook/api-home-note",false);
		         xml.setRequestHeader("Content-type", "application/json");
		         xml.send(noteJson); 
		         $('.note-article-active h6').text(titleArea);
		         $('.note-article-active .article-content').text(contentArea);
		         $('.note-article-active .article-time').text(today.getDate()  + "/" +  (today.getMonth() + 1)
							+ "/" + today.getFullYear() )
			 }
		}
		
        function handlerOnclickDeleteNote(event){
        	var id = $('.note-article-active .article-id').html();
        	var note = {
        			id : id,
        	}
        	var noteJson = JSON.stringify(note);
			var xml = new XMLHttpRequest();
	        xml.open ("DELETE","http://localhost:8080/notebook/api-home-note",false);
	        xml.setRequestHeader("Content-type", "application/json");
	        xml.send(noteJson); 
	        var thisElement = document.querySelector('.note-article-active');	        
	        $(thisElement).removeClass('note-article-active');
	        var thatElement = thisElement.cloneNode(true);
	        var bodyBin = document.querySelector('.body-nav-bin');
	        bodyBin.insertBefore(thatElement, document.querySelector('.body-nav-bin article'));
	        $(thisElement).remove();
	        var numberString = $('.header-nav-bin .number-of-note span').html();
	        var number = Number.parseInt(numberString);
	        $('.header-nav-bin .number-of-note span').text(number + 1);
	        var element = document.querySelector('.body-nav-note .article-added');
	        if (element == null){
	        	$('.impl-notebook').css('display','none');
	        } else {
	        	$(element).trigger('click');
	        }
	        var numberString = $('.header-nav-note .number-of-note span').html();
	        var number = Number.parseInt(numberString);
	        $('.header-nav-note .number-of-note span').text(number - 1);
	    }
		
        function handlerOnclickDeleteBin(event){
        	var id = $('.bin-article-active .article-id').html();
        	var note = {
        			id : id,
        	}
        	var noteJson = JSON.stringify(note);
			var xml = new XMLHttpRequest();
	        xml.open ("DELETE","http://localhost:8080/notebook/api-home-bin",false);
	        xml.setRequestHeader("Content-type", "application/json");
	        xml.send(noteJson); 
	        $('.bin-article-active').remove();
	        var element = document.querySelector('.body-nav-bin .article-added');
	        if (element == null){
	        	$('.impl-bin').css('display','none');
	        } else {
	        	$(element).trigger('click');
	        }	
	        var numberString = $('.header-nav-bin .number-of-note span').html();
	        var number = Number.parseInt(numberString);
	        $('.header-nav-bin .number-of-note span').text(number - 1);
        }
		
		$(document).ready(function() {
			$('body').on('click', '.body-nav-note .article-added', function() {
				$('.impl-bin').css('display', 'none');
				$('.impl-notebook').css('display', 'inline-block');
				var title = $(this).children('h6').html();
				var content = $(this).children('.article-content').html();
				if (title != 'Chưa có tiêu đề') {
					$('.impl-notebook .title-area').val(title);
				}
				$('.impl-notebook .title-content').val(content);
				$('.body-nav-note .article-added').removeClass('note-article-active');
                $(this).addClass('note-article-active');
			});
		});
		$(document).ready(function() {
			$('body').on('click', '.body-nav-bin .article-added', function() {
				$('.impl-notebook').css('display', 'none');
				$('.impl-bin').css('display', 'inline-block');
				var title = $(this).children('h6').html();
				var content = $(this).children('.article-content').html();				
				$('.impl-bin .title-area').text(title);
				$('.impl-bin .title-content').text(content);
				$('.body-nav-bin .article-added').removeClass('bin-article-active');
                $(this).addClass('bin-article-active');
			});
		});
		
		$('.nav .dropdown .btn img').attr('src', "<%=session.getAttribute("image")%>");
		$('.nav .dropdown .btn p').text("<%=session.getAttribute("username")%>");
		$('.account p').text("<%=session.getAttribute("username")%>");		
		var list = JSON.parse('${jSonOfList}');
		var bin = new Array();
		var notebook = new Array();
	    list.forEach ( value => {
	    	var noteElement = document.createElement("article");
			var param0 = document.createElement('p');
            $(param0).addClass('article-id');
            $(param0).css('display','none');
            param0.innerHTML= value.id;
            noteElement.appendChild(param0);
			var h6 = document.createElement("h6");
			h6.innerHTML = value.title;
			var param1 = document.createElement('p');
			$(param1).addClass('article-content');
			param1.innerHTML = value.content;
			var param2 = document.createElement("p");
			$(param2).addClass('article-time');
			var timeNote = value.timeNote.toString();
			var timeArray = timeNote.split('-');
			param2.innerHTML = timeArray[2] + '-' +timeArray[1] + '-' + timeArray[0]; 
			noteElement.appendChild(h6);
			noteElement.appendChild(param1);
			noteElement.appendChild(param2);
			$(noteElement).addClass('article-added');
			
	    	 if (value.deleted == false){
	    		 var element = document.getElementsByClassName("body-nav-note")[0];
		 			element.insertBefore(noteElement,document.querySelector(".body-nav-note .article-added"));
	    		 notebook.push(value);
	    	 } else {
	    		 var element = document.getElementsByClassName("body-nav-bin")[0];
		 			element.insertBefore(noteElement,document.querySelector(".body-nav-bin .article-added"));
	    		 bin.push(value);
	    	 }
	    })
	    $('.header-nav-note .number-of-note span').text(notebook.length);
	    $('.header-nav-bin .number-of-note span').text(bin.length);
		$('.item1').trigger('click');
		function signOut() {
		    var auth2 = gapi.auth2.getAuthInstance();
			auth2.signOut().then(function() {
				console.log('User signed out.');
			});
			<%session.invalidate();%>
		}
	</script>
</body>
</html>