<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Estates from seller">

<title>Landing Page &ndash; Layout Examples &ndash; Pure</title>

<link rel="stylesheet"
	href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
<link rel="stylesheet" href="/resources/css/layouts/side-menu.css">
<link rel="stylesheet"
	href="http://yui.yahooapis.com/pure/0.6.0/grids-responsive-min.css">
<link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
<link rel="stylesheet" href="/resources/css/layouts/marketing.css">
</head>
<body>
	<jsp:include page="_menu.jsp" />
	<div id="menu" class="side-menu">
		<div class="pure-menu">
			<a class="pure-menu-heading" href="">Seller</a>
			<ul class="pure-menu-list">
				<li class="pure-menu-item"><a class="pure-menu-link"
					href="${entryUrl}/seller/${loggedInUserEmail}/estates/add">Sell
						an estate</a></li>
			</ul>
		</div>
	</div>
	<div class="splash">
		<table class="pure-table">
			<thead>
				<tr>
					<th>Type</th>
					<th>Address</th>
					<th>Price</th>
					<th>Select</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="estate" items="${estates}">
					<tr>
						<td>${estate.type}</td>
						<td>${estate.addressToString()}</td>
						<td>${estate.price}</td>
						<td><a class="pure-button"
							href="${entryUrl}/seller/${loggedInUserEmail}/estates/${estate.addressToUrl()}">Select</a></td>
					</tr>

				</c:forEach>
			</tbody>
		</table>


	</div>
</body>
</html>