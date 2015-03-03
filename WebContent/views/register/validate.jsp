<%@ taglib prefix="create" tagdir="/WEB-INF/tags" %>
<create:wrap title="Create User">
<h1>Create user</h1>
<form method="POST" action="ValidatePinController">
	<input type="pincode" placeholder="enter pincode here" name="pincode"><br>
	<input type="submit" value="submit">
</form>
</create:wrap>