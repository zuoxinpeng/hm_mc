<?xml version="1.0" encoding="UTF-8"?>

<jsp:root version="2.1" xmlns="http://www.w3.org/1999/xhtml"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:win="http://www.aggrepoint.com/winlet">
	<jsp:directive.tag dynamic-attributes="attrMap" language="java" pageEncoding="UTF-8" />
	<jsp:directive.attribute name="list" required="true" type="java.util.Collection"/>
	<jsp:directive.variable name-given="item" scope="NESTED" />
	<jsp:directive.variable name-given="itemIdx" scope="NESTED" />
	<jsp:directive.variable name-given="itemTotalCount" scope="NESTED" />

</jsp:root>