<%@ taglib prefix="validate" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<validate:wrap title="La Pizzeria - Validate">
<script src="${pageContext.request.contextPath}/js/user-validation.js"></script>
<h1>Validate PIN</h1>
<c:if test="${msg != null}"><div class="alert alert-success"><p class="msg">${msg}</p></div></c:if>
<c:if test="${error != null}"><div class="alert alert-danger"><p class="error">${error}</p></div></c:if>
	
	<form method="POST" action="validate">
		<div class="form-group" id="pincode-group">
			<label for="pincode">PIN code:</label>
			<input type="text" class="form-control" id="pincode" placeholder="Enter PIN code here" name="pincode" onkeyup="checkValidPincode()">
			<span id="pincode-glyph"></span>
			<span style="display: none;" id="pincodeError" class="label label-warning">Not a valid PIN code</span>
			<input type="hidden" name="mail" value="${param.mail}"> 
		</div>
			<input type="submit" class="btn btn-default" value="Submit">
	</form>
	
</validate:wrap>