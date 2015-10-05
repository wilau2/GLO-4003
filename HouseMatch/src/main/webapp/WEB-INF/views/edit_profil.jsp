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
	   	</div>
		<a href="${entryUrl}/profil/edit" class="pure-button">Edit</a>  
		
		<form:form method="post" modelAttribute="user" class="pure-form pure-form-stacked">
              <fieldset>
					<form:input id="firstName" type="text" placeholder="${user.firstName}" path="firstName"/>
					<form:input id="lastName" type="text" placeholder="${user.lastName}" path="lastName"/>
					<form:input id="phoneNumber" type="text" placeholder="Phone number" path="phoneNumber"/>
					<form:input id="email" type="text" placeholder="Email" path="email"/>
                  	<button type="submit" class="pure-button">Save</button>
              </fieldset>
         </form:form>         
	
	</div>
</t:wrapper>