<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <c:if test="${loggedInUserEmail == null}">
	        <div class="splash-container">
	            <div class="splash">
	                <h1 class="splash-head">House Match</h1>
	                <p class="splash-subhead">
	                    Let us help you, we will know what you are looking for.
	                </p>
	            </div>
	        </div>
        </c:if>
        <c:if test="${loggedInUserEmail != null}">
	        <div class="splash">
	        	<jsp:include page="_welcome.jsp" />
	        </div>
		</c:if>
		
    </body>

</html>
