<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="header">
	 <c:if test="${loggedInUserEmail == null}">
	    <div class="home-menu pure-menu pure-menu-horizontal pure-menu-fixed">
	        <a class="pure-menu-heading" href="${entryUrl}">House Match</a>
	        <ul class="pure-menu-list">
	            <li class="pure-menu-item"><a href="${entryUrl}/login" class="pure-menu-link">Log In</a></li>
	            <li class="pure-menu-item"><a href="${entryUrl}/signup"class="pure-menu-link">Sign Up</a></li>
	        </ul>
	    </div>
    </c:if>
     <c:if test="${loggedInUserEmail != null}">
	    <div class="home-menu pure-menu pure-menu-horizontal pure-menu-fixed">
	        <a class="pure-menu-heading" href="${entryUrl}">House Match</a>
	        <ul class="pure-menu-list">
	            <c:if test="${loggedInUserRole == 'admin'}">
	              <li class="pure-menu-item"><a href="${entryUrl}/" class="pure-menu-link">ADMIN</a></li>
	            </c:if>
<%-- 	            <c:if test="${loggedInUserRole == 'vendor'}"> --%>
	              <li class="pure-menu-item"><a href="${entryUrl}/sell" class="pure-menu-link">Sell an estate</a></li>
<%-- 	            </c:if> --%>
	            <li class="pure-menu-item"><a href="${entryUrl}/" class="pure-menu-link">${loggedInUserEmail}</a></li>
	            <li class="pure-menu-item"><a href="${entryUrl}/logout" class="pure-menu-link">Log out</a></li>
	        </ul>
	    </div>
    </c:if>
    
</div>