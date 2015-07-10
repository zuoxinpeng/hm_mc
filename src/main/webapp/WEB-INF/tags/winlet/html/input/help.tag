<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root version="2.1" xmlns="http://www.w3.org/1999/xhtml"
	xmlns:jsp="http://java.sun.com/JSP/Page">
	<jsp:directive.attribute name="msgCode" required="false" />
	<jsp:directive.attribute name="id" required="false" />
	<span class="help-guidance-floatframe"> 
		<img class="help-guidance-floatimg" 
		src="${pageContext.request.contextPath }/resources/images/tips-ico.png" 
		onmouseover="getMsg('${pageContext.request.contextPath }',${msgCode},${id})"
		 />
		<div id="hgfr${id}" class="help-guidance-floatarrows" />
		<div id="hgfc${id}" class="help-guidance-floatcontent" onmouseout="over(this)">
			<ul class="help-guidance-floatcontentul"></ul>
			<ul>
				<li id="msgId${id}" />
			</ul>
			<a href="javascript:void()" class="helpclose"></a>
		</div>
	</span>
	
</jsp:root>