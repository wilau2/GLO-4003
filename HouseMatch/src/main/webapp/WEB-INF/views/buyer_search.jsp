<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>

	<jsp:include page="_menu.jsp" />
	<jsp:include page="_buyer_side_menu.jsp" />
	<div class="splash">
		<h2>Estates that are for sale!</h2>
		<c:if test="${!estates.isEmpty()}">
			<table class="pure-table">
				<thead>
					<tr>
						<th>Type</th>
						<th>Address</th>
						<th>Date 
						<a href="${entryUrl}/buyer/${loggedInUsername}/estates?dateAscendant">Asc</a>/<a href="${entryUrl}/buyer/${loggedInUsername}/estates?dateDescendant">Des</a>
						</th>
						<th>Date Modified 
						<a href="${entryUrl}/buyer/${loggedInUsername}/estates?dateModifiedAscendant">Asc</a>/<a href="${entryUrl}/buyer/${loggedInUsername}/estates?dateModifiedDescendant">Des</a>
						</th>
						<th>Price 
						<a href="${entryUrl}/buyer/${loggedInUsername}/estates?priceAscendant">Asc</a>/<a href="${entryUrl}/buyer/${loggedInUsername}/estates?priceDescendant">Des</a>
						</th>
						<th>Select</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="estate" items="${estates}">
						<tr>
							<td>${estate.type}</td>
							<td>${estate.address.addressToString()}</td>
							<td>${estate.dateRegistered}</td>
							<td>${estate.dateModified}</td>
							<td>${estate.price}</td>
							<td><a class="pure-button"
								href="${entryUrl}/buyer/${loggedInUsername}/estates/${estate.address.addressToUrl()}">Select</a></td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
		</c:if>
		
		<c:if test="${estates.isEmpty()}">
			<h2>It seems that you don't have any Estates for sale!</h2>
			<h3>
				Would you like to add one <a class="pure-button"
					href="${entryUrl}/seller/${loggedInUsername}/estates/add">Sell
					an Estate</a>
			</h3>
		</c:if>
		
	</div>
</t:wrapper>