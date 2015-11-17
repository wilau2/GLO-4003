<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
	<jsp:include page="_menu.jsp" />
	<jsp:include page="_admin_side_menu.jsp" />
	<div class="splash">
		<h2>Here's the number of active seller in Housematch</h2>
		<p>Number of Seller with at least one estate for sale : ${numberOfActiveSeller}</p>
	</div>
</t:wrapper>