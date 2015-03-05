<%@ taglib prefix="login" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<login:wrap title="Login">
<h1>Login</h1>
<c:if test="${msg != null}"><p class="msg">${msg}</p></c:if>
<form method="POST" action="LoginController">
	<input type="email" placeholder="enter email" name="email"><br>
	<input type="password" placeholder="enter password" name="password"><br>
	<input type="submit" value="login">
</form>
</login:wrap>