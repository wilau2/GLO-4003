<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:if test="${loggedInUserRole == 'admin'}">
	<p>Hi ${loggedInUsername}, you are logged in as an admin.</p>
	<p>Remember, with great powers comes great responsibilities.</p>
</c:if>
<c:if test="${loggedInUserRole == 'buyer'}">
	<p>Hi ${loggedInUsername}, you are logged in as a buyer.</p>
</c:if>
<c:if test="${loggedInUserRole == 'seller'}">
	<p>Hi ${loggedInUsername}, you are logged in as a seller.</p>
</c:if>
