<%@ taglib prefix="login" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<login:wrap title="La Pizzeria - Login">
<h1>Login</h1>
<script src="${pageContext.request.contextPath}/js/user-validation.js"></script>
<c:if test="${msg != null}"><div class="alert alert-warning"><p class="msg">${msg}</p></div></c:if>
<c:if test="${msgOK != null}"><div class="alert alert-success"><p class="msg">${msgOK}</p></div></c:if>

<div class="form-group">
	<form method="POST" action="login">
		<label for="email">Email:</label>
		<input type="email" id="email" class="form-control" placeholder="Enter email" name="email" onkeyup="checkLoginCredentials()"><br>
		<label for="password">Password:</label>
		<input type="password" id="password" class="form-control" placeholder="Enter password" name="password" onkeyup="checkLoginCredentials()"><br>
		<input type="submit" value="Login" class="btn btn-default" id="submit" disabled="disabled"> or <a href="createuser">create new user</a>
	</form>
</div>
</login:wrap>