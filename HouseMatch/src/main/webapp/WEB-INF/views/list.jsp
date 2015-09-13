<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" />
	<meta http-equiv="cache-control" content="no-cache">
</head>
<body>
<h1>
	Phone book entries list  
</h1>

<div class="container">
	<div class="row">
		<table class="table col-lg-8">
			<thead>
				<tr>
					<th>Name</th>
					<th>Phone</th>
					<th>Address</th>
					<th colspan="3"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="entry" items="${entries}">
					<c:url var="entryUrl" value="/${entry.id}" />
					<tr>
						<td>${entry.name}</td>
						<td>${entry.phone}</td>
						<td>${entry.address}</td>
						<td>
							<a href="${entryUrl}">Details</a>
						</td>
						<td>
							<a href="${entryUrl}/edit">Edit</a>
						</td>
						<td>
							<a href="${entryUrl}/delete">Delete</a>
						</td>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="row">
		<a href="<c:url value="add" />" class="col-lg-offset=2 btn btn-primary">Add an entry</a>
	</div>
</div>


</body>
</html>
