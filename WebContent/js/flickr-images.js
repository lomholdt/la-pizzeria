function getFlickrPics(int){
	var http;
	var pizzaImgID = ""+int
	var searchKey = $("#pizzaImg"+int).text().replace(/ /g, ','); 
	var url = "https://api.flickr.com/services/rest/?&method=flickr.photos.search&api_key=9b50e46612c7271cdd3dacd610642e25&&format=json&per_page=20&tag_mode=all&tags=pizza,"+searchKey; // Flickr url
	console.log(url);

	if(navigator.appName == "Microsoft Internet Explorer"){
		http = new ActiveXObject("Microsoft.XMLHTTP");
	}
	else{
		http = new XMLHttpRequest();	
	}

	http.onreadystatechange = function() {
    	if (http.readyState == 4 && http.status == 200) { // success, then go
	    	var jsonObj = http.responseText;
			var flickrJSON = jsonObj.substring(14,jsonObj.length-1); // remove flickrJson, to parse JSON file
			var newJsonObject = JSON.parse(flickrJSON);
			var pizzaImgId = newJsonObject.photos.photo[0].id;
			var farmID = newJsonObject.photos.photo[0].farm;
			var serverID = newJsonObject.photos.photo[0].server;
			var secret = newJsonObject.photos.photo[0].secret;
			$("#pizzaImg"+int).tooltip({ content: '<img class="pizTooltip" src="'+ "https://farm"+farmID+".staticflickr.com/"+serverID+"/"+pizzaImgId+"_"+secret+".jpg" +'"/>'}); 
    	}
    }

	http.open("GET", url, true); // get JSON from flickr
	http.send();
}
	
$( document ).ready(function() {
    for (var i = 0; i < 10; i++) {
		getFlickrPics(i);
	}
});