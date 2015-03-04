<%@ taglib prefix="validate" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<validate:wrap title="validate">
<h1>Validate pin</h1>
<c:if test="${error != null}"><p class="error">${error}</p></c:if>
<form method="POST" action="ValidatePinController">
	<input type="text" placeholder="enter pincode here" name="pincode"><br>
	<input type="hidden" name="email" value="${param.email}"> 
	<input type="submit" value="submit">
</form>
</validate:wrap>