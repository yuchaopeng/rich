function login() {
	if (!checkData()) {
		return;
	}
	$("#loginForm").form("submit",{
		success: function (data) {
			var json = eval("("+data+")");
			var result = json.result;
			if (result == 0) {//登陆成功
				window.location.href=getRootPath();
			} else {
				loginAlert(json.message);
			}
		}
	});
}

function checkData() {
	var mobile = $.trim($("input[name='mobile']").val());
	var password = $.trim($("input[name='password']").val());
	$("input[name='mobile']").val(mobile);
	$("input[name='password']").val(password);

	/* 先清掉提示 */
	loginAlertClose();

	if (mobile == '') {
		loginAlert("请输入手机号");
		return false;
	}

	if (!/^1\d{10}$/.test(mobile)) {
		loginAlert("请输入正确的手机号");
		return false;
	}

	if (password == '') {
		loginAlert("请输入密码");
		return false;
	}
	return true;
}

function loginAlert(message) {
	$("#bootstrapAlert p").text(message);
	$("#bootstrapAlert").show();
}

function loginAlertClose() {
	$("#bootstrapAlert h4").text("");
	$("#bootstrapAlert").hide();
}