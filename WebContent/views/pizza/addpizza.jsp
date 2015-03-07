<%@ taglib prefix="addpizza" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<addpizza:wrap title="Admin - Add New Pizza">
<h1>Add New Pizza</h1>
<c:if test="${error != null}"><div class="alert alert-warning">${error}</div></c:if>
<c:if test="${msg != null}"><div class="alert alert-success">${msg}</div></c:if>
<div class="form-group">
	<form method="POST" action="pizza">
		<label for="text">Name: </label>
		<input type="text" id="name" class="form-control" placeholder="Name" name="name"><br>
		
		<label for="price">Price: </label>
		<input type="text" id="price" class="form-control" placeholder="Price" name="price"><br>
		
		<label for="price">Description: </label>
		<textarea name="description" class="form-control" rows="3"></textarea><br>
		
		<input type="submit" value="Add New Pizza" class="btn btn-default">
	</form>
</div>
</addpizza:wrap>