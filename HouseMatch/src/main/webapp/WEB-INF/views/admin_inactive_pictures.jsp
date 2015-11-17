<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
	<jsp:include page="_menu.jsp" />
	
	<c:forEach items="${pictures}" var="picture" varStatus="counter">
		   
		<img class="photo-image" src = "${entryUrl}/admin/pictures/${picture.getUid()}" >
		       
	</c:forEach>
	
</t:wrapper>