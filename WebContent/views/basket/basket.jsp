<%@ taglib prefix="basket" tagdir="/WEB-INF/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<basket:wrap title="La Pizzeria - Basket">

<h1>Pizza Basket</h1> 
<p>Items in basket: <b>${basket.size}</b></p>
<p>Total: <b>${basket.totalPrice}</b></p>

<c:if test="${error != null}"><p class="alert alert-warning">${error}</p></c:if>
<c:choose>

	<c:when test="${basket.size gt 0}">
		<table class="table">
			<tr>
				<th>Name</th>
				<th>Price</th>
				<th>Description</th>
				<th></th>
			</tr>
			<c:forEach var="item" items="${basket.list}" varStatus="i">
			<tr>
				<td>${item.name}</td>
				<td>${item.price}</td>
				<td>${item.description}</td>
				<td><a href="basket?remove=${i.count - 1}">Remove from basket</a></td>
			</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
	<div class="alert alert-info">Your basket is empty...</div>
	
	</c:otherwise>

</c:choose>
</basket:wrap>