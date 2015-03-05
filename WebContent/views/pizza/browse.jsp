<%@ taglib prefix="browse" tagdir="/WEB-INF/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<browse:wrap title="La Pizzeria - Browse">

<h1>Pizza Browser</h1>



<!-- PIZZA BROWSER BEGIN -->
<c:if test="${error != null}"><p class="error">${error}</p></c:if>
<c:if test="${totalPages gt 0}">
	<table class="table">
		<form method="GET" action="">
			<select name="sortBy" onchange="this.form.submit()">
				<option selected="selected" disabled="disabled">Sort By</option>
				<option value="name">Name</option>
				<option value="price">Price</option>
			</select>
		</form>
		<tr>
			<th>Name</th>
			<th>Price</th>
			<th>Description</th>
		</tr>
		<c:forEach var="pizza" items="${pizzas}">
		<tr>
			<td>${pizza.name}</td>
			<td>${pizza.price}</td>
			<td>${pizza.description}</td>
		</tr>
		</c:forEach>
	</table>
	
	<!-- PAGINATION BEGIN -->
	<ul class="pagination">
	<c:if test="${page gt 1}">
		<li><a href="browse?page=${page - 1}&sortBy=${sortBy}">&lt; Previous</a></li>
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
		<li><a href="browse?page=${page + 1}&sortBy=${sortBy}">Next &gt;</a></li>
	</c:if>
	</ul>
</c:if>
<!-- PAGINATION END -->

</browse:wrap>