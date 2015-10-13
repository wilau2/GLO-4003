<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:wrapper>

    <body>
        <jsp:include page="_menu.jsp" />
        <div class="splash">
     			
				<div class="l-box-lrg pure-u-1 pure-u-md-2-5">
			 		<form:form method="post" class="pure-form pure-form-stacked">
			     		<fieldset>
				         <p>Click bellow to confirm your account</p>
		 					<button type="submit" class="pure-button pure-button-primary">Confirm</button>
					     </fieldset>
				 	</form:form>
				 </div>	  				 			 
		</div>
    </body>

 </t:wrapper>