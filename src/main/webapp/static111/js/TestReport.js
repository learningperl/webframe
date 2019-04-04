var obcases;
var details = "";

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

function Jump(){
	window.open("./config.html");
}

function Show(str){
	document.getElementById("nav").style.display = str;
}


function getRes() {
	document.getElementById("Tester").innerText = getCookie("user");
	var xmlhttp;
	t = getCookie("type");
	f = getCookie("filename");
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	document.getElementById("title").innerText = f;
	var url = "./getRes?filename=" + f + "&type=" + t;
	var data = "";
	xmlhttp.open("GET", url, true);
	xmlhttp.send();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4) {
			if (xmlhttp.status == 200) {
				data = xmlhttp.responseText;
				// alert(data);
				//try {
					var obj = eval("(" + data + ")");
					if (obj != null)
						if (obj["status"] == "200") {
							obcases = obj["result"];
							document.getElementById("time").innerText = obj["result"][obj["result"].length - 1]["endtime"];
							document.getElementById("rate").innerText = obj["result"][obj["result"].length - 1]["passrate"] + "%";
							if (obj["result"][obj["result"].length - 1]["passrate"] == "100.0") {
								document.getElementById("status").innerText = "Pass";
								document.getElementById("status").style.color = "blue";
							} else {
								document.getElementById("status").innerText = "Fail";
								document.getElementById("status").style.color = "red";
							}
							var size = obj["result"].length;
							if (size == 1) {
								// 需要提示
								return;
							}
						} else {
							return;
						}
//				} catch (e) {
//				
//				}
			}
			getCaseDetail();
		}
	};
}

function getCaseDetail() {
	var xmlhttp;
	t = getCookie("type");
	f = getCookie("filename");
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var url = "./getCaseDetail?filename=" + f + "&type=" + t;
	var data = "";
	xmlhttp.open("GET", url, true);
	xmlhttp.send();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4) {
			if (xmlhttp.status == 200) {
				data = xmlhttp.responseText;
				// alert(data);
				//try {
					var obj = eval("(" + data + ")");
					if (obj != null){
						if (obj["status"] == "200") {
							var size = obcases.length;
							var sizecase = obj["result"].length;
							var obj1 = obj["result"];
							var c=0;
							var classs=0;
							for (var i = 0; i < size - 1; i++) {
								var name = "";
								var body = "";
								var sizes = obcases[i]["count"];
								details += "<thead><tr><th>";
								details += obcases[i]["name"]
										+ "</th><th>Count</th><th>Pass</th><th>Fail</th><th>View</th></tr></thead><tbody>";
								
								var pass = 0;
								var fail = 0;
								var count = 0;
								for (var j = 0; j <=sizes; ) {
									var status = "";
									if(j==0){
										name = "";
										body="";
										name = obj1[c]["name"];
										c++;
										j++;
									}else{
										if(j!=0 && obj1[c]["status"] == undefined){
											details += "<tr><td>"
													+ name
													+ "</td><td><span class=\"am-badge am-badge-count\" data-type=\"count\">"
													+ count
													+ "</span></td><td><span class=\"am-badge am-badge-pass\" data-type=\"pass\">"
													+ pass;
											if(fail=="0")
												details	+= "</span></td><td><span class=\"am-badge am-badge-fail\" data-type=\"fail\">" + fail;
											else
												details	+= "</span></td><td><span style=\"background-color: red;\" class=\"am-badge am-badge-fail\" data-type=\"fail\">" + fail;
											
											details += "</span></td><td class=\"option\"><a data-target=\"one"+classs+"\">Detail</a></td></tr>"
											details += body;
											pass=0;
											fail=0;
											count=0;
											name = "";
											body="";
											classs++;
											name = obj1[c]["name"];
											c++;
										}else{
											while(j<=sizes && obj1[c]["status"] != undefined){
												if (obj1[c]["status"] == "PASS"){
													pass++;
													status = "<a>Pass";
												}else{
													fail++;
													status = "<a style=\"color: red;\">Fail";
												}
												count++;
												body += "<tr class=\"hiddenRow\" data-value=\"one"+classs+"\"><td colspan=\"2\"><p>"
														+ obj1[c]["name"]
														+ "</p><p>"
														+ obj1[c]["expect"]
														+ "</a></td><td colspan=\"5\"><a>"
														+ status + "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp result"
														+ "</a><p class=\"hiddenDetail\">"
														+ obj1[c]["details"]
														+"</p></td></tr>";
												c++;
												j++;
											}
										}
									}
								}
								details += "<tr><td>"
										+ name
										+ "</td><td><span class=\"am-badge am-badge-count\" data-type=\"count\">"
										+ obcases[i]["count"]
										+ "</span></td><td><span class=\"am-badge am-badge-pass\" data-type=\"pass\">"
										+ pass;
								if(fail=="0")
									details	+= "</span></td><td><span class=\"am-badge am-badge-fail\" data-type=\"fail\">" + pass;
								else
									details	+= "</span></td><td><span style=\"background-color: red;\" class=\"am-badge am-badge-fail\" data-type=\"fail\">" + fail;
								
								details += "</span></td><td class=\"option\"><a data-target=\"one"+classs+"\">Detail</a></td></tr>"
								details += body;
								details += "</tbody>";
								classs++;
							}
							document.getElementById("details").innerHTML = details;
							Box.Application.init();
							$(function() {
								$(".option a").click(function(){
							 		$(".hiddenRow[data-value='"+$(this).data("target")+"']").toggle();
							 	})
								$(".am-table.am-table-bd thead").click(function(){
									$(this).next().toggle();
								})
								$(".hiddenRow td:nth-child(2) a").click(function(){
									$(this).next().toggle();
								})

							});
						}
					}
//				} catch (e) {
//
//				}
			}
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