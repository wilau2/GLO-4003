<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>

	<jsp:include page="_menu.jsp" />
	<jsp:include page="_buyer_side_menu.jsp" />
	<div class="splash">
		<h2>Estates that are for sale!</h2>
		Price filter:
		<c:set var="minPrice" value="${param['minPrice']}"></c:set>
		<c:set var="maxPrice" value="${param['maxPrice']}"></c:set>
		<form name="form1" method="GET">
			<input id="minPrice" name="minPrice" value="${minPrice}"
				type="number" min="100" max="1000000000" step="1000" value="0"/> <input
				id="maxPrice" name="maxPrice" value="${maxPrice}" type="number"
				min="101" max="1000000000" step="1000" value="1000000000"/> <input type="button"
				class="pure-button" value="Filter" onclick="filter()"> <a
				class="pure-button"
				href="${entryUrl}/buyer/${loggedInUsername}/estates">Reset</a>
		</form>

		<script>
     
	            function filter()
	            {   
	            	
	                form1.submit()
	                var minPrice = document.getElementById('minPrice').value;
	                var maxPrice = document.getElementById('maxPrice').value;
	                var pageURL = "${entryUrl}/buyer/${loggedInUsername}/estates?filtered&type=PRICE&minPrice=" + minPrice + "&maxPrice=" + maxPrice;
	                	                      	                
	                window.location.href = pageURL;
	            }    
         
        	</script>

		<c:if test="${!estates.isEmpty()}">
			<table class="pure-table">
				<thead>
					<tr>

						<th>Type</th>
						<th>Address</th>
						<th>Date <a
							href="${entryUrl}/buyer/${loggedInUsername}/estates?sort=dateRegistered">Asc</a>/<a
							href="${entryUrl}/buyer/${loggedInUsername}/estates?sort=dateDescendant">Des</a>
						</th>
						<th>Date Modified <a
							href="${entryUrl}/buyer/${loggedInUsername}/estates?sort=dateModified">Asc</a>/<a
							href="${entryUrl}/buyer/${loggedInUsername}/estates?sort=dateModifiedDescendant">Des</a>
						</th>
						<th>Price <a
							href="${entryUrl}/buyer/${loggedInUsername}/estates?sort=price">Asc</a>/<a
							href="${entryUrl}/buyer/${loggedInUsername}/estates?sort=priceDescendant">Des</a>
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
