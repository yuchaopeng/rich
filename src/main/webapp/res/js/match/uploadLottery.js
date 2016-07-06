var fileTypes = [ ".png", ".gif", ".jpg", ".bmp" ];  
function uploadLotteryPhoto(){
	var fileName = $("#lottery").filebox("getValue");
	var suffix = fileName.substring(fileName.lastIndexOf("."));
	var inArray = $.inArray(suffix, fileTypes); 
	if(inArray == -1){
		alertMessage("提示","请上传正确的图片.");
		return;
	}
	
	var bettingRecordId = $("#bettingRecordId").val();
	$("#uploadLotteryForm").form("submit",{
		success: function (data) {
			var json = eval("("+data+")");
			if(json.result == 0){
				$("#lotteryImageUploadDiv #lotteryImageUploadId").val(json.bettingRecord.imageUpload.id);
				$("#lotteryImageUploadDiv #lotteryImageUploadName").text(json.bettingRecord.imageUpload.realName);
				$("#lotteryImageUploadDiv").show();
			}
			alertMessage("提示",json.message);
		}
	});
}


function cancelInputMatch(){
	$("#upload_lottery").dialog("close");
}

function viewLotteryImage(){
	var bettingRecordId = $("#bettingRecordId").val();
	var url = getRootPath()+"match/toViewLottery?bettingRecordId="+bettingRecordId;
	parent.window.$("#view_lottery").dialog({
		title:"查看彩票",
		href:url,
		width:540,
		height:540,
		modal:true
	});
}

function deleteLotteryImage(){
	var bettingRecordId = $("#bettingRecordId").val();
	var lotteryImageUploadId = $("#lotteryImageUploadDiv #lotteryImageUploadId").val();
	if(lotteryImageUploadId == ''){
		alertMessage("提示","您还未上传彩票.");
		return;
	}
	$.messager.confirm("操作提示", "确定要删除已上传的彩票吗？", function (data) {  
        if (data) {  
        	var url = getRootPath()+"match/deleteLottery?bettingRecordId="+bettingRecordId;
        	$.ajax({
        		type : 'POST',
        		url : url,
        		success : function(data){
        			var json = eval("("+data+")");
        			if(json.result == 0){
        				$("#lotteryImageUploadDiv #lotteryImageUploadId").val("");
        				$("#lotteryImageUploadDiv #lotteryImageUploadName").text("");
        				$("#lotteryImageUploadDiv").hide();
        			}
        			alertMessage("提示",json.message);
        		},
        	}); 
        }
    });  
}