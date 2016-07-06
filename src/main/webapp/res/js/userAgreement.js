function addUserAgreement() {
	var v = $("#agreementValue").textbox("getValue");
	$("#agreementValue").textbox("setValue",$.trim(v));
	
	
	if($("#agreementType").combobox("getValue") == 0){
		$("#agreementValue").textbox("setValue",0);
	}

	var agreementValue = $("#agreementValue").textbox("getValue");
	if (!/^\d+\.?\d{0,1}$/.test(agreementValue)) {
		alertMessage("提示", "“分成值”填写个格式不正确");
		return;
	}

	$("#addUserAgreementForm").form("submit",{
		success : function(data) {
			var result = eval("(" + data + ")");
			if (result.result == 0) {
				$("#add_user_agreement_dialog_div").dialog('close');
			} else {
				alertMessage("提示", result.message);
			}
		}
	});
}