<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>

	<jsp:include page="_menu.jsp" />
	
	<div class="splash">
	   		
		<form:form method="post" modelAttribute="user" class="pure-form pure-form-stacked">
              <fieldset>
					<form:input id="firstName" type="text" placeholder="${user.firstName}" path="firstName"/>
					<form:input id="lastName" type="text" placeholder="${user.lastName}" path="lastName"/>
					<form:input id="phoneNumber" type="text" placeholder="${user.phoneNumber}r" path="phoneNumber"/>
					<form:input id="email" type="text" placeholder="${user.email}" path="email"/>
                  	<button type="submit" class="pure-button">Save</button>
              </fieldset>
         </form:form>         
	
	</div>
	
</t:wrapper>
