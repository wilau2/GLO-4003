<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="header">
	 <c:if test="${loggedInUsername == null}">
	    <div class="home-menu pure-menu pure-menu-horizontal pure-menu-fixed">
	        <a class="pure-menu-heading" href="${entryUrl}">House Match</a>
	        <ul class="pure-menu-list">
	            <li class="pure-menu-item"><a href="${entryUrl}/login" class="pure-menu-link">Log In</a></li>
	            <li class="pure-menu-item"><a href="${entryUrl}/signup"class="pure-menu-link">Sign Up</a></li>
	            <li class="pure-menu-item"><a href="${entryUrl}/confirmation" class="pure-menu-link">Confirmation</a></li>
	        </ul>
	    </div>
    </c:if>
     <c:if test="${loggedInUsername != null}">
	    <div class="home-menu pure-menu pure-menu-horizontal pure-menu-fixed">
	        <a class="pure-menu-heading" href="${entryUrl}">House Match</a>
	        <ul class="pure-menu-list">
	            <c:if test="${loggedInUserRole == 'admin'}">
	              <li class="pure-menu-item"><a href="${entryUrl}/" class="pure-menu-link">Admin</a></li>
	            </c:if>

	            <c:if test="${loggedInUserRole == 'buyer'}">
	              <li class="pure-menu-item"><a href="${entryUrl}/" class="pure-menu-link">Buyer</a></li>
	            </c:if>
	            <c:if test="${loggedInUserRole == 'seller'}">
	              <li class="pure-menu-item"><a href="${entryUrl}/seller/${loggedInUsername}/estates" class="pure-menu-link">Seller</a></li>
	            </c:if>
	            <li class="pure-menu-item"><a href="${entryUrl}/profil" class="pure-menu-link">${loggedInUsername}</a></li>

	            <li class="pure-menu-item"><a href="${entryUrl}/logout" class="pure-menu-link">Log out</a></li>
	            
	        </ul>
	    </div>
    </c:if>
    
</div>