<%@ taglib prefix="browse" tagdir="/WEB-INF/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<browse:wrap title="La Pizzeria - Browse">
 

<h1>Pizza Browser</h1>


<table>
	<c:forEach var="pizza" items="${pizzas}">
	<tr>
		<td>${pizza.name}</td>
		<td>${pizza.price}</td>
		<td>${pizza.description}</td>
	</tr>
	</c:forEach>
</table>


<c:if test="${page != 1}">
	<a href="browse?page=${page - 1}">&lt; Previous</a>
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
	<a href="browse?page=${page + 1}">Next &gt;</a>
</c:if>

</browse:wrap>