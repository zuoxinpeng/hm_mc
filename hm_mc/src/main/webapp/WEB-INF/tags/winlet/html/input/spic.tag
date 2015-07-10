<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root version="2.1" xmlns="http://www.w3.org/1999/xhtml"
	xmlns:jsp="http://java.sun.com/JSP/Page">
	<jsp:directive.attribute name="login" required="false" />
	<jsp:directive.attribute name="id" required="false" />
	<span class="img-online">
		<label name="FpsSmallIconUser" id="FpsSmallIconUser" 
			loginId="${login}" towindowclientparam="&amp;productId=${id}"
			onlineimg="${pageContext.request.contextPath }/resources/images/online.gif"
			offlineimg="${pageContext.request.contextPath }/resources/images/online-gray.gif">
		</label>
	</span>
	
	
</jsp:root>