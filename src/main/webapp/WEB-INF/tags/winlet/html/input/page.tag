<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root version="2.0" xmlns="http://www.w3.org/1999/xhtml"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:win="http://www.aggrepoint.com/winlet">
	<jsp:directive.tag dynamic-attributes="attrMap" language="java" pageEncoding="UTF-8" />
	<jsp:directive.attribute name="currentPage" required="true" />
	<jsp:directive.attribute name="totalPage" required="true" />
	<win:element elm="page">
	
	<c:forEach var="attr" items="${attrMap}">
		<win:attr name="${attr.key}" value="${attr.value}" />
    </c:forEach>
    
	<c:if test="${totalPage gt 0}">
		
		<!-- 分页开始 -->
		<div class="zFooter clearfix"> 
			<div class="zPageNewTwo">
		        <c:if test="${currentPage ne 1}">
		       		<a class="zPageFirstNew" href="javascript:win$.get(null, {page: '1'})">首页</a>
		        	<a class="prev" href="javascript:win$.get(null, {page: '${currentPage - 1}'})">上一页</a>
		        </c:if>
	
		        <c:if test="${totalPage le 5}">
			        <c:forEach var="page" begin="1" end="${totalPage}">
						<c:choose>
							<c:when test="${page eq currentPage}"><a href="javascript:void(0);" class="num current">${page}</a></c:when>
							<c:otherwise> <a href="javascript:win$.get(null, {page: '${page}'})" class="num">${page}</a> </c:otherwise>
						</c:choose>
					</c:forEach>
		        </c:if>
		        <c:if test="${totalPage gt 5}">
		        	<c:if test="${totalPage-currentPage gt 2}">
			        	<c:if test="${currentPage gt 2}">
			        		<c:forEach var="page" begin="${currentPage - 2}" end="${currentPage + 2}">
								<c:choose>
									<c:when test="${page eq currentPage}"><a href="javascript:void(0);" class="num current">${page}</a></c:when>
									<c:otherwise> <a href="javascript:win$.get(null, {page: '${page}'})" class="num">${page}</a> </c:otherwise>
								</c:choose>
							</c:forEach>
			        	</c:if>
			        	<c:if test="${currentPage le 2}">
			        		<c:forEach var="page" begin="1" end="5">
								<c:choose>
									<c:when test="${page eq currentPage}"><a href="javascript:void(0);" class="num current">${page}</a></c:when>
									<c:otherwise> <a href="javascript:win$.get(null, {page: '${page}'})" class="num">${page}</a> </c:otherwise>
								</c:choose>
							</c:forEach>
			        	</c:if>
		        	</c:if>
	
		        	<c:if test="${totalPage-currentPage le 2}">
		        		<c:forEach var="page" begin="${totalPage - 5}" end="${totalPage}">
							<c:choose>
								<c:when test="${page eq currentPage}"><a href="javascript:void(0);" class="num current">${page}</a></c:when>
								<c:otherwise> <a href="javascript:win$.get(null, {page: '${page}'})" class="num">${page}</a> </c:otherwise>
							</c:choose>
						</c:forEach>
		        	</c:if>
		        </c:if>
	
		        <c:if test="${currentPage ne totalPage}">
		        	<a class="next" href="javascript:win$.get(null, {page: '${currentPage + 1}'})">下一页</a>
		        	<a class="zPageLastNew" href="javascript:win$.get(null, {page: '${totalPage}'})">末页</a>
		        </c:if> 
				共 ${totalPage} 页
			  	第  <input name="page" id="pageId" type="text" class="page-ipt" style="width:25px;" value="${currentPage}" />  页 
				<a href="javascript:${win:func('toOtherPage')}(this.parentNode);" class="btn-blue">确定</a> 
			</div>
		</div>
		<!-- 分页结束 -->
	</c:if>
	<script>
		${win:funcdef('toOtherPage')}(fontobj) {
			var pageId = $('#pageId').val();
			javascript:win$.get(null, {page: pageId});
		}
		tbInterlaced();
	</script>
	</win:element>
	<jsp:doBody />
</jsp:root>