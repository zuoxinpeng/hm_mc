<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root version="2.1" xmlns="http://www.w3.org/1999/xhtml"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:win="http://www.aggrepoint.com/winlet">
	<jsp:directive.tag dynamic-attributes="attrMap" language="java" pageEncoding="UTF-8" />
	<jsp:directive.attribute name="value" required="true" />

	<win:element elm="option">
		<win:attr name="value" value="${value}" />
		<jsp:doBody />
	</win:element>
</jsp:root>