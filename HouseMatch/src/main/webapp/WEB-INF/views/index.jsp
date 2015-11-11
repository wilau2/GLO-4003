<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>
        <jsp:include page="_menu.jsp" />
        <c:if test="${loggedInUsername == null}">
	        <div class="splash-container">
	            <div class="splash">
	                <h1 class="splash-head">House Match</h1>
	                <p class="splash-subhead">
	                    Let us help you, we will know what you are looking for.
	                </p>
	            </div>
	        </div>
        </c:if>
        <c:if test="${loggedInUsername != null}">
	        <div class="splash">
	        	<jsp:include page="_welcome.jsp" />
	        </div>
		</c:if>
		<div class="container">
					<form method="POST" action="uploadFile" enctype="multipart/form-data">
				        File to upload: <input type="file" name="file"><br /> 
				        Name: <input type="text" name="name"><br /> <br /> 
				        <input type="submit" value="Upload"> Press here to upload the file!
				    </form> 
		</div>
</t:wrapper>
