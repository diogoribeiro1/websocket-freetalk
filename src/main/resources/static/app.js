const stompClient = new StompJs.Client({
    brokerURL: 'ws://' + window.location.host + '/ws'
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/messages', (message) => {
        console.log('TESTE= '+ message)
        updateLiveChat(JSON.parse(message.body));
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
}

function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
    stompClient.publish({
        destination: "/app/chat",
        body: JSON.stringify({'sender': $("#user").val(), 'content': $("#message").val()})
    });
    $("#message").val("");
}

function updateLiveChat(message) {
    $("#livechat").append("<tr><td>" + "<b>" + message.sender + "</b>: "+ message.content + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#send" ).click(() => sendMessage());
});