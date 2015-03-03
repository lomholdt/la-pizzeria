<%@ taglib prefix="browse" tagdir="/WEB-INF/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<browse:wrap title="La Pizzeria - Browse">

<h1>Pizza Browser</h1>



<!-- PIZZA BROWSER BEGIN -->
<c:if test="${totalPages != 0}">
	<table border="1">
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
	<c:if test="${page != 1}">
		<a href="browse?page=${page - 1}&sortBy=${sortBy}">&lt; Previous</a>
	</c:if>
	
	<c:forEach begin="1" end="${totalPages}" var="i">
		<c:choose>
			<c:when test="${page eq i}">
				${i}
			</c:when>
			<c:otherwise>
				<a href="browse?page=${i}">${i}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	<c:if test="${page lt totalPages}">
		<a href="browse?page=${page + 1}&sortBy=${sortBy}">Next &gt;</a>
	</c:if>
</c:if>
<!-- PAGINATION END -->

</browse:wrap>