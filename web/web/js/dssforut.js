
//HOST
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

    if (mindate.value === "" || maxdate.value === "") {
        alert("Select dates first"); //Cambiar por los alerts de bootstrap
        return;
    }
    if (mindate.value === maxdate.value) {
        alert("You should select more than one day"); //Cambiar por los alerts de bootstrap
        return;
    }

    var params = "min=" + mindate.value + "&max=" + maxdate.value
            + "&variable=" + variable;

    var url = host.host + service + params;
    serverGet(url);

    spin("historico");
    var consultarBtn = document.getElementById("consultarBtn");
    consultarBtn.disabled = true;
}

/**
 * This function is called when getHistory services sends a response
 * @param {type} response
 * @returns {undefined}
 */
function onHistoryResponse(response) {

    var serviceData = eval("(" + response + ")");
    var data = {
        labels: serviceData.horas,
        datasets: [{
                fillColor: "rgba(151,187,205,0.5)",
                strokeColor: "rgba(151,187,205,1)",
                pointColor: "rgba(151,187,205,1)",
                pointStrokeColor: "#fff",
                data: serviceData.datos
            }]
    };

    // Get the context of the canvas element we want to select
    var ctx = document.getElementById("myChart").getContext("2d");
    var myNewChart = new Chart(ctx).Line(data);

    spinner.stop();
    var consultarBtn = document.getElementById("consultarBtn");
    consultarBtn.disabled = false;
}

/**
 * Function to get server resources
 * @param {type} url
 * @returns {undefined}
 */
function serverGet(url) {
    $.get(url, function(data) {
        onHistoryResponse(data);
    });
}

/**
 * This function loads the config file
 * @param {type} url
 * @returns {undefined}
 */
function loadConfigFile(url) {
    $.get(url, function(data) {
        host = eval("(" + data + ")");
    });
}

/**
 * Spin.js
 */
var spinner;
function spin(element) {
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

/**
 * Load Charts
 */
var chart;
var dps;
function loadCharts() {

    dps = []; // dataPoints

    chart = new CanvasJS.Chart("humedadChart", {
        title: {
            text: "Humedad Data"
        },
        data: [{
                type: "line",
                dataPoints: dps
            }]
    });
}

var a = 1;
function updateChart(xVal, yVal) {

    var dataLength = 20; // number of dataPoints visible at any point

    if (dps.length > dataLength)
    {
        dps.shift();
    }

    dps.push({
        label: xVal,
        x: a++,
        y: Number(yVal)
    });

    chart.render();
}

var webSocket = new WebSocket("ws://" + document.location.host + document.location.pathname + "datasocket");

webSocket.onerror = function(event) {
    onError(event);
};
webSocket.onopen = function(event) {
    onOpen(event);
};
webSocket.onmessage = function(event) {
    onMessage(event);
};

function onMessage(event) {
    var data = eval("(" + event.data + ")");

    updateChart(data.hora, data.humedad);
}
function onOpen(event) {

}
function onError(event) {
    alert(event.data);
}