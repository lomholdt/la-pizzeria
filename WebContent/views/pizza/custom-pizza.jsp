<%@ taglib prefix="custom-pizza" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<custom-pizza:wrap title="Custom Pizza">
<script src="${pageContext.request.contextPath}/js/custom-pizza.js"></script>
<script src="${pageContext.request.contextPath}/js/custom-pizza-validation.js"></script>
<h1>Create A Custom Pizza</h1>
<c:if test="${error != null}"><div class="alert alert-warning">${error}</div></c:if>
<c:if test="${msg != null}"><div class="alert alert-success">${msg}</div></c:if>
<h4>Here at La Pizzeria we are super flexible, if you don't like the pizzas you see, you can make your own custom one.
Pick a topping, and add as many as you like!</h4>
<p>We charge 50 for the pizza bottom, and 15 for each topping!</p>


<form method="POST" action="custompizza">
<h3>Choose A Name For Your Pizza</h3>
<div class="form-group" id="pizzaname">
<input class="form-control" name="name" id="name" placeholder="Give your pizza a name" onkeyup="checkValidPizzaName()">
<span id="pizza-glyph"></span>
<span style="display: none;" id="pizzaError" class="label label-warning">Not a valid pizza name</span>
</div>

<h4>Choose Toppings<span id="toppingsCount"></span></h4>
<div class="row">
	<div class="form-group col-md-5" id="toppings"> <!-- Get's populated with toppings -->
		
	</div>
</div>
<div class="form-group">
	<input type="button" class="btn btn-default" value="Add Topping" onclick="addMoreTopping()"><br>
</div>
<div class="form-group">
	<input type="submit" class="btn btn-success" value="Add Custom Pizza to Basket">
</div>
</form>

</custom-pizza:wrap>
