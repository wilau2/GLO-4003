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
<link rel="stylesheet" href="/resources/css/layouts/side-menu.css">
<link rel="stylesheet"
	href="http://yui.yahooapis.com/pure/0.6.0/grids-responsive-min.css">
<link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
<link rel="stylesheet" href="/resources/css/layouts/marketing.css">
</head>

</head>

<body>
	<jsp:include page="_menu.jsp" />
	<jsp:include page="_seller_side_menu.jsp" />
	<div class="splash">
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
				<h3>Address</h3>
				<div class="pure-control-group">
					<div class="pure-control-group">

						<form:label path="civicNumber">Civic No </form:label>
						<div class="pure-u-13-24">
							<form:input id="civicNumber" type="number" path="civicNumber" />
						</div>
					</div>
					<div class="pure-control-group">
						<form:label path="street">Street </form:label>
						<div class="pure-u-13-24">
							<form:input id="street" type="text" path="street" />
						</div>
					</div>
					<div class="pure-control-group">
						<form:label path="postalCode">Postal Code </form:label>
						<div class="pure-u-13-24">
							<form:input id="postalCode" type="text" path="postalCode" />
						</div>
					</div>
					<div class="pure-control-group">
						<form:label path="state">State </form:label>
						<div class="pure-u-13-24">
							<form:input id="state" type="text" path="state" />
						</div>
					</div>
					<div class="pure-control-group">
						<form:label path="country">Country </form:label>
						<div class="pure-u-13-24">
							<form:input id="country" type="text" path="country" />
						</div>
					</div>
				</div>
				<div class="pure-control-group">
					<form:label path="price">Price </form:label>
					<div class="pure-u-13-24">
						<form:input id="price" type="number" path="price" value="1000"
							min="0" step="100" />
					</div>
				</div>
				<button type="submit" class="pure-button pure-button-primary">Add
					to sell</button>
				<button type="reset" class="pure-button pure-button-cancel">Reset</button>
			</fieldset>
		</form:form>
	</div>
</body>
</html>