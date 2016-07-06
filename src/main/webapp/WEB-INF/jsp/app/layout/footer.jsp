<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<input type="hidden" id="currentTab" value="${param.currentTab }"/>
<footer>
	<div class="footer_tab" id="tab_planList" tabUrl="/app/planList">
    	<img src="/res/img/icon/tab_planList.png">
        <p>计划</p>
    </div>
	<div class="footer_tab" id="tab_bettingList" tabUrl="/app/bettingList">
    	<img src="/res/img/icon/tab_bettingList.png">
        <p>投注</p>
    </div>
	<div class="footer_tab" id="tab_userInfo" tabUrl="/app/userInfo">
    	<img src="/res/img/icon/tab_userInfo.png">
        <p>个人</p>
    </div>
	<div class="footer_tab" id="tab_system" tabUrl="/app/systemMessageList">
    	<img src="/res/img/icon/tab_system.png">
        <p>系统</p>
    </div>    
</footer>