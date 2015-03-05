<%@ taglib prefix="basket" tagdir="/WEB-INF/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<basket:wrap title="La Pizzeria - Basket">

<h1>Pizza Basket</h1>

<c:if test="${error != null}"><p class="error">${error}</p></c:if>
<c:choose>

	<c:when test="${basket.size gt 0}">
		<table class="table">
			<tr>
				<th>Name</th>
				<th>Price</th>
				<th>Description</th>
			</tr>
			<c:forEach var="item" items="${basket.list}">
			<tr>
				<td>${item.name}</td>
				<td>${item.price}</td>
				<td>${item.description}</td>
			</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
	<div>Your basket is empty...</div>
	
	</c:otherwise>

</c:choose>
</basket:wrap>