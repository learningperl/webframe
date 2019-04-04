var index=-1;
var f="";
var t="";

function operate() {
	setTimeout("disappeare()", 3000);
}
function disappeare() {
	document.getElementById('Msg').innerHTML = "&nbsp";
}

function Init() {
	getInfo();
	SearchAll();
	document.getElementById("debug").href = "autoTest://\"debug\" \""
			+ window.location.href + "\" \"3\" \"4\"";
}

function Jump(){
	window.open("./config.html");
}

function Show(str){
	document.getElementById("nav").style.display = str;
}

function getInfo() {
	var AjaxURL = "./getUserInfo";
	$
			.ajax({
				type : "POST",
				dataType : "html",
				url : AjaxURL,
				success : function(result) {
					data = result;
					var obj = eval("(" + data + ")");
					if (obj["status"] == "200") {
						document.getElementById("userinfo").innerText = obj["nickname"];
					} else {
						document.getElementById("Msg").innerText = obj["msg"];
						operate();
					}
				},
				error : function(data) {
					document.getElementById("Msg").innerText = "服务器忙！请稍后重试。";
					operate();
				}
			});
}

function AjaxLogout() {
	var msg = "您真的确定要退出登录吗？";
	if (confirm(msg)==true){
		var AjaxURL = "./logout";
		$.ajax({
			type : "POST",
			dataType : "html",
			url : AjaxURL,
			success : function(result) {
				location="../index.html";
			},
			error : function(data) {
				document.getElementById("Msg").innerText = "服务器忙！请稍后重试。";
				operate();
			}
		});
	}else{
		return;
	}
}

function ajaxSubmitForm(file) {
	var value = document.getElementById(file).value;
	if (value == "") {
		document.getElementById("Msg").innerText = "请先选择文件";
		operate();
		return false;
	}
	if (!value.match(/.xls/i)) {
		document.getElementById("Msg").innerText = "文件格式错误";
		return false;
	}

	var formData = new FormData();
	formData.append("type", "upload");
	formData.append("name", file);
	formData.append(file, $("#" + file)[0].files[0]);
	$.ajax({
		url : "./UploadServlet",
		type : 'POST',
		data : formData,
		// 告诉jQuery不要去处理发送的数据
		processData : false,
		// 告诉jQuery不要去设置Content-Type请求头
		contentType : false,
		beforeSend : function() {
			document.getElementById("Msg").innerText = "正在上传，请稍等...";
		},
		success : function(responseStr) {
			var obj = eval("(" + responseStr + ")");
			if (obj["status"] == "200") {
				document.getElementById("Msg").innerText = obj["msg"];
				SearchAll();
				operate();
			} else {
				document.getElementById("Msg").innerText = obj["msg"];
				operate();
			}
		},
		error : function(responseStr) {
			document.getElementById("Msg").innerText = "服务器忙，请稍后重试！";
			operate();
		}
	});
}

function show(id) {
	var div = [ "child1", "child2", "child3" ]
	for (i = 0; i < 3; i++) {
		document.getElementById(div[i]).style.display = (i == id ? "block"
				: "none")
	}
}

function runExe(type) {
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var url = "./runCases?filename=" +getCookie("filename");
	var data = "";
	xmlhttp.open("post", url, true);
	xmlhttp.send();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4)
			if (xmlhttp.status == 200)
				data = xmlhttp.responseText;
		// alert(data);
		var obj;
		try{
			obj = eval("(" + data + ")");
		}catch(e){
			
		}
		if (obj != null)
			if (obj["status"] == "200") {
				var filename = "";
				if (type == "UI") {
					filename = document.getElementById("select_UI").value;
				}

				if (type == "APP") {
					filename = document.getElementById("select_APP").value;
				}

				if (type == "Interface") {
					filename = document.getElementById("select_Interface").value;
				}
				if (filename == "" || filename == " " || filename == "选择用例") {
					document.getElementById("Msg").innerText = "请先选择要运行的用例";
					operate();
				} else {
					if(index==-1){
						alert("服务器忙，请重试！");
						return;
					}
					document.cookie = 'filename=' + filename.substring(0,filename.indexOf(".")) + "-" + index + ".xls; path=/TestFrame;";
					document.cookie = 'type=' + type +"; path=/TestFrame;";
					document.getElementById("run").href = "autoTest://\"run\" \""
							+ obj["url"]
							+ "\" \""
							+ filename
							+ "\" \""
							+ getCookie("sid") + "\" \"" + type + "\"";
					document.getElementById("run").click();
				}
			} else {
				document.getElementById("Msg").innerText = obj["msg"];
				operate();
			}
	};

}

function getResName(type) {
	var xmlhttp;
	t=type;
	f=document.getElementById("select_UI").value;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var url = "./getResName?filename=" + f + "&type=" + t;
	var data = "";
	xmlhttp.open("GET", url, true);
	xmlhttp.send();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4)
			if (xmlhttp.status == 200)
				data = xmlhttp.responseText;
		// alert(data);
		try {
			var obj = eval("(" + data + ")");
			if (obj != null)
				if (obj["status"] == "200") {
					index = obj["name"];
				}
		} catch (e) {

		}
	};
}

function getCookie(name) {
	var arr = document.cookie.split('; ');
	var i = 0;
	for (i = 0; i < arr.length; i++) {
		// arr2->['username', 'abc']
		var arr2 = arr[i].split('=');

		if (arr2[0] == name) {
			var getC = decodeURIComponent(arr2[1]);
			return getC;
		}
	}

	return '';
}

function SearchAll() {
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var url = "./getCases";
	var data = "";
	xmlhttp.open("GET", url, true);
	xmlhttp.send();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4)
			if (xmlhttp.status == 200)
				data = xmlhttp.responseText;
		// alert(data);
		try {
			var obj = eval("(" + data + ")");
			if (obj != null)
				if (obj["status"] == "200") {
					var c = "";
					document.getElementById("select_UI").innerHTML = "<option value=\"选择用例\">选择用例</option>";
					for (var i = 0; i < obj["UI"].length; i++) {
						c = "case" + (i + 1);
						document.getElementById("select_UI").innerHTML += "<option value=\""
								+ obj["UI"][i][0][c]
								+ "\">"
								+ obj["UI"][i][0][c] + "</option>"
					}

					document.getElementById("select_APP").innerHTML = "<option value=\"选择用例\">选择用例</option>";
					for (var i = 0; i < obj["APP"].length; i++) {
						c = "case" + (i + 1);
						document.getElementById("select_APP").innerHTML += "<option value=\""
								+ obj["APP"][i][0][c]
								+ "\">"
								+ obj["APP"][i][0][c] + "</option>"
					}

					document.getElementById("select_Interface").innerHTML = "<option value=\"选择用例\">选择用例</option>";
					for (var i = 0; i < obj["Interface"].length; i++) {
						c = "case" + (i + 1);
						document.getElementById("select_Interface").innerHTML += "<option value=\""
								+ obj["Interface"][i][0][c]
								+ "\">"
								+ obj["Interface"][i][0][c] + "</option>"
					}
				}
		} catch (e) {

		}

	};
}
