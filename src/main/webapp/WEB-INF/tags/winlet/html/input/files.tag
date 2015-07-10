<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root version="2.1" xmlns="http://www.w3.org/1999/xhtml"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:win="http://www.aggrepoint.com/winlet">
	<jsp:directive.tag dynamic-attributes="attrMap" language="java" pageEncoding="UTF-8" />
	<jsp:directive.attribute name="id" required="true" />
	<jsp:directive.attribute name="name" required="true" />
	<jsp:directive.attribute name="mandatory" required="false" />

	<div style="margin: -30px 0 0 0;">
			<win:element elm="input">
				<win:attr name="id" value="${id}" />
				<win:attr name="type" value="file" />
				<win:attr name="name" value="${name}" />
				<win:attr name="multiple" value="multiple" />
		
				<c:forEach var="attr" items="${attrMap}">
					<win:attr name="${attr.key}" value="${attr.value}" />
			    </c:forEach>
			    <win:attr name="style" value="z-index: 10; filter:alpha(opacity=0); opacity: 0; margin-left: 5px; width: 70px; cursor: pointer; height: 30px;"></win:attr>
			</win:element>
</div>

	

</jsp:root>