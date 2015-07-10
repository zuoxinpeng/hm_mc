<?xml version="1.0" encoding="UTF-8"?>

<jsp:root version="2.1" xmlns="http://www.w3.org/1999/xhtml"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:win="http://www.aggrepoint.com/winlet">
	<jsp:directive.tag language="java" pageEncoding="UTF-8" />
	<jsp:directive.attribute name="list"
		type="com.icebean.core.adb.ADBList" required="true" />
	<jsp:directive.attribute name="size" required="true" />
	<jsp:directive.attribute name="func" required="true" />

	<win:tbody>
		<win:navpage pid="pid" list="${list}" size="${size}">
			<win:prev><button type="button" onclick="${func}(1)" class="win_btn3 win_btnsilver__normal">|&lt;</button><button type="button" onclick="${func}(${pid})" class="win_btn3 win_btnsilver__normal">&lt;&lt;</button></win:prev>
			<win:curr>&amp;nbsp;${pid}&amp;nbsp;</win:curr>
			<win:page><button type="button" onclick="${func}(${pid})" class="win_btn3 win_btnsilver__normal">${pid}</button></win:page>
			<win:gap></win:gap>
			<win:next><button type="button" onclick="${func}(${pid})" class="win_btn3 win_btnsilver__normal">&gt;&gt;</button><button type="button" onclick="${func}(${list.pageCount})" class="win_btn3 win_btnsilver__normal">&gt;|</button></win:next>
		</win:navpage>
	</win:tbody>
</jsp:root>