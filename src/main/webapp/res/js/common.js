//js获取项目根路径，如： http://localhost:8083/uimcardprj
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPath=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    //var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    var projectName = "/";
    return(localhostPath+projectName);
}

/**  
 * 创建新选项卡  
 * @param tabId    选项卡id  
 * @param title    选项卡标题  
 * @param url      选项卡远程调用路径  
 */  
function addTab(tabId,title,url){
    var name = 'iframe_'+tabId;  
    var tabs = $("#centerTab");
    var opts = {  
    	id:tabId,
        title: title,  
        closable:true,  
        cache : false,
        //注：使用iframe即可防止同一个页面出现js和css冲突的问题  
        content : '<iframe name="'+name+'"id="'+tabId+'"src="'+url+'" width="100%" height="90%" frameborder="0" scrolling="auto"></iframe>'  
    };
    if(tabs.tabs("exists",opts.title)){
    	tabs.tabs('select', opts.title);
    	refreshTab();
    }else{
    	tabs.tabs('add',opts);
    }
}  

$(function(){
	$('#centerTab').tabs({
	    border:false,
	    onSelect:function(title,index){
	    	refreshTab();
	    }
	});
});


function refreshTab(){           
    var tab = $('#centerTab').tabs('getSelected');  
    var tbId = tab.attr("id");  
    //获取tab的iframe对象  
    var tbIframe = $("#"+tbId+" iframe:first-child");  
    if(tbIframe.length > 0){
    	tbIframe.attr('src', tbIframe.attr('src'));
	}
}

var sy = $.extend({}, sy);/*定义一个全局变量*/

sy.serializeObject = function (form) { /*将form表单内的元素序列化为对象，扩展Jquery的一个方法*/
    var o = {};
    $.each(form.serializeArray(), function (index) {
        if (o[this['name']]) {
            o[this['name']] = o[this['name']] + "," + this['value'];
        } else {
            o[this['name']] = this['value'];
        }
    });
    return o;
};


function alertMessage(title,message){
	parent.window.$.messager.alert(title,message);
}

//扩展easyui表单的验证
$.extend($.fn.validatebox.defaults.rules, {
    //验证汉子
    CHS: {
        validator: function (value) {
            return /^[\u0391-\uFFE5]+$/.test(value);
        },
        message: '只能输入汉字'
    },
    //移动手机号码验证
    mobile: {//value值为文本框中的值
        validator: function (value) {
            return /^1\d{10}$/.test(value);
        },
        message: '输入手机号码格式不准确.'
    },
    //国内邮编验证
    zipcode: {
        validator: function (value) {
            var reg = /^[1-9]\d{5}$/;
            return reg.test(value);
        },
        message: '邮编必须是非0开始的6位数字.'
    },
    email : {
    	validator:function(value){
    		return /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/.test(value);
    	},
    	message:"输入的邮箱格式不正确."
    },
	equalTo : {
		validator : function(value, param) {
			return $(param[0]).val() == value;
		},
		message : '输入不匹配'
	},
	password : {
		validator : function(value) {
			return /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}$/.test(value);
		},
		message : '新密码须为6位（含）以上数字与字符组合.'
	},
	decimalNum : {
		validator : function(value) {
			return /^\d+\.?\d{0,1}$/.test(value);
		},
		message : '数值格式不正确.'
	},
    //用户账号验证(只能包括 _ 数字 字母) 
    account: {//param的值为[]中值
        validator: function (value, param) {
            if (value.length < param[0] || value.length > param[1]) {
                $.fn.validatebox.defaults.rules.account.message = '用户名长度必须在' + param[0] + '至' + param[1] + '范围';
                return false;
            } else {
                if (!/^[\w]+$/.test(value)) {
                    $.fn.validatebox.defaults.rules.account.message = '用户名只能数字、字母、下划线组成.';
                    return false;
                } else {
                    return true;
                }
            }
        }, message: ''
    }
});


function disableForm(formId,isDisabled) {  
    
    var attr="disable";  
    if(!isDisabled){  
       attr="enable";  
    }  
    $("form[id='"+formId+"'] :text").attr("disabled",isDisabled);  
    $("form[id='"+formId+"'] textarea").attr("disabled",isDisabled);  
    $("form[id='"+formId+"'] select").attr("disabled",isDisabled);  
    $("form[id='"+formId+"'] :radio").attr("disabled",isDisabled);  
    $("form[id='"+formId+"'] :checkbox").attr("disabled",isDisabled);  
    $("form[id='"+formId+"'] a").attr("disabled",isDisabled);
      
    //禁用jquery easyui中的下拉选（使用input生成的combox）  
  
    $("#" + formId + " input[class='combobox-f combo-f']").each(function () {  
        if (this.id) {alert("input"+this.id);  
            $("#" + this.id).combobox(attr);  
        }  
    });  
      
    //禁用jquery easyui中的下拉选（使用select生成的combox）  
    $("#" + formId + " select[class='combobox-f combo-f']").each(function () {  
        if (this.id) {  
        alert(this.id);  
            $("#" + this.id).combobox(attr);  
        }  
    });  
      
    //禁用jquery easyui中的日期组件dataBox  
    $("#" + formId + " input[class='datebox-f combo-f']").each(function () {  
        if (this.id) {  
        alert(this.id)  
            $("#" + this.id).datebox(attr);  
        }  
    });  
    
    
}  