<%@ taglib prefix="create" tagdir="/WEB-INF/tags" %>
<create:wrap title="Create User">

<form method="POST" action="createusercontroller">
	<input type="text" placeholder="enter email" name="email">
	<input type="text" placeholder="enter password" name="password">
	<input type="text" placeholder="enter name" name="name">
	<input type="text" placeholder="enter address" name="address">
	<input type="text" placeholder="enter zipcode" name="zipcode">
	<input type="text" placeholder="enter phonenumber" name="phonenumber">
	<input type="submit" value="create">
</form>

</create:wrap>