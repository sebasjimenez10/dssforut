
//HOST
//var host = "http://localhost:8080/dssforut/services/";
//var host = "http://dssforut.sebasjimenezv.cloudbees.net/services/";
var host;
loadConfigFile("javascript_config.json");
//SERVICES
var service = "history?";

//FUNCTIONS
/**
 * Obtiene el historico a partir de las fechas y la variable
 * seleccionadas 
 */
function getHistorico() {

	var mindate = document.getElementById("mindate");
	var maxdate = document.getElementById("maxdate");
	var variables = document.getElementById("variables");
	var variable = variables.options[variables.options.selectedIndex].text;

	if (mindate.value == "" || maxdate.value == "") {
		alert("Select dates first"); //Cambiar por los alerts de bootstrap
		return;
	}
	if( mindate.value == maxdate.value ){
		alert("You should select more than one day"); //Cambiar por los alerts de bootstrap
		return;
	}

	var params = "min=" + mindate.value + "&max=" + maxdate.value
			+ "&variable=" + variable;

	var url = host.host + service + params;
	sendRequest(url);
	
	spin("historico");
	var consultarBtn = document.getElementById("consultarBtn");
	consultarBtn.disabled = true;
}

/**
 * This function is called when getHistory services sends a response
 */
function onHistoryResponse(response){
	
	var serviceData = eval("(" + response + ")");
	var data = {
		labels : serviceData.horas,
		datasets : [ {
			fillColor : "rgba(151,187,205,0.5)",
			strokeColor : "rgba(151,187,205,1)",
			pointColor : "rgba(151,187,205,1)",
			pointStrokeColor : "#fff",
			data : serviceData.datos
		} ]
	}

	// Get the context of the canvas element we want to select
	var ctx = document.getElementById("myChart").getContext("2d");
	var myNewChart = new Chart(ctx).Line(data);
	spinner.stop();
	
	var consultarBtn = document.getElementById("consultarBtn");
	consultarBtn.disabled = false;
}

/**
 * This function sends the request to the server
 */
function sendRequest(url) {
	
	var xmlhttp = new XMLHttpRequest();
	// true - it's async
	xmlhttp.open('GET', url, true);
	xmlhttp.onreadystatechange = function(){
		if (xmlhttp.readyState == 4) {
			if (xmlhttp.status == 200) {
				//SUCCESS
				onHistoryResponse(xmlhttp.responseText);
			} else {
				//ERROR
			}
		}
	}
	xmlhttp.setRequestHeader("Content-Type", "text/plain");
	xmlhttp.send(null);
}

/**
 * This function loads the config file
 */
function loadConfigFile(url) {
	
	var xmlhttp = new XMLHttpRequest();
	// true - it's async
	xmlhttp.open('GET', url, true);
	xmlhttp.onreadystatechange = function(){
		if (xmlhttp.readyState == 4) {
			if (xmlhttp.status == 200) {
				//SUCCESS
				host = eval( "(" + xmlhttp.responseText + ")" );
			} else {
				//ERROR
			}
		}
	}
	xmlhttp.setRequestHeader("Content-Type", "text/plain");
	xmlhttp.send(null);
}

/**
 * Spin.js
 */
var spinner;
function spin (element){
	var opts = {
		  lines: 17, // The number of lines to draw
		  length: 20, // The length of each line
		  width: 10, // The line thickness
		  radius: 30, // The radius of the inner circle
		  corners: 1, // Corner roundness (0..1)
		  rotate: 0, // The rotation offset
		  direction: 1, // 1: clockwise, -1: counterclockwise
		  color: '#000', // #rgb or #rrggbb or array of colors
		  speed: 1, // Rounds per second
		  trail: 60, // Afterglow percentage
		  shadow: true, // Whether to render a shadow
		  hwaccel: false, // Whether to use hardware acceleration
		  className: 'spinner', // The CSS class to assign to the spinner
		  zIndex: 2e9, // The z-index (defaults to 2000000000)
		  top: 'auto', // Top position relative to parent in px
		  left: 'auto' // Left position relative to parent in px
	};
	var target = document.getElementById(element);
	spinner = new Spinner(opts).spin(target);
}