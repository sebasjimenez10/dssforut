/**
 * This function sends the request to the server
 */
function sendRequest(url) {
	var xmlhttp = new XMLHttpRequest();
	// false - it's not async
	xmlhttp.open('GET', url, false);
	xmlhttp.setRequestHeader("Content-Type", "text/plain");
	xmlhttp.send(null);
	xmlhttp.onreadystatechange = httpHandle(xmlhttp);
	return;
}

/**
 * This function handles the http request
 */

var response;

function httpHandle(xmlhttp) {
	if (xmlhttp.readyState == 4) {
		if (xmlhttp.status == 200) {
			//SUCCESS
			response = xmlhttp.responseText;
			//alert( xmlhttp.responseText );
		} else {
			//ERROR
		}
	}
}