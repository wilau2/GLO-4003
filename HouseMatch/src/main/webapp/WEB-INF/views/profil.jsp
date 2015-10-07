<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>


	<jsp:include page="_menu.jsp" />
	
	<div id="editable" class="splash">
		<input type="checkbox" id="edit" role="button" class="pure-button">
		<label for="edit" onclick=""><span>Edit</span><span>Cancel</span>
		</label>
		 
		    <div>
			   	<p><span>Username: </span><span>${user.username}</span></p>
			   	<p><span>First Name: </span><span>${user.firstName}</span></p>
			   	<p><span>Last Name: </span><span>${user.lastName}</span></p>
			   	<p><span>Phone Number: </span><span>${user.phoneNumber}</span></p>
			   	<p><span>Email: </span><span>${user.email}</span></p>
			   	<!-- a href="${entryUrl}/profil/edit" class="pure-button">Edit</a-->
		   	</div>
		  
		 
		   	<div>
			   	<form:form  method="post" modelAttribute="user" class="pure-form pure-form-stacked">
		              <fieldset>
		              		<form:input id="username" type="text" placeholder="Username" path="username" readonly="true"/>
		              		<form:input id="firstName" type="text" placeholder="First Name" path="firstName"/>
							<form:input id="lastName" type="text" placeholder="Last Name" path="lastName"/>
							<form:input id="phoneNumber" type="text" placeholder="Phone Number" path="phoneNumber"/>
							<form:input id="email" type="text" placeholder="Email" path="email"/>
		                  	<button type="submit" class="pure-button">Save</button>
		              </fieldset>
		         </form:form>
	         </div>   
		         
	
	</div>
</t:wrapper>