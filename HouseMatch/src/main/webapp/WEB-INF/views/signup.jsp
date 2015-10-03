<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="A layout example that shows off a responsive product landing page.">

        <title>Landing Page &ndash; Layout Examples &ndash; Pure</title>

        <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
        <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/grids-responsive-min.css">
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
        <link rel="stylesheet" href="/resources/css/layouts/marketing.css">
    </head>

    <body>

        <jsp:include page="_menu.jsp" />

        <div class="splash">
            
            <c:if test="${loggedInUserEmail == null}">
	            <div class="l-box-lrg pure-u-1 pure-u-md-2-5">
	                <form:form method="post" modelAttribute="user" class="pure-form pure-form-stacked">
	                    <fieldset>
	                    	<form:radiobutton value="buyer" path="role"/> BUYER
	                      	<form:radiobutton value="seller" path="role"/> SELLER
  							<form:input id="firstName" type="text" placeholder="First name" path="firstName"/>
  							<form:input id="lastName" type="text" placeholder="Last name" path="lastName"/>
  							<form:input id="phoneNumber" type="text" placeholder="Phone number" path="phoneNumber"/>
  							<form:input id="email" type="text" placeholder="Email" path="email"/>
  						    <form:input id="username" type="text" placeholder="Username" path="username"/>
	                        <form:input id="password" type="password" placeholder="Password" path="password"/>
	                        <button type="submit" class="pure-button">Sign Up</button>
	                    </fieldset>
	                </form:form>
	            </div>
            </c:if>
 			<c:if test="${loggedInUserEmail != null}">
 				<jsp:include page="_welcome.jsp" />
 				<a href="${entryUrl}/logout" class="pure-button">Log out</a>
 			</c:if>
        </div>
    </body>

</html>