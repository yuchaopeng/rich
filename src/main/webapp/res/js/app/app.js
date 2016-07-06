$(function(){
	$(".footer_tab").click(function(){
		var tabUrl = $(this).attr("tabUrl");
		if(tabUrl != null && tabUrl != ''){
			window.location.href=tabUrl;
		}
	});
	
	var currentTab = $("#currentTab").val();
	if(currentTab != null && currentTab != ""){
		var png = "/res/img/icon/"+currentTab+".png";
		$("#"+currentTab).find("img").attr("src","/res/img/icon/"+currentTab+"_click.png");
		$("#"+currentTab).find("p").css("color","#FF7F24");
	}
});