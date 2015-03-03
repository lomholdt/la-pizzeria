<%@ taglib prefix="login" tagdir="/WEB-INF/tags" %>
<login:wrap title="Login">
<h1>Login</h1>
<form method="POST" action="logincontroller">
	<input type="email" placeholder="enter email" name="email"><br>
	<input type="password" placeholder="enter password" name="password"><br>
	<input type="submit" value="login">
</form>
</login:wrap>