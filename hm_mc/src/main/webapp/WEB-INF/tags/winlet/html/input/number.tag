<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root version="2.1" xmlns="http://www.w3.org/1999/xhtml"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:win="http://www.aggrepoint.com/winlet">
	<jsp:directive.tag dynamic-attributes="attrMap" language="java" pageEncoding="UTF-8" />
	<jsp:directive.attribute name="name" required="true" />
	<jsp:directive.attribute name="mandatory" required="false" />

	<win:element elm="input">
		<win:attr name="type" value="text" />
		<win:attr name="name" value="${name}" />

		<c:forEach var="attr" items="${attrMap}">
			<win:attr name="${attr.key}" value="${attr.value}" />
	    </c:forEach>
	</win:element>

	<c:if test="${mandatory eq 'yes'}">
		<span class="win_mandatory" title="必须填写">&amp;nbsp;</span>
	</c:if>

	<span id="validate_res_${name}"></span>
</jsp:root>