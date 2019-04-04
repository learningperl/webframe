
var obj;

function disappeare(id,msg) {
	document.getElementById(id).innerHTML = msg;
	setTimeout("Timeout",3000);
	Timeout(id);
}

function Timeout(id){
	//document.getElementById(id).innerHTML = "&nbsp";
}

function Init() {
	getCases();
	getMail();
}

function ShowTimer(s){
	if(s==1)
		document.getElementById("timer").hidden = true;
	else
		document.getElementById("timer").hidden = false;
}

function addCases(file){
	document.getElementById("filenames").value = document.getElementById("type").value + "," + file;
}

function showCases(type){
	if (obj != null)
		if (obj["status"] == "200") {
			var c = "";
			document.getElementById("Cases").innerHTML = "<option value=\"选择用例\">选择用例</option>";
			for (var i = 0; i < obj[type].length; i++) {
				c = "case" + (i + 1);
				document.getElementById("Cases").innerHTML += "<option value=\""
						+ obj["UI"][i][0][c]
						+ "\">"
						+ obj["UI"][i][0][c] + "</option>"
			}
		}
}

function getCases() {
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
			if (xmlhttp.status == 200){
				data = xmlhttp.responseText;
				obj = eval("(" + data + ")");
			}else{
				disappeare("MsgRun","服务器忙！");
			}
	};
}

function testMail() {
	var AjaxURL = "./TestMail";
	var Param = $("#mail").serialize();	
	$.ajax({  
        type:'post',      
        url:AjaxURL,  
        data:Param,  
        cache:false,  
        dataType:'json',  
        success:function(data){
        	if(data["status"]=="200")
        		disappeare("MsgMail",data["msg"]);
        	else
        		disappeare("MsgMail",data["msg"]);
        },
        error:function(data){
        	disappeare("MsgMail","服务器忙！");
        }
    });  
}

function getMail() {
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var url = "./GetMail";
	var data = "";
	xmlhttp.open("GET", url, true);
	xmlhttp.send();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4)
			if (xmlhttp.status == 200){
				data = xmlhttp.responseText;
				obj = eval("(" + data + ")");
				document.getElementById("sendnick").value = obj["sendnick"];
				document.getElementById("email").value = obj["email"];
				document.getElementById("recievers").value = obj["recievers"];
				document.getElementById("copylist").value = obj["copylist"];
			}else{
				disappeare("MsgMail","服务器忙！");
			}
	};
}


function UpdateMail() {
	var AjaxURL = "./UpdateMail";
	var Param = $("#mail").serialize();	
	$.ajax({  
        type:'post',      
        url:AjaxURL,  
        data:Param,  
        cache:false,  
        dataType:'json',  
        success:function(data){
        	if(data["status"]=="200"){
        		disappeare("MsgMail",data["msg"]);
        	}else
        		disappeare("MsgMail",data["msg"]);
        },
        error:function(data){
        	disappeare("MsgMail","服务器忙！");
        }
    });  
}
