<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<div class="header">
	 <c:if test="${loggedInUser == null}">
	    <div class="home-menu pure-menu pure-menu-horizontal pure-menu-fixed">
	        <a class="pure-menu-heading" href="${entryUrl}">House Match</a>
	        <ul class="pure-menu-list">
	            <li class="pure-menu-item"><a href="${entryUrl}/login" class="pure-menu-link">Log In</a></li>
	            <li class="pure-menu-item"><a href="${entryUrl}/signup"class="pure-menu-link">Sign Up</a></li>
	        </ul>
	    </div>
    </c:if>
     <c:if test="${loggedInUser != null}">
	    <div class="home-menu pure-menu pure-menu-horizontal pure-menu-fixed">
	        <a class="pure-menu-heading" href="${entryUrl}">House Match</a>
	        <ul class="pure-menu-list">
	            <li class="pure-menu-item"><a href="${entryUrl}/login" class="pure-menu-link">${loggedInUser}</a></li>
	            <li class="pure-menu-item"><a href="${entryUrl}/logout" class="pure-menu-link">Log out</a></li>
	        </ul>
	    </div>
    </c:if>
</div>