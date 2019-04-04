var success = '<div id="msgsuccess" class="sufee-alert alert with-close alert-primary alert-dismissible fade show" style="position: fixed;display: table-footer-group;margin-left: auto;left: 0;top: 15px;margin-right: auto;width: 400px;right: 0;text-align: center;"><span class="badge badge-pill badge-primary">Success</span>&emsp;msg<button id="close" type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button></div>';
var error = '<div id="msgerror" class="sufee-alert alert with-close alert-danger alert-dismissible fade show" style="position: fixed;display: table-footer-group;margin-left: auto;left: 0;top: 15px;margin-right: auto;width: 400px;right: 0;text-align: center;"><span class="badge badge-pill badge-danger">error</span>&emsp;msg<button id="close" type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button></div>';
var warn = '<div id="msgwarn" class="sufee-alert alert with-close alert-warning alert-dismissible fade show" style="position: fixed;display: table-footer-group;margin-left: auto;left: 0;top: 15px;margin-right: auto;width: 400px;right: 0;text-align: center;"><span class="badge badge-pill badge-warning">info</span>&emsp;msg<button id="close" type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button></div>';
var onecase = '<div class="col-md-6"><div class="card"><div class="card-header"><strong>casename&emsp;</strong><small><code>&lt;type&gt;</code></small><small style="float:right;top: 4px;position: relative;"><code>&lt;createtime&gt;</code></small></div><div class="card-body"><a class="btn btn-outline-primary" href="javascript:edit(\'caseid\');" role="button">查看</a>&emsp;<a class="btn btn-outline-warning" href="javascript:deatails(\'caseid\');" role="button">Link</a>&emsp;<a class="btn btn-outline-danger" href="javascript:dialogBox(\'您确认删除该用例？\',\'caseid\');" role="button">删除</a></div></div><!-- /# card --></div>';
var sheetshow = '<a class="nav-item nav-link active show" data-toggle="tab" href="#sheet1" role="tab"  aria-selected="true" style="font-size: 20px;">sheetname</a>';
var sheet = '<a class="nav-item nav-link" data-toggle="tab" href="#sheet1" role="tab" aria-selected="false" style="font-size: 20px;">sheetname</a>';
var tableshow = '<div class="tab-pane fade active show" id="sheet1" role="tabpanel" aria-labelledby="custom-nav-home-tab"><div class="card"><div class="card-body" style="padding-top:20px;"><table id="bootstrap-data-table" class="table table-striped table-bordered"><thead><tr><th>用例名</th><th>关键字</th><th>p1</th><th>p2</th><th>p3</th><th>状态</th></tr></thead><tbody>bodycontent</tbody></table></div></div></div>';
var table = '<div class="tab-pane fade" id="sheet1" role="tabpanel" aria-labelledby="custom-nav-home-tab"><div class="card"><div class="card-body" style="padding-top:20px;"><table id="bootstrap-data-table" class="table table-striped table-bordered"><thead><tr><th>用例名</th><th>关键字</th><th>p1</th><th>p2</th><th>p3</th><th>状态</th></tr></thead><tbody>bodycontent</tbody></table></div></div></div>';


jQuery(document).ready(
		function($) {
			var u = location.href;
			if(u.endsWith("pages/") || u.endsWith("index.html")){
				getRank();
			}
			if (get_cookie("img") == "") {
				var AjaxURL = "../user/getuserinfo";
				$.ajax({
					type : 'post',
					url : AjaxURL,
					data : null,
					cache : false,
					dataType : 'json',
					success : function(data) {
						if (data["status"] == 200) {
							document.cookie = 'img=' + data['img'];
						} else
							showmsg(2, obj["msg"]);
					},
					error : function(data) {
						showmsg(2, "服务器忙");
					}
				});
			}
			document.getElementById("user").src = "images/avatar/"
					+ get_cookie("img");

			"use strict";

			[].slice.call(document.querySelectorAll('select.cs-select'))
					.forEach(function(el) {
						new SelectFx(el);
					});

			jQuery('.selectpicker').selectpicker;

			$('.search-trigger').on('click', function(event) {
				event.preventDefault();
				event.stopPropagation();
				$('.search-trigger').parent('.header-left').addClass('open');
			});

			$('.search-close').on(
					'click',
					function(event) {
						event.preventDefault();
						event.stopPropagation();
						$('.search-trigger').parent('.header-left')
								.removeClass('open');
					});

			$('.equal-height').matchHeight({
				property : 'max-height'
			});

			// var chartsheight = $('.flotRealtime2').height();
			// $('.traffic-chart').css('height', chartsheight-122);

			// Counter Number
			$('.count').each(function() {
				$(this).prop('Counter', 0).animate({
					Counter : $(this).text()
				}, {
					duration : 3000,
					easing : 'swing',
					step : function(now) {
						$(this).text(Math.ceil(now));
					}
				});
			});

			// Menu Trigger
			$('#menuToggle').on('click', function(event) {
				var windowWidth = $(window).width();
				if (windowWidth < 1010) {
					$('body').removeClass('open');
					if (windowWidth < 760) {
						$('#left-panel').slideToggle();
					} else {
						$('#left-panel').toggleClass('open-menu');
					}
				} else {
					$('body').toggleClass('open');
					$('#left-panel').removeClass('open-menu');
				}

			});

			$(".menu-item-has-children.dropdown").each(
					function() {
						$(this).on(
								'click',
								function() {
									var $temp_text = $(this).children(
											'.dropdown-toggle').html();
									$(this).children('.sub-menu').prepend(
											'<li class="subtitle">'
													+ $temp_text + '</li>');
								});
					});

			// Load Resize
			$(window).on("load resize", function(event) {
				var windowWidth = $(window).width();
				if (windowWidth < 1010) {
					$('body').addClass('small-device');
				} else {
					$('body').removeClass('small-device');
				}

			});

			$(".userSubmit").on('click', function() {
				var AjaxURL = "../user/setuserinfo";
				$.ajax({
					type : 'post',
					url : AjaxURL,
					data : $(".userinfo").serialize(),
					cache : false,
					dataType : 'json',
					success : function(data) {
						if (data["status"] == 200) {
							var AjaxURL = "../user/getuserinfo";
							$.ajax({
								type : 'post',
								url : AjaxURL,
								data : null,
								cache : false,
								dataType : 'json',
								success : function(data) {
									if (data["status"] == 200) {
										document.cookie = 'img=' + data['img'];
										location.reload();
										showmsg(1, obj["msg"]);
									} else
										showmsg(2, obj["msg"]);
								},
								error : function(data) {
									showmsg(2, "服务器忙");
								}
							});
						} else
							showmsg(2, "服务器忙");
					},
					error : function(data) {
						showmsg(2, "服务器忙");
					}
				});
			});

		});
		
function getRank(){
	$.ajax({
	url : "../user/getrank",
	type : 'GET',
	data : null,
	// 告诉jQuery不要去处理发送的数据
	processData : false,
	async : false,
	// 告诉jQuery不要去设置Content-Type请求头
	contentType : false,
	success : function(responseStr) {
		var obj = eval("(" + responseStr + ")");
		if (obj["status"] == 201) {
			document.getElementById("users").innerText = obj["ucount"];
			document.getElementById("cases").innerText = obj["ccount"];
			document.getElementById("reports").innerText = obj["rcount"];
			showmsg(2, obj["msg"]);
		} else {
			if (obj["status"] == 200) {
				document.getElementById("users").innerText = obj["ucount"];
				document.getElementById("cases").innerText = obj["ccount"];
				document.getElementById("reports").innerText = obj["rcount"];
				var html = '';
				for (var i=0;i<obj["ranklist"].length;i++){
					html+='<tr><td class="serial">'+(i+1);
					html+='</td><td class="avatar"><div class="round-img"><a href="#"><img class="rounded-circle" src="images/avatar/'+obj["userlist"][i]["img"];
					html+='" alt=""></a></div></td><td>#'+obj["userlist"][i]["id"];
					html+='</td><td><span class="name">'+obj["userlist"][i]["userName"];
					html+='</span></td><td><span class="count">'+obj["ranklist"][i]["c1"];
					html+='</span></td><td><span class="count">'+obj["ranklist"][i]["c2"];
					if(i==0){
						html+='</span></td><td><span class="badge badge-complete">First</span></td></tr>';
					}
					if(i==1){
						html+='</span></td><td><span class="badge badge-complete">Second</span></td></tr>';
					}
					if(i==2){
						html+='</span></td><td><span class="badge badge-complete">Third</span></td></tr>';
					}
				}
				document.getElementById("ranklist").innerHTML = html;
				showmsg(0, obj["msg"]);
			}else{
				showmsg(2, obj["msg"]);
			}
		}
	},
	error : function(responseStr) {
		showmsg(2, "服务器忙，请稍后重试！");
	}
	});
}

function Upload() {
	var AjaxURL = "../user/upload";
	var value = document.getElementById("upload").value;
	if (value == "") {
		// document.getElementById("Msg").innerText = "文件名";
		return false;
	}
	if (!value.match(/.xls/.xlsx)) {
		// document.getElementById("Msg").innerText = "文件格式错误";
		return false;
	}

	var formData = new FormData();
	formData.append('upload', $("#upload")[0].files[0]);
	$.ajax({
		url : AjaxURL,
		type : 'POST',
		data : formData,
		// 告诉jQuery不要去处理发送的数据
		processData : false,
		// 告诉jQuery不要去设置Content-Type请求头
		contentType : false,
		beforeSend : function() {
			showmsg(3, "正在上传，请稍等");
			document.getElementById('u2').disabled = true;
		},
		success : function(responseStr) {
			var obj = eval("(" + responseStr + ")");
			if (obj["status"] == 200) {
				showmsg(1, obj["msg"]);
				location.reload();
			} else {
				showmsg(2, obj["msg"]);
			}
		},
		error : function(responseStr) {
			showmsg(2, "服务器忙，请稍后重试！");
		}
	});
	document.getElementById('u2').disabled = false;
}

function onUpload() {
	document.getElementById('u1').style.display = 'none';
	document.getElementById('u2').style.display = 'block';
	document.getElementById('filename').value = document
			.getElementById('upload').files[0].name;
}

function getCases() {
	var AjaxURL = "../user/getcases";
	$.ajax({
		url : AjaxURL,
		type : 'GET',
		data : null,
		// 告诉jQuery不要去处理发送的数据
		processData : false,
		// 告诉jQuery不要去设置Content-Type请求头
		contentType : false,
		beforeSend : function() {
			// document.getElementById("Msg").innerText = "正在上传，请稍等...";
		},
		success : function(responseStr) {
			var obj = eval("(" + responseStr + ")");
			if (obj["status"] == 200) {
				var cases = obj['cases'];
				for (var i = 0; i < cases.length; i++) {
					var c = onecase.replace(/caseid/g, cases[i]['id']);
					c = c.replace(/casename/g, cases[i]['caseName']);
					c = c.replace(/type/g, cases[i]['type']);
					c = c.replace(/createtime/g,
							getLocalTime(cases[i]['createTime']));
					document.getElementById("row").innerHTML += c;
				}
			} else {
				showmsg(2, obj["msg"]);
			}
		},
		error : function(responseStr) {
			showmsg(2, "服务器忙，请稍后重试！");
		}
	});
}

function delCase(caseid) {
	var AjaxURL = "../user/delcases?id="+caseid;
//	formData = new FormData();
//	formData.append('id',caseid);
	$.ajax({
		url : AjaxURL,
		type : 'GET',
		data : null,
		// 告诉jQuery不要去处理发送的数据
		processData : false,
		// 告诉jQuery不要去设置Content-Type请求头
		contentType : false,
		beforeSend : function() {
			showmsg(3, "正在删除用例");
		},
		success : function(responseStr) {
			var obj = eval("(" + responseStr + ")");
			if (obj["status"] == 200) {
				location.reload();
			} else {
				showmsg(2, obj["msg"]);
			}
		},
		error : function(responseStr) {
			showmsg(2, "服务器忙，请稍后重试！");
		}
	});
}

function getDetails(){
	if(get_cookie('caseid')==""){
		document.getElementById('sheets').innerHTML = '<div id="msgwarn" class="sufee-alert alert with-close alert-warning alert-dismissible fade show" style="display: table-footer-group;margin-left: auto;left: 0;top: 15px;margin-right: auto;width: 400px;right: 0;text-align: center;"><span class="badge badge-pill badge-warning">info</span>&emsp;您还没有上传或者选择用例<a href="./cases.html">上传/查看</a></div>';
	}else{
		$.ajax({
		url : "../user/getdetails?caseid="+get_cookie('caseid'),
		type : 'GET',
		data : null,
		// 告诉jQuery不要去处理发送的数据
		processData : false,
		// 告诉jQuery不要去设置Content-Type请求头
		contentType : false,
		beforeSend : function() {
			showmsg(3, "正在加载用例详情");
		},
		success : function(responseStr) {
			var obj = eval("(" + responseStr + ")");
			if (obj["status"] == 200) {
				var cases = obj["cases"];
				if (cases.length<1){
					document.getElementById('sheets').innerHTML = '<div id="msgwarn" class="sufee-alert alert with-close alert-warning alert-dismissible fade show" style="display: table-footer-group;margin-left: auto;left: 0;top: 15px;margin-right: auto;width: 400px;right: 0;text-align: center;"><span class="badge badge-pill badge-warning">info</span>&emsp;您还没有上传或者选择用例<a href="./cases.html">上传/查看</a></div>';
				}else{
					var s="";
					var sheetnum = 1;
					var t = "";
					var c = "";
					for (var i=0;i<cases.length;i++){
						if(cases[i]["type"]==3){
							if (c!=""){
								c = t.replace(/bodycontent/g,"sheet"+c);
								document.getElementById('tabContent').innerHTML += c;
							}
							if(i==0){
								s = sheetshow.replace(/sheet1/g,"sheet"+sheetnum);
								s = s.replace(/sheetname/g,cases[i]["caseName"]);
								t = tableshow.replace(/sheet1/g,"sheet"+sheetnum);
							}else{
								s = sheet.replace(/sheet1/g,"sheet"+sheetnum);
								s = s.replace(/sheetname/g,cases[i]["caseName"]);
								t = table.replace(/sheet1/g,"sheet"+sheetnum);
							}
							document.getElementById('sheets').innerHTML += s;
							
							sheetnum++;
							c = "";
						}else{
							c += '<tr role="row" class="odd">'
                            c += '<td class="cccc">'+getValue(cases[i],"caseName") +'</td>';
                            c += '<td class="cccc">'+getValue(cases[i],"keyWord")+'</td>';
                            c += '<td>'+getValue(cases[i],"param1")+'</td>';
                            c += '<td>'+getValue(cases[i],"param2")+'</td>';
							c += '<td>'+getValue(cases[i],"param3")+'</td>';
							if (getValue(cases[i],"status")=="N/A" || getValue(cases[i],"status")=="")
								c += '<td  class="NA">N/A</td>';
							else
								if (getValue(cases[i],"status")=="PASS")
									c += '<td  class="PASS">PASS</td>';
								else
									c += '<td  class="FAIL">FAIL</td>';
								
                            c += '</tr>';
						}
					}
					if (c!=""){
						c = t.replace(/bodycontent/g,"sheet"+c);
						document.getElementById('tabContent').innerHTML += c;
					}
					$('#bootstrap-data-table-export').DataTable();
					showmsg(1, "用例加载完成！");
				}
			} else {
				showmsg(2, obj["msg"]);
			}
		},
		error : function(responseStr) {
			showmsg(2, "服务器忙，请稍后重试！");
		}
	});
	}
}

function edit(casid){
	document.cookie = 'caseid=' + casid;
	location.href = "./edit.html";
}

function deatails(caseid){
	var AjaxURL = "../user/run?caseid="+caseid;
//	formData = new FormData();
//	formData.append('id',caseid);
	$.ajax({
		url : AjaxURL,
		type : 'GET',
		data : null,
		// 告诉jQuery不要去处理发送的数据
		processData : false,
		// 告诉jQuery不要去设置Content-Type请求头
		contentType : false,
		beforeSend : function() {
			showmsg(3, "正在准备数据");
		},
		success : function(responseStr) {
			var obj = eval("(" + responseStr + ")");
			if (obj["status"] == 200) {
				location.reload();
			} else {
				showmsg(2, obj["msg"]);
			}
		},
		error : function(responseStr) {
			showmsg(2, "服务器忙，请稍后重试！");
		}
	});
}

function getValue(obj,key){
	var value = "";
	try{
		if(obj[key]==undefined){
			value = "";
		}else{
			value = obj[key];
		}
	}catch (err) {
		value = "";
	}
	return value;
}

function getLocalTime(nS) {
	return new Date(parseInt(nS)).toLocaleString().replace(/:\d{1,2}$/, ' ');
}

function get_cookie(Name) {
	var search = Name + "="// 查询检索的值
	var returnvalue = "";// 返回值
	if (document.cookie.length > 0) {
		sd = document.cookie.indexOf(search);
		if (sd != -1) {
			sd += search.length;
			end = document.cookie.indexOf(";", sd);
			if (end == -1)
				end = document.cookie.length;
			// unescape() 函数可对通过 escape() 编码的字符串进行解码。
			returnvalue = unescape(document.cookie.substring(sd, end))
		}
	}
	return returnvalue;
}

function dialogBox(message,data){
	document.getElementById("msgalert").style.display = "block";
	document.getElementById("shade").style.display = "block";
	document.getElementById("msgs").innerText=message;
	$('#del').click(function(){
		document.getElementById("msgalert").style.display = "none";
		document.getElementById("shade").style.display = "none";
		delCase(data);
	});
	// 取消按钮
	$('#cancel').click(function(){
		document.getElementById("msgalert").style.display = "none";
		document.getElementById("shade").style.display = "none";
	});
}



function showmsg(t, msg) {
	try {
		document.getElementById('close').click();
	} catch (err) {
	}
	if(t==0){
		try {
			document.getElementById('close').click();
		} catch (err) {
		}
	}
	if (t == 1) {
		document.getElementById('header').innerHTML += success.replace(/msg/g,
				msg);
	}
	if (t == 2) {
		document.getElementById('header').innerHTML += error.replace(/msg/g,
				msg);
	}
	if (t == 3) {
		document.getElementById('header').innerHTML += warn
				.replace(/msg/g, msg);
	}
}
