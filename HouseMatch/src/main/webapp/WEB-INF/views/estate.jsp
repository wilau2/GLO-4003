<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="A layout example that shows off a responsive product landing page.">

<title>HouseMatch - Estate Description</title>

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
				<legend>${estate.type} at ${estate.addressToString()}</legend>
				<div class="pure-control-group">
					<form:label path="type">Type </form:label>
					<div class="pure-u-13-24">
						<form:input disabled="true" id="type" type="text" path="type" />
					</div>
				</div>
				<div class="pure-control-group">
					<form:label path="price">Price $</form:label>
					<div class="pure-u-13-24">
						<form:input disabled="true" id="price" type="number" path="price"
							value="1000" min="0" step="100" />
					</div>
				</div>
			</fieldset>
		</form:form>
	</div>

</body>
</html>