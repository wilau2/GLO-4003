<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
	<jsp:include page="_menu.jsp" />

	<jsp:include page="_seller_side_menu.jsp" />
	<jsp:include page="estate_button.jsp" />
	<c:if test="${loggedInUserRole == 'buyer'}">
		<jsp:include page="_buyer_side_menu.jsp" />
	</c:if>
	<c:if test="${loggedInUserRole == 'seller'}">
		<jsp:include page="_seller_side_menu.jsp" />
	</c:if>
     
	<div class="splash">
			<div id = "myCarousel" class = "carousel slide" style="text-transform:none">   
		   <div class = "carousel-inner">
		   <c:forEach items="${pictures}" var="picture" varStatus="counter">
		   <c:choose>
	          <c:when test="${counter.count == 1}">
	            <div class = "item active">
	          </c:when>
	          <c:otherwise>
	            <div class = "item">
	          </c:otherwise>
        	</c:choose>
		         <img src = "${entryUrl}/picture/${estate.address.addressToUrl()}/${picture.name}" alt = "First slide" style="display:inline">
		          <div class="carousel-caption">
			          <h3 style="color:white; margin-bottom:-50px">${picture.name}</h3>
			      </div>
		      </div>
			</c:forEach>
		   </div>
		   
		   <a class = "carousel-control left" href = "#myCarousel" data-slide = "prev">&lsaquo;</a>
		   <a class = "carousel-control right" href = "#myCarousel" data-slide = "next">&rsaquo;</a>
		   
		</div>
		<button id="btn_add_picture" type="button" class="pure-button">Add Picture</button>
		<button id="btn_del_picture" type="button" class="pure-button">Remove Picture</button>
		<div id="delete_picture" style="text-align:left">
			<form method="POST" action="${entryUrl}/seller/${loggedInUsername}/estates/${estate.address.addressToUrl()}/deletePicture">
		        Name: <input type="text" name="name"><br /> <br /> 
		        <input type="submit" value="Delete"> Press here to Delete the file!
		    </form> 
	    </div>
		<div id="upload_picture" style="text-align:left">
			<form method="POST" action="${entryUrl}/seller/${loggedInUsername}/estates/${estate.address.addressToUrl()}/addPicture" enctype="multipart/form-data">
		        File to upload: <input type="file" name="file"><br /> 
		        Name: <input type="text" name="name"><br /> <br /> 
		        <input type="submit" value="Upload"> Press here to upload the file!
		    </form> 
	    </div>
		<form:form method="post" modelAttribute="estate"
			class="pure-form pure-form-aligned content-head" id="eForm">
			<fieldset>
				<legend>${estate.type} at
					${estate.address.addressToString()}</legend>
				<div class="pure-control-group">
					<form:label path="type">Type </form:label>
					<div class="pure-u-13-24">
						<form:input id="type" type="text" path="type" />
					</div>
				</div>
				<div class="pure-control-group">
					<form:label path="price">Price $</form:label>
					<div class="pure-u-13-24">
						<form:input id="price" type="number" path="price" />
					</div>
				</div>
			</fieldset>
		</form:form>

		<form:form method="POST" commandName="description"
			class="pure-form pure-form-aligned content-subhead" id="dForm">
			<fieldset>
				<legend>Description</legend>
				<div class="pure-control-group">
					<form:label path="numberOfBedRooms">Number of bedrooms</form:label>
					<div class="pure-u-13-24">
						<form:input id="numberOfBedRooms" type="number"
							path="numberOfBedRooms" />
					</div>
				</div>
				<div class="pure-control-group">
					<form:label path="numberOfBathrooms">Number of bathrooms</form:label>
					<div class="pure-u-13-24">
						<form:input id="numberOfBathrooms" type="number"
							path="numberOfBathrooms" />
					</div>
				</div>
				<div class="pure-control-group">
					<form:label path="numberOfRooms">Number of rooms</form:label>
					<div class="pure-u-13-24">
						<form:input id="numberOfRooms" type="number" path="numberOfRooms" />
					</div>
				</div>
				<div class="pure-control-group">
					<form:label path="numberOfLevel">Number of level</form:label>
					<div class="pure-u-13-24">
						<form:input id="numberOfLevel" type="number" path="numberOfLevel" />
					</div>
				</div>
				<div class="pure-control-group">
					<form:label path="yearOfConstruction">Year of construction</form:label>
					<div class="pure-u-13-24">
						<form:input id="yearOfConstruction" type="number"
							path="yearOfConstruction" />
					</div>
				</div>
				<div class="pure-control-group">
					<form:label path="buildingDimension">Dimensions of building</form:label>
					<div class="pure-u-13-24">
						<form:input id="buildingDimension" path="buildingDimension" />
					</div>
				</div>
				<div class="pure-control-group">
					<form:label path="livingSpaceAreaSquareMeter">Living space in square meters</form:label>
					<div class="pure-u-13-24">
						<form:input id="livingSpaceAreaSquareMeter"
							path="livingSpaceAreaSquareMeter" type="number" />
					</div>
				</div>
				<div class="pure-control-group">
					<form:label path="municipalAssessment">Municipal Valuation</form:label>
					<div class="pure-u-13-24">
						<form:input id="municipalAssessment" path="municipalAssessment"
							type="number" />
					</div>
				</div>
				<div class="pure-control-group">
					<form:label path="backyardOrientation">Backyard Faces</form:label>
					<div class="pure-u-13-24">
						<form:input id="backyardOrientation" path="backyardOrientation" />
					</div>
				</div>
			</fieldset>
				<c:if test="${loggedInUserRole == 'seller'}">
					<button id="btn_edit" type="button" class="pure-button">Edit</button>
					<button id="btn_save" type="submit" class="pure-button">Save</button>
					<button id="btn_cancel" type="button" class="pure-button">Cancel</button>
				</c:if>
		</form:form>
	</div>
</t:wrapper>
