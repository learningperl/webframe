
function Select(t){
	if(t=="1"){
		document.getElementById("login").style.display='';
		document.getElementById("register").style.display='none';
	}else{
		document.getElementById("login").style.display='none';
		document.getElementById("register").style.display='';
	}
}


function Login() {
	var AjaxURL = "./user/login";
//	var encrypt = new JSEncrypt();
//	encrypt.setPublicKey(key);
//	var Param = 'loginName='+document.getElementsByName("loginName")[0].value+'&Pwd='+encodeURI(encrypt.encrypt(document.getElementsByName("Pwd")[0].value)).replace(/\+/g, '%2B');
	var Param = 'userName='+document.getElementsByName("loginName")[0].value+'&Pwd='+document.getElementsByName("Pwd")[0].value;
	//+号的处理：因为数据在网络上传输时，非字母数字字符都将被替换成百分号（%）后跟两位十六进制数，而base64编码在传输到后端的时候，+会变成空格，因此先替换掉。后端再替换回来
	$.ajax({  
        type:'post',      
        url:AjaxURL,  
        data:Param,  
        cache:false,  
        dataType:'json',  
        success:function(data){
        	if(data["status"]=="200")
        		window.location.href="./pages/";
        	else
        		 jAlert(data["msg"]);
        },
        error:function(data){
        	jAlert("服务器忙");
        }
    });  
}

function Regist() {
	var AjaxURL = "./user/register";
//	var encrypt = new JSEncrypt();
//	encrypt.setPublicKey(key);
//	var Param = 'loginName='+document.getElementsByName("loginName")[0].value+'&Pwd='+encodeURI(encrypt.encrypt(document.getElementsByName("Pwd")[0].value)).replace(/\+/g, '%2B');
	if(document.getElementsByName("username")[0].value == "username"){
		jAlert("请输入注册信息！");
		return;
	}
	var Param = 'userName='+document.getElementsByName("username")[0].value+'&Pwd='+document.getElementsByName("password")[0].value+'&nick='+document.getElementsByName("nickname")[0].value+'&desc='+document.getElementsByName("describe")[0].value;
	//+号的处理：因为数据在网络上传输时，非字母数字字符都将被替换成百分号（%）后跟两位十六进制数，而base64编码在传输到后端的时候，+会变成空格，因此先替换掉。后端再替换回来
	$.ajax({  
        type:'post',      
        url:AjaxURL,  
        data:Param,  
        cache:false,  
        dataType:'json',  
        success:function(data){
        	if(data["status"]=="200")
        		window.location.href="./";
        	else
        		 jAlert(data["msg"]);
        },
        error:function(data){
        	jAlert("服务器忙");
        }
    });  
}