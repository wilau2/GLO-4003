<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
	<jsp:include page="_menu.jsp" />
	<jsp:include page="_admin_side_menu.jsp" />
	<div class="splash">
	<div class="pure-g">
    
	<c:if test="${pictures.isEmpty()}">
	<p>There is no pictures to activate</p>
	</c:if>
	<c:if test="${!pictures.isEmpty()}">
	
		<form:form method="post" modelAttribute="album" class="pure-form pure-form-stacked">
	
		<fieldset>
			<c:forEach items="${pictures}" var="picture" varStatus="counter">
				<div class="image-box pure-u-1-3">
					<p>"${picture.getAddress()}"</p>
					<p>"${picture.getName()}"</p>
					
					<img class="inactive-pictures photo-image" src="${entryUrl}/admin/pictures/${picture.getUid()}" >
					<form:checkbox path="uids" value="${picture.getUid()}"/>
					
				</div>
				   
			</c:forEach>
	        <div>
		        <span class="image-button"><button type="submit" name = "approve" class="pure-button">Approve</button></span>
		        <span class="image-button"><button type="submit" name = "delete" class="pure-button">DELETE</button></span>
	        </div>
	     </fieldset>
	 </form:form>
	</c:if>
	</div>
	</div>
</t:wrapper>