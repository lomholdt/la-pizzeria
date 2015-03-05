<%@ taglib prefix="validate" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<validate:wrap title="validate">
<h1>Validate pin</h1>
<c:if test="${msg != null}"><p class="msg">${msg}</p></c:if>
<c:if test="${error != null}"><p class="error">${error}</p></c:if>
<div class="form-group">
	<form method="POST" action="validate">
		<label for="pincode">PIN code:</label>
		<input type="text" class="form-control" id="pincode" placeholder="Enter PIN code here" name="pincode"><br>
		<input type="hidden" name="mail" value="${param.mail}"> 
		<input type="submit" class="btn btn-default" value="Submit">
	</form>
</div>
</validate:wrap>