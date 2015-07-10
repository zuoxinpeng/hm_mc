<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root version="2.1" xmlns="http://www.w3.org/1999/xhtml"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:win="http://www.aggrepoint.com/winlet">
	<jsp:directive.tag dynamic-attributes="attrMap" language="java" pageEncoding="UTF-8" />
	<jsp:directive.attribute name="name" required="true" />
	<jsp:directive.attribute name="object" required="false" type="java.lang.Object"/>
	<jsp:directive.attribute name="property" required="false" />
	<jsp:directive.attribute name="value" required="false" type="java.lang.Object" />
	<jsp:directive.attribute name="error" required="false" />
	<jsp:directive.attribute name="validate" required="false" />
	<jsp:directive.attribute name="mandatory" required="false" />
	<jsp:directive.attribute name="size" required="false" />
	<jsp:directive.attribute name="maxlength" required="false" />
	<jsp:directive.attribute name="format" required="false" />
	<jsp:directive.attribute name="start" required="false" />
	<jsp:directive.attribute name="end" required="false" />
	<jsp:directive.attribute name="disabled" required="false" type="java.lang.Object" />
	<jsp:directive.attribute name="readOnly" required="false" type="java.lang.Boolean" />

	<win:getinput var="inp" type="date" name="${name}" object="${object}" property="${property}" value="${value}" validate="${validate}" error="${error}" disabled="${disabled}">
		<win:inputattr name="size" value="${size}" />
		<win:inputattr name="maxlength" value="${maxlength}" />
		<win:inputattr name="format" value="${format}" />
		<jsp:doBody />
	</win:getinput>

	<c:set var="calid" value="cal_${win:encodens(name)}" />

	<win:element elm="input">
		<win:attr name="type" value="text" />
		<win:attr name="id" value="${calid}" />
		<win:attr name="name" value="${name}" />
		<win:attr name="value" value="${inp.value}" />
		<win:attr name="size" value="${size}" test="${not empty size}" />
		<win:attr name="maxlength" value="${maxlength}"	test="${not empty maxlength}" />
		<win:attr name="disabled" value="disabled" test="${inp.disabled}" />
		<win:attr name="readOnly" value="readOnly" test="${readOnly}" />

		<c:forEach var="attr" items="${attrMap}">
			<win:attr name="${attr.key}" value="${attr.value}" />
	    </c:forEach>
	</win:element>

	<c:if test="${mandatory eq 'yes'}">
		<span class="win_mandatory" title="必须填写">&amp;nbsp;</span>
	</c:if>

	<span id="validate_res_${name}">
		<c:if test="${inp.hasError}">
			<div class="win_valfailed">${win:htmlencode(inp.error)}</div>
		</c:if>
	</span>

	<script language="javascript">
	$(function() {
		$("#${calid}").datepicker({changeMonth: true, changeYear: true, showAnim: 'slideDown', beforeShow:function(input, inst){
			<c:if test="${not empty start}">
			try {
				var dt = $("#cal_${win:encodens(start)}").datepicker("getDate");
				dt.setDate(dt.getDate() + 1);
				return {minDate: dt};
			} catch (e) {
			}
			</c:if>
			<c:if test="${not empty end}">
			try {
				var dt = $("#cal_${win:encodens(end)}").datepicker("getDate");
				dt.setDate(dt.getDate() - 1);
				return {maxDate: dt};
			} catch (e) {
			}
			</c:if>
		}});
	});
	</script>
</jsp:root>