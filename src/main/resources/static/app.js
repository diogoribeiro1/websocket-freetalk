let currentGroupId = '';

const stompClient = new StompJs.Client({
    brokerURL: 'ws://' + window.location.host + '/ws'
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);

    const group = $("#group").val().trim();
    if (group) {
        currentGroupId = group;
        $("#chat-title").text("Group Chat: " + currentGroupId);

        stompClient.subscribe('/topic/messages/' + currentGroupId, (message) => {
            updateLiveChat(JSON.parse(message.body));
        });

        $.get("/chat/group/" + currentGroupId, function (messages) {
            $("#livechat").empty();
            messages.forEach(updateLiveChat);
        });

    } else {
        alert("Please enter a group name.");
    }
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
    const messageData = {
        sender: $("#user").val(),
        content: $("#message").val(),
        groupId: currentGroupId
    };

    stompClient.publish({
        destination: "/app/chat",
        body: JSON.stringify(messageData)
    });
    $("#message").val("");
}

function updateLiveChat(message) {
    $("#livechat").append("<tr><td><b>" + message.sender + "</b>: " + message.content + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $("#connect").click(() => connect());
    $("#disconnect").click(() => disconnect());
    $("#send").click(() => sendMessage());
});
