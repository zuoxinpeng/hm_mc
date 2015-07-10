<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root version="2.1" xmlns="http://www.w3.org/1999/xhtml"
	xmlns:jsp="http://java.sun.com/JSP/Page">
	<jsp:directive.attribute name="login" required="false" />
	<jsp:directive.attribute name="id" required="false" />
	<jsp:directive.attribute name="shopId" required="false" />
	<span class="img-online">
		<img src="${pageContext.request.contextPath }/resources/images/online.gif" style="display: inline; cursor: pointer;" onclick="getUsersServiceList('${shopId}')"/>
	</span>
	
	
</jsp:root>