<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>

        <jsp:include page="_menu.jsp" />
        
        <div class="splash">
     	
			<c:if test="${loggedInUsername == null}">
				<div class="l-box-lrg pure-u-1 pure-u-md-2-5">
			 		<form:form method="post" modelAttribute="user" class="pure-form pure-form-stacked">
			     		<fieldset>
					         <form:input id="name" type="text" placeholder="Username" path="username"/>
					         <form:input id="password" type="password" placeholder="Password" path="password"/>
					         <button type="submit" class="pure-button">Log In</button>
					     </fieldset>
					 </form:form>
				 </div>
			</c:if>
			
			
			
       		<c:if test="${loggedInUsername != null}">
       			<jsp:include page="_welcome.jsp" />
				<a href="${entryUrl}/logout" class="pure-button">Log out</a>
			</c:if>
		  
		</div>
 </t:wrapper>