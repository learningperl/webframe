var success = '<div id="msgsuccess" class="sufee-alert alert with-close alert-primary alert-dismissible fade show" style="position: fixed;display: table-footer-group;margin-left: auto;left: 0;top: 15px;margin-right: auto;width: 400px;right: 0;text-align: center;"><span class="badge badge-pill badge-primary">Success</span>&emsp;msg<button id="close" type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button></div>';
var error = '<div id="msgerror" class="sufee-alert alert with-close alert-danger alert-dismissible fade show" style="position: fixed;display: table-footer-group;margin-left: auto;left: 0;top: 15px;margin-right: auto;width: 400px;right: 0;text-align: center;"><span class="badge badge-pill badge-danger">error</span>&emsp;msg<button id="close" type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button></div>';
var warn = '<div id="msgwarn" class="sufee-alert alert with-close alert-warning alert-dismissible fade show" style="position: fixed;display: table-footer-group;margin-left: auto;left: 0;top: 15px;margin-right: auto;width: 400px;right: 0;text-align: center;"><span class="badge badge-pill badge-warning">info</span>&emsp;msg<button id="close" type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button></div>';
var onecase = '<div class="col-md-6"><div class="card"><div class="card-header"><strong>casename&emsp;</strong><small><code>&lt;type&gt;</code></small><small style="float:right;top: 4px;position: relative;"><code>&lt;createtime&gt;</code></small></div><div class="card-body"><a class="btn btn-outline-primary" href="javascript:edit(\'caseid\');" role="button">查看</a>&emsp;<a class="btn btn-outline-warning" href="javascript:deatails(\'caseid\');" role="button">Link</a>&emsp;<a class="btn btn-outline-danger" href="javascript:dialogBox(\'您确认删除该用例？\',\'caseid\');" role="button">删除</a></div></div><!-- /# card --></div>';
var sheetshow = '<a class="nav-item nav-link active show" data-toggle="tab" href="#sheet1" role="tab"  aria-selected="true" style="font-size: 20px;">sheetname</a>';
var sheet = '<a class="nav-item nav-link" data-toggle="tab" href="#sheet1" role="tab" aria-selected="false" style="font-size: 20px;">sheetname</a>';
var tableshow = '<div class="tab-pane fade active show" id="sheet1" role="tabpanel" aria-labelledby="custom-nav-home-tab"><div class="card"><div class="card-body" style="padding-top:20px;"><table id="bootstrap-data-table" class="table table-striped table-bordered"><thead><tr><th>用例名</th><th>关键字</th><th>p1</th><th>p2</th><th>p3</th><th>状态</th></tr></thead><tbody>bodycontent</tbody></table></div></div></div>';
var table = '<div class="tab-pane fade" id="sheet1" role="tabpanel" aria-labelledby="custom-nav-home-tab"><div class="card"><div class="card-body" style="padding-top:20px;"><table id="bootstrap-data-table" class="table table-striped table-bordered"><thead><tr><th>用例名</th><th>关键字</th><th>p1</th><th>p2</th><th>p3</th><th>状态</th></tr></thead><tbody>bodycontent</tbody></table></div></div></div>';

var resdetail = '<div class="card" style="border: 2px solid;border-color: #41d841;"><div class="card-header"><h4>casename</h4></div><div class="card-body">';
var resbody='<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist"><li class="nav-item"><a class="nav-link" id="pills-home-tab" data-toggle="pill" href="#click-home" role="tab" aria-controls="pills-home" aria-selected="false">关键字</a></li><li class="nav-item"><a class="nav-link" id="pills-profile-tab" data-toggle="pill" href="#click-profile" role="tab" aria-controls="pills-profile" aria-selected="false">参数</a></li><li class="nav-item"><a class="nav-link active show" id="pills-contact-tab" data-toggle="pill" href="#click-contact" role="tab" aria-controls="pills-contact" aria-selected="true">实际结果</a></li></ul><div class="tab-content" id="pills-tabContent"><div class="tab-pane fade" id="click-home" role="tabpanel" aria-labelledby="pills-home-tab"><p>keywordname</p> </div><div class="tab-pane fade" id="click-profile" role="tabpanel" aria-labelledby="pills-profile-tab"><p>params</p></div><div class="tab-pane fade show active" id="click-contact" role="tabpanel" aria-labelledby="pills-contact-tab"><p>actualresult</p></div></div>';

var reses = null;

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
			//document.getElementById("user").src = "images/avatar/"+ get_cookie("img");

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

function getRess() {
	var AjaxURL = "../user/getress";
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
				var ress = obj['ress'];
				reses = ress;
				for (var i = 0; i < ress.length; i++) {
					var c = onecase.replace(/caseid/g, ress[i]['id']);
					c = c.replace(/casename/g, ress[i]['resName']);
					c = c.replace(/type/g, ress[i]['type']);
					c = c.replace(/createtime/g,
							getLocalTime(ress[i]['startTime']));
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
	
	AjaxURL = "../user/getcases";
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
				var ress = obj['cases'];
				for (var i = 0; i < ress.length; i++) {
					var r = '<option value ="'+ress[i]["id"]+'">'+ress[i]["caseName"]+'</option>';
					document.getElementById("cases").innerHTML += r;
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

function showRess(){
	var caseid = $("#cases option:selected").val();
	document.getElementById("row").innerHTML = '<div class="col-md-6"><div class="card"><div class="card-body" style="height: 132px;text-align: center;"><div style="border: 1px dashed #ccc;height: 95px;top: center;/* position: relative; */"><img src="images/upload.jpg" style="width: auto;float:left;top: 11px;position: relative;max-height: 71px;"><input id="filename" class="btn btn-outline-info" type="text" value="文件名" style="top: 26px;position: relative;text-align:left;" readonly="readonly"><input id="u1" class="btn btn-outline-info" type="button" value="选择用例" style="float: right;height: 95px;display:block;" onclick="javascript:document.getElementById(\'upload\').click();"><input id="u2" class="btn btn-outline-success" type="button" value="上传用例" style="float: right;height: 95px;display:none;" onclick="javascript:Upload();"><input id="upload" type="file" name="fileUpload" accept=".xls,.xlsx" style="display:none;" onchange="javascript:onUpload();"> </div></div></div><!-- /# card --></div>'
	if(caseid==0){
		for (var i = 0; i < reses.length; i++) {
			var c = onecase.replace(/caseid/g, reses[i]['id']);
			c = c.replace(/casename/g, reses[i]['resName']);
			c = c.replace(/type/g, reses[i]['type']);
			c = c.replace(/createtime/g,
					getLocalTime(reses[i]['startTime']));
			document.getElementById("row").innerHTML += c;
		}
	}else{
		for (var i = 0; i < reses.length; i++) {
			if(reses[i]['id']==caseid){
				var c = onecase.replace(/caseid/g, reses[i]['id']);
				c = c.replace(/casename/g, reses[i]['resName']);
				c = c.replace(/type/g, reses[i]['type']);
				c = c.replace(/createtime/g,
						getLocalTime(reses[i]['startTime']));
				document.getElementById("row").innerHTML += c;
			}
		}
	}
}

function getReport() {
	
	AjaxURL = "../user/getress";
	$.ajax({
		url : AjaxURL,
		type : 'GET',
		data : null,
		// 告诉jQuery不要去处理发送的数据
		processData : false,
		async : false,
		// 告诉jQuery不要去设置Content-Type请求头
		contentType : false,
		beforeSend : function() {
			// document.getElementById("Msg").innerText = "正在上传，请稍等...";
		},
		success : function(responseStr) {
			var obj = eval("(" + responseStr + ")");
			if (obj["status"] == 200) {
				var ress = obj['ress'];
				reses = ress;
				for (var i = 0; i < ress.length; i++) {
					var r = '<option value ="'+ress[i]["id"]+'">'+ress[i]["resName"]+'</option>';
					document.getElementById("ress").innerHTML += r;
				}
				showReport();
			} else {
				showmsg(2, obj["msg"]);
			}
		},
		error : function(responseStr) {
			showmsg(2, "服务器忙，请稍后重试！");
		}
	});
	
}

function showReport(){
	var resid = $("#ress option:selected").val();
	var html='<li class="list-group-item"><a href="#"> <i class="fa fa-envelope-o"></i> Name <span class="badge badge-primary pull-right">Count</span></a></li><li class="list-group-item"><a href="#"> <i class="fa fa-bell-o"></i> Rate <span class="badge badge-success pull-right">passrate</span></a></li><li class="list-group-item"><a href="#"> <i class="fa fa-tasks"></i> PASS | FAIL | N/A <span class="badge badge-warning pull-right r-activity">na </span><span class="badge badge-danger pull-right">fail </span><span class="badge badge-success pull-right">pass </span></a></li><li class="list-group-item"><a href="#"> <i class="fa fa-clock-o"></i> stattime <span style="float:right;font-size: 13px;">stime</span></a></li><li class="list-group-item"><a href="#"> <i class="fa fa-clock-o"></i> endtime <span style="float:right;font-size: 13px;">etime</span></a></li>';
	if(resid==0){
		html = html.replace(/Name/g, reses[0]['resName']);
		html = html.replace(/Count/g, reses[0]['count']);
		html = html.replace(/passrate/g, (reses[0]['pass']*100/reses[0]['count']).toFixed(2) + "%");
		html = html.replace(/pass/g, reses[0]['pass']);
		html = html.replace(/fail/g, reses[0]['fail']);
		html = html.replace(/na/g, reses[0]['count']-reses[0]['pass']-reses[0]['fail']);
		html = html.replace(/stime/g, getLocalTime(reses[0]['startTime']));
		html = html.replace(/etime/g, getLocalTime(reses[0]['endTime']));
		document.getElementById("stastic").innerHTML = html;
		
		// Pie chart flotPie1
		var piedata = [
			{ label: "PASS", data: [[1,reses[0]['pass']*100/reses[0]['count']]], color: '#66bb6a'},
			{ label: "FAIL", data: [[1,reses[0]['fail']*100/reses[0]['count']]], color: '#ef5350'},
			{ label: "N/A", data: [[1,(reses[0]['count']-reses[0]['fail']-reses[0]['pass'])*100/reses[0]['count']]], color: '#ffc107'}
		];

		$.plot('#flotPie1', piedata, {
			series: {
				pie: {
					show: true,
					radius: 1,
					innerRadius: 0.65,
					label: {
						show: true,
						radius: 2/3,
						threshold: 1
					},
					stroke: {
						width: 0
					}
				}
			},
			grid: {
				hoverable: true,
				clickable: true
			}
		});
            // Pie chart flotPie1  End
	}else{
		for (var i=0;i<reses.length;i++){
			if(reses[i]['id']==resid){
				html = html.replace(/Name/g, reses[i]['resName']);
				html = html.replace(/Count/g, reses[i]['count']);
				html = html.replace(/passrate/g, (reses[i]['pass']*100/reses[i]['count']).toFixed(2) + "%");
				html = html.replace(/pass/g, reses[i]['pass']);
				html = html.replace(/fail/g, reses[i]['fail']);
				html = html.replace(/na/g, reses[i]['count']-reses[i]['pass']-reses[i]['fail']);
				html = html.replace(/stime/g, getLocalTime(reses[i]['startTime']));
				html = html.replace(/etime/g, getLocalTime(reses[i]['endTime']));
				document.getElementById("stastic").innerHTML = html;
				
				// Pie chart flotPie1
				var piedata = [
					{ label: "PASS", data: [[1,reses[i]['pass']*100/reses[i]['count']]], color: '#66bb6a'},
					{ label: "FAIL", data: [[1,reses[i]['fail']*100/reses[i]['count']]], color: '#ef5350'},
					{ label: "N/A", data: [[1,(reses[i]['count']-reses[i]['fail']-reses[i]['pass'])*100/reses[i]['count']]], color: '#ffc107'}
				];

				$.plot('#flotPie1', piedata, {
					series: {
						pie: {
							show: true,
							radius: 1,
							innerRadius: 0.65,
							label: {
								show: true,
								radius: 2/3,
								threshold: 1
							},
							stroke: {
								width: 0
							}
						}
					},
					grid: {
						hoverable: true,
						clickable: true
					}
				});
			// Pie chart flotPie1  End
			}
		}
	}
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

function getResDetails(){
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
			showmsg(3, "正在加载详细结果");
		},
		success : function(responseStr) {
			var obj = eval("(" + responseStr + ")");
			if (obj["status"] == 200) {
				var cases = obj["cases"];
				if (cases.length<1){
					document.getElementById('sheets').innerHTML = '<div id="msgwarn" class="sufee-alert alert with-close alert-warning alert-dismissible fade show" style="display: table-footer-group;margin-left: auto;left: 0;top: 15px;margin-right: auto;width: 400px;right: 0;text-align: center;"><span class="badge badge-pill badge-warning">info</span>&emsp;您还没有选择要查看的结果文件<a href="./cases.html">上传/查看</a></div>';
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
							c += '<tr role="row" class="odd" ondblclick="showtr(this)">'
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
							
							if(cases[i]["type"]==2){
								var r = resdetail + resbody;
								r = r.replace(/click-home/g,"click-home"+i);
								r = r.replace(/click-contact/g,"click-contact"+i);
								r = r.replace(/click-profile/g,"click-profile"+i);
								r = r.replace(/casename/g,getValue(cases[i],"caseName"));
								var param = "param1："+getValue(cases[i],"param1")+"</br>param2："+getValue(cases[i],"param2") + "</br>param3："+getValue(cases[i],"param3");
								r = r.replace(/params/g,param);
								r = r.replace(/keywordname/g,getValue(cases[i],"keyWord"));
								var act = "PASS";
								if (getValue(cases[i],"actual")!=""){
									act = getValue(cases[i],"actual");
								}
								r = r.replace(/actualresult/g,act);
								c += '</tr><tr ondblclick="showtrt(this)" style="display:none;"><td colspan="6">'+r+'</div></div></td></tr><tr></tr>';
							}else{
								var r = resdetail;
								r = r.replace(/casename/g,getValue(cases[i],"caseName"));
								r = r + "这是分组信息"
								c += '</tr><tr ondblclick="showtrt(this)" style="display:none;"><td colspan="6">'+r+'</div></div></td></tr><tr></tr>';
							}
						}
					}
					if (c!=""){
						c = t.replace(/bodycontent/g,"sheet"+c);
						document.getElementById('tabContent').innerHTML += c;
					}
					$('#bootstrap-data-table-export').DataTable();
					showmsg(1, "结果加载完成！");
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

function showtr(ele){
	if(ele.nextElementSibling.style.display == "")
		ele.nextElementSibling.style.display = "none";
	else
		ele.nextElementSibling.style.display = "";
}

function showtrt(ele){
	if(ele.style.display == "")
		ele.style.display = "none";
	else
		ele.style.display = "";
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
