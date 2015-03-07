<%@ taglib prefix="validate" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<validate:wrap title="La Pizzeria - Validate">
<h1>Validate PIN</h1>
<c:if test="${msg != null}"><div class="alert alert-success"><p class="msg">${msg}</p></div></c:if>
<c:if test="${error != null}"><div class="alert alert-danger"><p class="error">${error}</p></div></c:if>
<div class="form-group">
	<form method="POST" action="validate">
		<label for="pincode">PIN code:</label>
		<input type="text" class="form-control" id="pincode" placeholder="Enter PIN code here" name="pincode"><br>
		<input type="hidden" name="mail" value="${param.mail}"> 
		<input type="submit" class="btn btn-default" value="Submit">
	</form>
</div>
</validate:wrap>