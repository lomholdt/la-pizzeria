function addMoreTopping(){
	var newDivTag = document.createElement("div");
	var newInputItem = document.createElement("input");
	var newPTag = document.createElement("p");
	
	newDivTag.className = "form-group";
	
	newInputItem.type = "text";
	newInputItem.className = "form-control";
	newInputItem.placeholder = "Add topping";
	newInputItem.name = "topping";

	newPTag.innerHTML = "Remove";
	newPTag.cursor = "pointer";
	
	// add a new toppings line
	var toppings = document.getElementById("toppings");
	toppings.appendChild(newDivTag);
	newDivTag.appendChild(newInputItem);
	newDivTag.appendChild(newPTag);
	
	cleanToppings();
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