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
<link rel="stylesheet"
	href="http://yui.yahooapis.com/pure/0.6.0/grids-responsive-min.css">
<link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
<link rel="stylesheet" href="/resources/css/layouts/marketing.css">
</head>
<body>
	<jsp:include page="_menu.jsp" />
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
				<ui:repeat var="estate" value="#{estates}">
					<tr>
						<td>#{estate.type}</td>
						<td>#{estate.address}</td>
						<td>#{estate.price}</td>
						<td><a class="btn btn-default" href="${entryUrl}/seller/${loggedInUserEmail}/estates/${estate.address}"></a></td>
					</tr>

					<tr>
						<td>2</td>
						<td>Toyota</td>
						<td>Camry</td>
						<td>2012</td>
					</tr>

					<tr>
						<td>3</td>
						<td>Hyundai</td>
						<td>Elantra</td>
						<td>2010</td>
					</tr>
				</ui:repeat>
			</tbody>
			</table>


	</div>
</body>
</html>