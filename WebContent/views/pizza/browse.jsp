<%@ taglib prefix="browse" tagdir="/WEB-INF/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<browse:wrap title="La Pizzeria - Pizzas">

<h1>Pizzas</h1>
<p>Choose one of our tasty pizzas, our <a href="custompizza">create your own</a></p>



<!-- PIZZA BROWSER BEGIN -->
<c:if test="${error != null}">
	<div class="alert alert-warning">
		<span class="glyphicon glyphicon-exclamation-sign"></span>
		${error}
	</div>
</c:if>
<c:if test="${msg != null}">
	<div class="alert alert-success">
		<span class="glyphicon glyphicon-exclamation-sign"></span>
		${msg}
	</div>
</c:if>
<c:if test="${totalPages gt 0}">
	<form method="GET" action="">
		<select name="sortBy" onchange="this.form.submit()">
			<option selected="selected" disabled="disabled">Sort By</option>
			<option value="name">Name</option>
			<option value="price">Price</option>
		</select>
	</form>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Name</th>
				<th>Price</th>
				<th>Description</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="pizza" items="${pizzas}">
			<tr>
				<td>${pizza.name}</td>
				<td>${pizza.price}</td>
				<td>${pizza.description}</td>
				<td><a href="basket?add=${pizza.id}"><span class="glyphicon glyphicon-plus-sign"></span></a></td>
				<c:if test="${user.role ne null}">
					<c:if test="${user.role eq 'admin'}"><td><a href="pizza?remove=${pizza.id}">Remove</a></td></c:if>
				</c:if>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- PAGINATION BEGIN -->
	<ul class="pagination">
	<c:if test="${page gt 1}">
		<li><a href="browse?page=${page - 1}&sortBy=${sortBy}"><span class="glyphicon glyphicon-menu-left"></span> Previous</a></li>
	</c:if>
	
	<c:forEach begin="1" end="${totalPages}" var="i">
		<c:choose>
			<c:when test="${page eq i}">
				<li class="active"><a>${i}</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="browse?page=${i}">${i}</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	<c:if test="${page lt totalPages}">
		<li><a href="browse?page=${page + 1}&sortBy=${sortBy}">Next <span class="glyphicon glyphicon-menu-right"></span></a></li>
	</c:if>
	</ul>
</c:if>
<!-- PAGINATION END -->

</browse:wrap>