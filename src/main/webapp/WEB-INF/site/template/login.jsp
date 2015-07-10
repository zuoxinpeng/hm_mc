<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.aggrepoint.com/winlet/site/local" prefix="site" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"  xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=7">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title><site:name /></title>
		<link type="text/css" rel="stylesheet" href="/messagecenter/resources/css/base/default.css"></link>
		<script src="/messagecenter/resources/js/jquery/jquery-1.9.1.min.js"></script>
	    <script src="/messagecenter/resources/js/winlet/winlet_local.js"></script>
		<script src="/messagecenter/resources/js/jquery/jquery-ui-1.10.3.custom.min.js"></script>
		<link type="text/css" rel="stylesheet" href="/messagecenter/resources/css/base/iValueNew.css"></link>
		<link href="/messagecenter/resources/css/redmond/jquery-ui-1.10.3.custom.min.css" rel="stylesheet"></link>
		<script>
		Fps_Conf_isMustLoginUser=true;
		</script>
		<% 
		HttpSession s = request.getSession();
		String sessionId = s.getAttribute("sessionId")==null?"":s.getAttribute("sessionId").toString(); 
		%>
	</head>
	<body>
		<div class="table_wrap">
			<div class="section">
				<site:area name="main" />
			</div>
		</div>
	</body>
</html>
