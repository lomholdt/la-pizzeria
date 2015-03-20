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