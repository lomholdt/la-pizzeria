<%@ taglib prefix="basket" tagdir="/WEB-INF/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<basket:wrap title="La Pizzeria - Basket">

<h1>Basket</h1> 
<p>Items in basket: <b>${basket.size}</b></p>
<p>Total: <b>${basket.totalPrice}</b></p>

<c:if test="${error != null}">
	<div class="alert alert-warning">
		<span class="glyphicon glyphicon-exclamation-sign"></span>
		${error}
	</div>
</c:if>
<c:choose>

	<c:when test="${basket.size gt 0}">
		<table class="table">
			<tr>
				<th>Name</th>
				<th>Price</th>
				<th>Description</th>
				<th>Quantity</th>
				<th></th>
			</tr>
			<c:forEach var="item" items="${basket.list}" varStatus="i">
			<tr>
				<td>${item.value.name}</td>
				<td>${item.value.price}</td>
				<td>${item.value.description}</td>
				
				<td>
					<form method="POST" action="basket">
						<div class="btn-group btn-group-sm" role="group" aria-label="...">
							<c:if test="${item.value.quantity gt 1}">
							<button type="submit" class="btn btn-default" name="mod-minus" value="${item.value.id}">-</button>
							</c:if>
							<button type="button" class="btn btn-default">${item.value.quantity}</button>
							<button type="submit" class="btn btn-default" name="mod-plus" value="${item.value.id}">+</button>
						</div>
					</form>
				</td>
				
				<td><a href="basket?remove=${item.value.id}"><span class="glyphicon glyphicon-remove-circle"></span></a></td>
			</tr>
			</c:forEach>
		</table>
		<div>
			<a href="checkout"><button type="button" class="btn btn-info"><span class="glyphicon glyphicon-shopping-cart"></span> Check out</button></a>
		</div>
	</c:when>
	<c:otherwise>
	<div class="alert alert-info">
		<span class="glyphicon glyphicon-shopping-cart"></span>
		Your basket is empty
	</div>
	</c:otherwise>

</c:choose>
</basket:wrap>