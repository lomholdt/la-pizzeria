<%@ taglib prefix="create" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<create:wrap title="La Pizzeria - Create user">
<script src="${pageContext.request.contextPath}/js/user-validation.js"></script>
<h1>Create user</h1>
<c:if test="${msg != null}"><div class="alert alert-warning"><p class="msg">${msg}</p></div></c:if>
<div class="form-group">
	<form method="POST" action="createuser">
		
		<div class="form-group" id ="email-group">
		<label for="email">Email:</label>
		<input type="email" id="email" class="form-control" placeholder="Enter email" name="email" onkeyup="checkValidEmail()">
		<span id="email-glyph"></span>
		</div>

		<div class="form-group" id="password-group">
		<label for="password">Password:</label>
		<input type="password" id="password" class="form-control" placeholder="Enter password" name="password" onkeyup="checkValidPassword()">
		<span id="password-glyph"></span>
		</div>
		
		<div class="form-group" id="name-group">
		<label for="name">Name:</label>
		<input type="text" id="name" class="form-control" placeholder="Enter name" name="name" onkeyup="checkValidName()">
		<span id="name-glyph"></span>
		<span style="display: none;" id="nameError" class="label label-warning">Not a valid name</span>
		</div>
		
		<div class="form-group" id="address-group">
		<label for="address">Address:</label>
		<input type="text" id="address" class="form-control" placeholder="Enter address" name="address" onkeyup="checkValidAddress()">
		<span id="address-glyph"></span>
		<span style="display: none;" id="addressError" class="label label-warning">Not a valid address</span>
		</div>
		
		<label for="zipcode">Zipcode:</label>
		<input type="text" id="zipcode" class="form-control" placeholder="Enter zipcode" name="zipcode"><br>
		
		<div class="form-group" id="phonenumber-group">
		<label for="phonenumber">Phonenumber:</label>
		<input type="text" id="phonenumber" class="form-control" placeholder="Enter phonenumber" name="phonenumber" onkeyup="checkValidPhonenumber()">
		<span style="display: none;" id="phonenumberError" class="label label-warning">Not a valid phonenumber</span>
		<span id="phonenumber-glyph"></span>
		</div>
		
		<input type="submit" class="btn btn-default" value="Submit">
	</form>
</div>
</create:wrap>