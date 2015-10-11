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
	
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

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
	<jsp:include page="_buyer_side_menu.jsp" />
	<div class="splash">
	
		<form:form method="post" modelAttribute="estate" class="pure-form pure-form-aligned" id="eForm">
			<fieldset>
				<legend>${estate.type} at ${estate.addressToString()}</legend>
				<div class="pure-control-group">
					<form:label path="type">Type </form:label>
					<div class="pure-u-13-24">
						<form:input id="type" type="text" path="type" />
					</div>
				</div>
				<div class="pure-control-group">
					<form:label path="price">Price $</form:label>
					<div class="pure-u-13-24">
						<form:input id="price" type="number" path="price" />
					</div>
				</div>
			</fieldset>
		</form:form>
		
		<form:form method="post" modelAttribute="description" class="pure-form pure-form-aligned" id="dForm">
			<fieldset>
			<legend>Description</legend>
				<div class="pure-control-group">
					<form:label path="numberOfBedRooms">Number of bedrooms</form:label>
					<div class="pure-u-13-24">
						<form:input id="numberOfBedRooms" type="text" path="numberOfBedRooms" />
					</div>
				</div>
				<div class="pure-control-group">
					<form:label path="numberOfBathrooms">Number of bathrooms</form:label>
					<div class="pure-u-13-24">
						<form:input id="numberOfBathrooms" type="text" path="numberOfBathrooms" />
					</div>
				</div>
				<div class="pure-control-group">
					<form:label path="numberOfRooms">Number of rooms</form:label>
					<div class="pure-u-13-24">
						<form:input id="numberOfRooms" type="text" path="numberOfRooms" />
					</div>
				</div>
				<div class="pure-control-group">
					<form:label path="numberOfLevel">Number of level</form:label>
					<div class="pure-u-13-24">
						<form:input id="numberOfLevel" type="text" path="numberOfLevel" />
					</div>
				</div>
				<div class="pure-control-group">
					<form:label path="yearsOfConstruction">Year of construction</form:label>
					<div class="pure-u-13-24">
						<form:input id="yearsOfConstruction" type="text" path="yearsOfConstruction" />
					</div>
				</div>
				<div class="pure-control-group">
					<form:label path="dimensionsBuilding">Dimensions of building</form:label>
					<div class="pure-u-13-24">
						<form:input id="dimensionsBuilding" type="text" path="dimensionsBuilding" />
					</div>
				</div>
				<div class="pure-control-group">
					<form:label path="livingSpaceAreaSquareMeter">Living space in square meters</form:label>
					<div class="pure-u-13-24">
						<form:input id="livingSpaceAreaSquareMeter" type="text" path="livingSpaceAreaSquareMeter" />
					</div>
				</div>
				<div class="pure-control-group">
					<form:label path="municipalValuation">Municipal Valuation</form:label>
					<div class="pure-u-13-24">
						<form:input id="municipalValuation" type="text" path="municipalValuation" />
					</div>
				</div>
				<div class="pure-control-group">
					<form:label path="backyardFaces">Backyard Faces</form:label>
					<div class="pure-u-13-24">
						<form:input id="backyardFaces" type="text" path="backyardFaces" />
					</div>
				</div>
				</fieldset>	
			</form:form>	
		</div>		
</body>
</html>

<script>
 $("#eForm input").prop('disabled', true);
 $("#dForm input").prop('disabled', true);
</script>

