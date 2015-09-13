<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

        <div class="header">
            <div class="home-menu pure-menu pure-menu-horizontal pure-menu-fixed">
                <a class="pure-menu-heading" href="${entryUrl}">House Match</a>
                <ul class="pure-menu-list">
                    <li class="pure-menu-item"><a href="${entryUrl}/login" class="pure-menu-link">Log In</a></li>
                    <li class="pure-menu-item"><a href="${entryUrl}/signup"class="pure-menu-link">Sign Up</a></li>
                </ul>
            </div>
        </div>

        <div class="splash-container">
            <div class="splash">
                <h1 class="splash-head">House Match</h1>
                <p class="splash-subhead">
                    Let us help you, we will know what you are looking for.
                </p>
            </div>
        </div>

    </body>

</html>
