
var page=1;
var ispage=false;
var issearch=false;

function operate() {
	setTimeout("disappeare()", 3000);
}
function disappeare() {
	document.getElementById('Msg').innerHTML = "";
}

function Jump(){
	window.open("./config.html");
}

function Show(str){
	document.getElementById("nav").style.display = str;
}

function Init(){
	document.getElementById("userinfo").innerText = getCookie("user");
	getKeyWrods();
	
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

	return 'null';
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

function getKeyWrods() {
	if(!ispage)
		page=1;
	var s="search";
	if(issearch)
		s=document.getElementById("searchText").value;
	
	var AjaxURL = "./getKeyWords?Search=" + s + "&type=" + document.getElementById("select_t").value + "&page=" + page;
	$.ajax({
		type : "Get",
		dataType : "html",
		url : AjaxURL,
		success : function(result) {
				data = result;
				var objs = eval("(" + data + ")");
				objs = objs["keyWords"];
				var html="<li style=\"display: list-item; opacity: 1;\"><span class=\"avatar\">Index</span><span>Name</span><span>Describe</span><span>Usage</span></li>";
				try 
				{ 
//					if(objs.length<10)
//						document.getElementById("next").hidden = true;
//					else
//						document.getElementById("next").hidden = false;
					
//					if(page=="1"){
//						document.getElementById("back").hidden = true
//					}else
//						document.getElementById("back").hidden = false;
				} 
				catch (e) 
				{
				} 
				if(objs.length>0){
					for (var i=0;i<objs.length;i++)
					{
						//var obj= eval("(" + objs[i] + ")");
						html += "<li style=\"display: list-item; opacity: 1;\"><span class=\"avatar\">"+(i+1)+"</span><span>"+objs[i]["keyword"]+"</span><span>"+objs[i]["describes"]+"</span><span title=\""+objs[i]["uses"]+"\">"+objs[i]["uses"]+"</span></li>";
					}
					document.getElementById("myTable").innerHTML = html;
				}else
					document.getElementById("myTable").innerHTML = html + "<li style=\"display: list-item; opacity: 1;\"><span class=\"avatar\">1</span><span>系统提示</span><span>查询结果</span><span>查询结果为空，请检查查询条件</span></li>";

		},
		error : function(data) {
			document.getElementById("Msg").innerText = "服务器忙！请稍后重试。";
			operate();
		}
	});
}

function LastPage(){
	ispage=true;
	if(page>1)
		page=page-1;
	getKeyWrods();
	ispage=false;
}

function NextPage(){
	ispage=true;
	page=page+1;
	getKeyWrods();
	ispage=false;
}

function searchKey(){
	issearch=true;
	getKeyWrods();
	issearch=false;
}

