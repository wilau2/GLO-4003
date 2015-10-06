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
     			
				<div class="l-box-lrg pure-u-1 pure-u-md-2-5">
			 		<p>Please check your email to confirm your account! You may close this page.</p>		 		
				 </div>	  
		</div>
    </body>

</html>