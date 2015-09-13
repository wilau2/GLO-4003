<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" />
</head>
<body>
<h1>
	Edit phone book entry
</h1>
<div class="container">
	<div class="row">
		<jsp:include page="_form.jsp" />
	</div>
</div>

</body>
</html>