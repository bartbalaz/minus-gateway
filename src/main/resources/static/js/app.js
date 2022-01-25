var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}
var ws;

function connect() {

    /*

    var socket = new SockJS('https://bbalaz.ddns.net/gs-guide-websocket');
    //var socket = new SockJS('http://192.168.1.10:8080/gs-guide-websocket');

    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });

    */

    	ws = new WebSocket('wss://bbalaz.ddns.net/ws');
    	ws.onmessage = function(data){
    		showGreeting(data.data);
    	}

    	console.log("connecting...")
}




function disconnect() {
/*
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
    */

        if (ws != null) {
            ws.close();
        }
        console.log("Disconnected");
}

function sendName() {
   // stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
    ws.send(JSON.stringify($("#name").val()));

}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});