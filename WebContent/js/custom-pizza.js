function addMoreTopping(){
	var newDivTag = document.createElement("div");
	var newSelectTag = document.createElement("select");
	fillOptions(newSelectTag);
	var newPTag = document.createElement("p");
	
	newDivTag.className = "form-group";
	
	newSelectTag.type = "text";
	newSelectTag.className = "form-control";
	newSelectTag.placeholder = "Add topping";
	newSelectTag.name = "topping";

	newPTag.innerHTML = "Remove";
	newPTag.cursor = "pointer";
	
	// add a new toppings line
	var toppings = document.getElementById("toppings");
	toppings.appendChild(newDivTag);
	newDivTag.appendChild(newSelectTag);
	newDivTag.appendChild(newPTag);
	
	cleanToppings();
}

function fillOptions(selectTag){
	var toppings = ["Tomato", "Onions", "Pineapple", "Salad", "Mozzerella", "Jalape&#241;o", "Pepperoni", "Artichoke", "Potato", "Pesto", "Creme Fraiche"]; // Could be pulled automatically from DB
	toppings.sort();
	for (var i = 0; i < toppings.length; i++){
		var newOption = document.createElement("option")
		newOption.innerHTML = toppings[i];
		selectTag.appendChild(newOption);
	}	
}

function cleanToppings(){
	var toppings = document.getElementById("toppings");
	var divs = toppings.getElementsByTagName("div");
	var ps = toppings.getElementsByTagName("p");
	
	for(var i = 0; i < divs.length; i++){
		console.log(i);
		divs[i].id = i;
		ps[i].onclick = function(){removeTopping(this.parentNode.id);}
	}
	setToppingsCount();
}

function removeTopping(id){
	var node = document.getElementById(id);
	console.log("Got ID: " + node.id);
	if(node.parentNode){
		node.parentNode.removeChild(node);
	}
	setToppingsCount();
}

function setToppingsCount(){
	var toppings = document.getElementById("toppings");
	var divs = toppings.getElementsByTagName("div");
	document.getElementById("toppingsCount").innerHTML = " (" + divs.length + ")";
}