function appLogin(){
	var f = checkFormData();
	if(!f){
		return;
	}
	
	var mobile = document.getElementById("mobile").value;
	var password = document.getElementById("password").value;
	AppAjax.ajax({
		url: "/app/login",              //请求地址
        type: "post",                       //请求方式
        data: { mobile: mobile, password: password },        //请求参数
        dataType: 'json',
        success: function (response, xml) {
        	var json = eval("("+response+")");
        	if(json.result == 0){
        		window.location.href="/app/planList";
        	}else{
        		document.getElementById("alert_msg").innerText = json.message;
        	}
        },
        fail: function (status) {
            // 此处放失败后执行的代码
        }
	});
}


function checkFormData(){
	var mobile = document.getElementById("mobile").value;
	var password = document.getElementById("password").value;
	mobile = trim(mobile);
	password = trim(password);
	document.getElementById("mobile").value = mobile;
	document.getElementById("password").value = password;
	
	document.getElementById("alert_msg").innerText = "";
	
	if(mobile == ''){
		document.getElementById("alert_msg").innerText = "请输入手机号.";
		return false;
	}
	
	if(!/^1\d{10}$/.test(mobile)){
		document.getElementById("alert_msg").innerText = "手机号格式不正确.";
		return false;
	}
	
	if(password == ''){
		document.getElementById("alert_msg").innerText = "请输入密码.";
		return false;
	}
	return true;
}

function trim(str){ //删除左右两端的空格 
	return str.replace(/(^\s*)|(\s*$)/g, ""); 
} 