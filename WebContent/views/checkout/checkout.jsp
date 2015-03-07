<%@ taglib prefix="checkout" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<checkout:wrap title="La Pizzeria - Checkout">
<h1>Your order</h1>

<table class="table">
	<tr>
		<th>Name</th>
		<th>Price</th>
		<th>Description</th>
	</tr>
	<c:forEach var="item" items="${basket.list}" varStatus="i">
	<tr>
		<td>${item.name}</td>
		<td>${item.price}</td>
		<td>${item.description}</td>
	</tr>
	</c:forEach>
</table>

<p>Total number of pizzas: <b>${basket.size}</b></p>
<p>Total price: <b>${basket.totalPrice}</b></p>

<h3 style="padding-top:10px">Delivery information</h3>
<table class="table">
	<tr>
		<th>Name</th>
		<th>Address</th>
		<th>Zipcode</th>
	</tr>
	<tr>
		<td>${user.name}</td>
		<td>${user.address}</td>
		<td>${user.zipcode}</td>
	</tr>
</table>
<a href="receipt"><button type="button" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span> Confirm</button></a>
		

</checkout:wrap>