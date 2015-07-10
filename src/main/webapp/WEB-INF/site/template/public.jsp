<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.aggrepoint.com/winlet/site/local" prefix="site" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"  xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=7">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title><site:name /></title>
		<link type="text/css" rel="stylesheet" href="/messagecenter/resources/css/base/default.css"></link>
	    <link type="text/css" rel="stylesheet" href="/messagecenter/resources/css/base/index.css"></link>
		
		<script src="/messagecenter/resources/js/jquery/jquery-1.9.1.min.js"></script>
	    <script src="/messagecenter/resources/js/winlet/winlet_local.js"></script>
		<script src="/messagecenter/resources/js/jquery/jquery-ui-1.10.3.custom.min.js"></script>
		<link type="text/css" rel="stylesheet" href="/messagecenter/resources/css/base/iValueNew.css"></link>
		<link href="/messagecenter/resources/css/redmond/jquery-ui-1.10.3.custom.min.css" rel="stylesheet"></link>
	</head>
	<body>

<div class="main">
    <div class="header clearfix">
    	<div id="winlet:messagecenter/anonymous/login/headerWin"></div>
    </div>
    <div class="contents" style="position:relative;">
        <table border="0" style="width:100%; height:100%;">
            <tbody>
            <tr>
                <td style="width:160px; position: relative;">
                    <div  style="position: absolute; top:0;left:0;">
                        <p class="nav_title_1">消息中心</p>
                    	<ul class="nav_left">
                            <li>
                                <p class="nav_title_2">短信管理<span class="arrow_up" ></span> </p>
                                <ul>
                                    <li><a href="/messagecenter/resources/mc/basMcMessage/basMcMessage.html" target="ct_frame">待发送短信</a> </li>
                                    <li><a href="/messagecenter/resources/mc/basMcMessage/basMcMessageSuccess.html" target="ct_frame">已发送短信</a> </li>
                                    <li><a href="/messagecenter/resources/mc/basMcMessage/basMcMessageFail.html" target="ct_frame">发送失败短信</a> </li>
                                </ul>
                            </li>
                             <li>
                                <p class="nav_title_2">邮件管理<span class="arrow_up" ></span> </p>
                                <ul>
                                	<li><a href="/messagecenter/resources/mc/basMcEmail/basMcMessage.html" target="ct_frame">待发送邮件</a> </li>
                                    <li><a href="/messagecenter/resources/mc/basMcEmail/basMcMessageSuccess.html" target="ct_frame">已发送邮件</a> </li>
                                    <li><a href="/messagecenter/resources/mc/basMcEmail/basMcMessageFail.html" target="ct_frame">发送失败邮件</a> </li>
                                </ul>
                            </li>
                            <li>
                                    <p class="nav_title_2">IMO管理<span class="arrow_up" ></span> </p>
                                    <ul>
                                    	<li><a href="/messagecenter/resources/mc/basMcImo/basMcImo.html" target="ct_frame">待发送IMO</a> </li>
                                        <li><a href="/messagecenter/resources/mc/basMcImo/basMcImoSuccess.html" target="ct_frame">已发送IMO</a> </li>
                                        <li><a href="/messagecenter/resources/mc/basMcImo/basMcImoFail.html" target="ct_frame">发送失败IMO</a> </li>
                                    </ul>
                            </li>
                            <li>
                                <p class="nav_title_2">模板管理<span class="arrow_up" ></span> </p>
                                <ul>
                                    <li><a href="/messagecenter/resources/mc/template/mcTemplate.html" target="ct_frame">消息模板管理</a> </li>
                                    <li><a href="/messagecenter/resources/mc/template/templateAllot.html" target="ct_frame">消息模板配置</a> </li>
                                </ul>
                            </li>
                            <li>
                                <p class="nav_title_2">用户管理<span class="arrow_up" ></span> </p>
                                <ul>
                                    <li><a href="/messagecenter/resources/mc/user/basMcPiuUser.html" target="ct_frame">用户管理</a> </li>
                                </ul>
                            </li>
                             <li>
                                <p class="nav_title_2">消息统计<span class="arrow_up" ></span> </p>
                                <ul>
                                    <li><a href="/messagecenter/resources/mc/messageReport/basMessageReport.html" target="ct_frame">消息统计</a> </li>
                                </ul>
                            </li>
                             <li>
                                <p class="nav_title_2">日志管理<span class="arrow_up" ></span> </p>
                                <ul>
                                    <li><a href="/messagecenter/resources/mc/log/basMcLog.html" target="ct_frame">日志查询</a> </li>
                                </ul>
                            </li>
                             <li>
                                <p class="nav_title_2">任务管理<span class="arrow_up" ></span> </p>
                                <ul>
                                    <li><a href="/messagecenter/resources/mc/job/mcBasScheduleJob.html" target="ct_frame">定时任务</a> </li>
                                </ul>
                            </li>
                    	</ul>
                    </div>
                </td>
                <td>
                    <div class="frame_right" style="width:100%; overflow: hidden;">
                        <iframe id="ct_frame" name="ct_frame" style="width:100%; height:600px;"   frameborder="0"></iframe>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<style type="text/css">
    .nav_left li ul{display: none;}
</style>
<script type="text/javascript">
    $(function(){
        $(".nav_left li a").click(function(){
            $("head title").html($(this).text());
        });
        $(".nav_left li p").click(function(){
            var tar=$(this),cd=tar.children("span");
            tar.siblings("ul").toggle();
            cd.hasClass("arrow_down")?cd.removeClass("arrow_down"):cd.addClass("arrow_down");
        })
    })
</script>
</body>
</html>
