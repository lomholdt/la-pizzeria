function checkValidPizzaName(){
	var nameField = document.getElementById("pizzaname");
	var spanTag = document.getElementById("pizza-glyph");
	var spanMsg = document.getElementById("pizzaError");
	var name = document.getElementById("name").value;
	if(isValidPizzaName(name)){
		nameField.className = "form-group has-success has-feedback";
		spanTag.className = "glyphicon glyphicon-ok form-control-feedback";
		spanMsg.style.display = "none";
	}
	else{
		nameField.className = "form-group has-error has-feedback";
		spanTag.className = "glyphicon glyphicon-remove form-control-feedback";
		spanMsg.style.display = "inline";
	}
}

function isValidPizzaName(name){
	var pattern = /^.+$/i;
	if(pattern.test(name)) return true;
	return false;
}