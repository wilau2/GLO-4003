<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" />
</head>
<body>
<h1>
	Details for ${entry.name} 
</h1>

<div class="container">
	<div class="row">
		<span class="label label-info col-lg-2">Phone</span> <span class="col-lg-10">${entry.phone}</span>
		<span class="label label-info col-lg-2">Address</span> <span class="col-lg-10">${entry.address}</span>
	</div>
</div>

<a href="<c:url value="/" />" class="btn btn-primary">Back</a>

</body>
</html>