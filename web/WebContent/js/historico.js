//var host = "http://localhost:8080/dssforut/services/";
 var host = "http://dssforut.sebasjimenezv.cloudbees.net/services/";
var service = "history?";

function getHistorico() {

	var mindate = document.getElementById("mindate");
	var maxdate = document.getElementById("maxdate");
	var variables = document.getElementById("variables");
	var variable = variables.options[variables.options.selectedIndex].text;

	if (mindate.value == "" || maxdate.value == "") {
		alert("Select dates first");
		return;
	}

	var params = "min=" + mindate.value + "&max=" + maxdate.value
			+ "&variable=" + variable;

	var url = host + service + params;
	sendRequest(url);

	// Alert
	//alert(response);

	var serviceData = eval("(" + response + ")");
	//alert(serviceData.datos);
	//alert(serviceData.horas);

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

}