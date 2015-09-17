<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:if test="${loggedInUserRole == 'admin'}">
	<p>Hi ${loggedInUserEmail}, you are logged in as an admin.</p>
	<p>Remember, with great powers comes great responsibilities.</p>
</c:if>
<c:if test="${loggedInUserRole == 'user'}">
	<p>Hi ${loggedInUserEmail}, you are logged in as a user.</p>
</c:if>
