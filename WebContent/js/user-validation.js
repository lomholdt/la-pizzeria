var http;
if (navigator.appName == "Microsoft Internet Explorer"){
	http = new ActiveXObject("Microsoft.XMLHTTP");
}
else{
	http = new XMLHttpRequest();	
}

function sendRequest(action, responseHandler){
	http.open("POST", action);
	http.onreadystatechange = responseHandler;
	http.send(null);
}

function checkValidEmail(){
	if (http.readyState == 0 || http.readyState == 4){
		// ready to rock'n'roll!
		var email = document.getElementById("email").value;
		if(isValidEmail(email)){
			sendRequest("email-is-available?email=" + encodeURIComponent(email), responseReceived);			
		}
	}
}

function responseReceived(){
	if (http.readyState == 4){
		try {
			if (http.status == 200){
				var emailField = document.getElementById("email-group");
				var result = http.responseText;
				var spanTag = document.getElementById("email-glyph");
				if (result == "true"){
					emailField.className = "form-group has-success has-feedback";
					spanTag.className = "glyphicon glyphicon-ok form-control-feedback";
				}
				else{
					emailField.className = "form-group has-error has-feedback";
					spanTag.className = "glyphicon glyphicon-remove form-control-feedback";
				}
			}
			
		} catch (e) {
			alert(e);
		}
	}
}

function isValidEmail(email){
	var pattern = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i;
	if (pattern.test(email) && email.length != 0) return true;
	return false;
}

function isValidPassword(password){
	var pattern = /^(?=.*[0-9])(?=.*[A-z])(?=.*[*'^¨~|;,.><!#¤%&()=?@£$€])(?=\S+$).{6,}$/;
	if(pattern.test(password)) return true;
	return false;
}

function isValidName(name){
	var pattern = /^[A-z\\s]+$/i;
	if(pattern.test(name)) return true;
	return false;
}

function isValidAddress(address){
	var pattern = /^(?=.*\s)(?=.*\d).*/;
	if(pattern.test(address)) return true;
	return false;
}

function isValidZipcode(zipcode){
	var pattern = /^\d{4}$/;
	if(pattern.test(zipcode)) return true;
	return false;
}

function isValidPhonenumber(phonenumber){
	var pattern = /^\d{8}$/;
	if(pattern.test(phonenumber)) return true;
	return false;
}

function isValidPincode(pincode){
	var pattern = /^\d{4}$/;
	if(pattern.test(pincode)) return true;
	return false;
}

function checkValidPassword(){
	var passwordField = document.getElementById("password-group");
	var spanTag = document.getElementById("password-glyph");
	var password = document.getElementById("password").value;
	if(isValidPassword(password)){
		passwordField.className = "form-group has-success has-feedback";
		spanTag.className = "glyphicon glyphicon-ok form-control-feedback";
	}
	else{
		passwordField.className = "form-group has-error has-feedback";
		spanTag.className = "glyphicon glyphicon-remove form-control-feedback";
	}
}


function checkValidName(){
	var nameField = document.getElementById("name-group");
	var spanTag = document.getElementById("name-glyph");
	var spanMsg = document.getElementById("nameError");
	var name = document.getElementById("name").value;
	if(isValidName(name)){
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


function checkValidAddress(){
	var addressField = document.getElementById("address-group");
	var spanTag = document.getElementById("address-glyph");
	var spanMsg = document.getElementById("addressError");
	var address = document.getElementById("address").value;
	if(isValidAddress(address)){
		addressField.className = "form-group has-success has-feedback";
		spanTag.className = "glyphicon glyphicon-ok form-control-feedback";
		spanMsg.style.display = "none";
	}
	else{
		addressField.className = "form-group has-error has-feedback";
		spanTag.className = "glyphicon glyphicon-remove form-control-feedback";
		spanMsg.style.display = "inline";
	}
}

function checkValidZipcode(){
	var zipcodeField = document.getElementById("zipcode-group");
	var spanTag = document.getElementById("zipcode-glyph");
	var spanMsg = document.getElementById("zipcodeError");
	var zipcode = document.getElementById("zipcode").value;
	if(isValidZipcode(zipcode)){
		zipcodeField.className = "form-group has-success has-feedback";
		spanTag.className = "glyphicon glyphicon-ok form-control-feedback";
		spanMsg.style.display = "none";
	}
	else {
		zipcodeField.className = "form-group has-error has-feedback";
		spanTag.className = "glyphicon glyphicon-remove form-control-feedback";
		spanMsg.style.display = "inline";
	}
}

function checkValidPhonenumber(){
	var phonenumberField = document.getElementById("phonenumber-group");
	var spanTag = document.getElementById("phonenumber-glyph");
	var spanMsg = document.getElementById("phonenumberError");
	var phonenumber = document.getElementById("phonenumber").value;
	if(isValidPhonenumber(phonenumber)){
		phonenumberField.className = "form-group has-success has-feedback";
		spanTag.className = "glyphicon glyphicon-ok form-control-feedback";
		spanMsg.style.display = "none";
	}
	else {
		phonenumberField.className = "form-group has-error has-feedback";
		spanTag.className = "glyphicon glyphicon-remove form-control-feedback";
		spanMsg.style.display = "inline";
	}
}

function checkValidPincode(){
	var pincodeField = document.getElementById("pincode-group");
	var spanTag = document.getElementById("pincode-glyph");
	var spanMsg = document.getElementById("pincodeError");
	var pincode = document.getElementById("pincode").value;
	if(isValidPincode(pincode)){
		pincodeField.className = "form-group has-success has-feedback";
		spanTag.className = "glyphicon glyphicon-ok form-control-feedback";
		spanMsg.style.display = "none";
	}
	else {
		pincodeField.className = "form-group has-error has-feedback";
		spanTag.className = "glyphicon glyphicon-remove form-control-feedback";
		spanMsg.style.display = "inline";
	}
}


