<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>


	<jsp:include page="_menu.jsp" />
	
	<div class="splash">
	   
	  
	    <div class="l-box-lrg pure-u-1 pure-u-md-2-5">
		   	<p><span>Username: </span><span>${user.username}</span></p>
		   	<p><span>First Name: </span><span>${user.firstName}</span></p>
		   	<p><span>Last Name: </span><span>${user.lastName}</span></p>
		   	<p><span>Phone Number: </span><span>${user.phoneNumber}</span></p>
		   	<p><span>Email: </span><span>${user.email}</span></p>
		   	<a href="${entryUrl}/profil/edit" class="pure-button">Edit</a>
	   	</div>
		           
	
	</div>
</t:wrapper>