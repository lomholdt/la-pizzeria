<%@ taglib prefix="create" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<create:wrap title="La Pizzeria - Create user">
<h1>Create user</h1>
<c:if test="${msg != null}"><div class="alert alert-warning"><p class="msg">${msg}</p></div></c:if>
<div class="form-group">
	<form method="POST" action="createuser">
		<label for="email">Email:</label>
		<input type="email" id="email" class="form-control" placeholder="Enter email" name="email"><br>
		<label for="password">Password:</label>
		<input type="password" id="password" class="form-control" placeholder="Enter password" name="password"><br>
		<label for="name">Name:</label>
		<input type="text" id="name" class="form-control" placeholder="Enter name" name="name"><br>
		<label for="address">Address:</label>
		<input type="text" id="address" class="form-control" placeholder="Enter address" name="address"><br>
		<label for="zipcode">Zipcode:</label>
		<input type="text" id="zipcode" class="form-control" placeholder="Enter zipcode" name="zipcode"><br>
		<label for="phonenumber">Phonenumber:</label>
		<input type="text" id="phonenumber" class="form-control" placeholder="Enter phonenumber" name="phonenumber"><br>
		<input type="submit" class="btn btn-default" value="Submit">
	</form>
</div>
</create:wrap>