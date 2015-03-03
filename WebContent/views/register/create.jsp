<%@ taglib prefix="create" tagdir="/WEB-INF/tags" %>
<create:wrap title="Create User">
<h1>Create user</h1>
<form method="POST" action="createusercontroller">
	<input type="text" placeholder="enter email" name="email"><br>
	<input type="text" placeholder="enter password" name="password"><br>
	<input type="text" placeholder="enter name" name="name"><br>
	<input type="text" placeholder="enter address" name="address"><br>
	<input type="text" placeholder="enter zipcode" name="zipcode"><br>
	<input type="text" placeholder="enter phonenumber" name="phonenumber"><br>
	<input type="submit" value="create">
</form>

</create:wrap>