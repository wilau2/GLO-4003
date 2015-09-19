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
<meta name="description"
	content="A layout example that shows off a responsive product landing page.">

<title>Landing Page &ndash; Layout Examples &ndash; Pure</title>

<link rel="stylesheet"
	href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
<link rel="stylesheet"
	href="http://yui.yahooapis.com/pure/0.6.0/grids-responsive-min.css">
<link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
<link rel="stylesheet" href="/resources/css/layouts/marketing.css">
</head>

</head>

<body>
	<jsp:include page="_menu.jsp" />
	<div class="splash">
		<div modelAttribute="exception"><p>{$exception.message}</p></div>
		<form:form method="post" modelAttribute="estate"
			class="pure-form pure-form-aligned">
			<fieldset>
				<legend>Add an estate to sell</legend>
				<div class="pure-control-group">
					<form:label path="type">Type </form:label>
					<form:select id="type" path="type">
						<option value="NONE">--- Select ---</option>
						<option value="CONDO">Condo</option>
						<option value="APPARTMENT">Appartment</option>
						<option value="SINGLE_FAMILY">Single family</option>
						<option value="MULTIPLEX">Multiplex</option>
						<option value="LOT">Lot</option>
						<option value="COTTAGE">Cottage</option>
						<option value="COMMERCIAL">Commercial</option>
					</form:select>
				</div>
				<div class="pure-control-group">
					<form:label path="address">Address </form:label>
					<div class="pure-u-13-24">
						<form:input id="address" type="text" path="address" />
					</div>
				</div>
				<div class="pure-control-group">
					<form:label path="price">Price </form:label>
					<div class="pure-u-13-24">
						<form:input id="price" type="number" path="price" value="1000" min="0" step="100"/>
					</div>
				</div>
				<c:choose>
					<c:when test ="%{estate.address == ''}">
						<button type="submit" class="pure-button pure-button-primary" disabled="disabled">Add to sell</button>
					</c:when>
					<c:otherwise>
						<button type="submit" class="pure-button pure-button-primary">Add to sell</button>
					</c:otherwise>
				</c:choose>
				<button type="reset" class="pure-button pure-button-cancel">Reset</button>
			</fieldset>
		</form:form>
	</div>
</body>
</html>