<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
	<jsp:include page="_menu.jsp" />
	<jsp:include page="_seller_side_menu.jsp" />
	<div class="splash">
		<form:form method="post" modelAttribute="estate" 
			class="pure-form pure-form-aligned">
			<fieldset>
				<legend>Add an estate to sell</legend>
				<div class="pure-control-group">
					<form:label path="type">Type </form:label>
					<form:select id="type" path="type">
						<option value="NONE">--- Select ---</option>
						<option value="CONDO">Condo</option>
						<option value="APPARTMENT">Appartment</option>
						<option value="SINGLE_FAMILY">Single family</option>
						<option value="MULTIPLEX">Multiplex</option>
						<option value="LOT">Lot</option>
						<option value="COTTAGE">Cottage</option>
						<option value="COMMERCIAL">Commercial</option>
					</form:select>
				</div>
				<h3>Address</h3>
				<div class="pure-control-group">
					<div class="pure-control-group">

						<form:label path="address.civicNumber">Civic No </form:label>
						<div class="pure-u-13-24">
							<form:input id="civicNumber" type="number"
								path="address.civicNumber" />
						</div>
					</div>
					<div class="pure-control-group">
						<form:label path="address.street">Street </form:label>
						<div class="pure-u-13-24">
							<form:input id="street" type="text" path="address.street" />
						</div>
					</div>
					<div class="pure-control-group">
						<form:label path="address.postalCode">Postal Code </form:label>
						<div class="pure-u-13-24">
							<form:input id="postalCode" type="text" path="address.postalCode" />
						</div>
					</div>
					<div class="pure-control-group">
						<form:label path="address.state">State </form:label>
						<div class="pure-u-13-24">
							<form:input id="state" type="text" path="address.state" />
						</div>
					</div>
					<div class="pure-control-group">
						<form:label path="address.country">Country </form:label>
						<div class="pure-u-13-24">
							<form:input id="country" type="text" path="address.country" />
						</div>
					</div>
				</div>
				<div class="pure-control-group">
					<form:label path="price">Price </form:label>
					<div class="pure-u-13-24">
						<form:input id="price" type="number" path="price" value="1000"
							min="0" step="100" />
					</div>
				</div>
				<button type="submit" class="pure-button pure-button-primary">Add
					to sell</button>
				<button type="reset" class="pure-button pure-button-cancel">Reset</button>
			</fieldset>
		</form:form>
	</div>
</t:wrapper>