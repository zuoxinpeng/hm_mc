<jsp:root version="2.1" xmlns="http://www.w3.org/1999/xhtml"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core">
	<jsp:directive.tag language="java" pageEncoding="UTF-8" />
	<jsp:directive.attribute name="defmsg" required="true" />
	<jsp:directive.attribute name="errmsg" required="true" />
	<jsp:directive.attribute name="errors" required="true" />
	<jsp:directive.attribute name="colspan" required="true" />

	<c:choose>
		<c:when test="${errmsg == null}">
			<tr>
			<td colspan="${colspan}" nowrap="nowrap">${defmsg}</td>
			</tr>
		</c:when>
		<c:otherwise>
			<tr>
			<td colspan="${colspan}" nowrap="nowrap">
			<font color="red">${errmsg}</font>
			</td>
			</tr>
		</c:otherwise>
	</c:choose>
	<c:if test="${errors != null}">
		<c:forEach var="err" items="${errors}">
			<tr>
			<td colspan="${colspan}" nowrap="nowrap">
			<font color="red">* ${err}</font>
			</td>
			</tr>
		</c:forEach>
	</c:if>
</jsp:root>