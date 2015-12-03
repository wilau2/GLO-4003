<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
	<jsp:include page="_menu.jsp" />
	<div class="splash">
		<h2>Here's some truly awesome statistics about HouseMatch</h2>
		<p>Number of Buyer active in the last 6 months : ${numberOfActiveBuyers}</p>
		<p>Number of Seller with at least one estate for sale : ${numberOfActiveSellers}</p>
		<p>Number of Estates that have been sold in the past year : ${numberOfEstatesSoldLastYear}</p>
		<h3>You should really sign up!</h3>
	</div>
</t:wrapper>