<%@ taglib prefix="receipt" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<receipt:wrap title="La Pizzeria - Receipt">
<h1>Thank you for eating pizza everyday <span class="glyphicon glyphicon-ok" aria-hidden="true"></span> </h1>

<div style="padding-top:10px">Your order:</div>

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


</receipt:wrap>