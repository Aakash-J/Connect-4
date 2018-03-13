function getXMLObject() //XML OBJECT
{
	var xmlHttp = false;
	try {
		xmlHttp = new ActiveXObject("Msxml2.XMLHTTP") // For Old Microsoft Browsers
	} catch (e) {
		try {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP") // For Microsoft IE 6.0+
		} catch (e2) {
			xmlHttp = false // No Browser accepts the XMLHTTP Object then false
		}
	}
	if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
		xmlHttp = new XMLHttpRequest(); //For Mozilla, Opera Browsers
	}
	return xmlHttp; // Mandatory Statement returning the ajax object created
}

var xmlhttp = new getXMLObject(); //xmlhttp holds the ajax object
var xmlhttp2 = new getXMLObject();
var xmlhttpChat = new getXMLObject();

//  This funtions check for online users.

function ajaxFunction(obj) {

	xmlhttpChatInsert("auto");

	if (xmlhttp) {

		xmlhttp.open("POST", "online.do", true);
		xmlhttp.onreadystatechange = handleServerResponse;
		xmlhttp.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded');
		xmlhttp.send("&Player1=" + document.getElementById("uname").value);

	}
}

//  This funtion calls a java servlet to insert chat in database.

function xmlhttpChatInsert(obj) {
	text = document.getElementById("textData").value;
	//console.log(obj + "aakash");

		if (obj == "auto")
			text = "";

	if (xmlhttpChat) {
		xmlhttpChat.open("POST", "insertChat.do", true);
		xmlhttpChat.onreadystatechange = handleServerResponseChat;
		xmlhttpChat.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded');
		xmlhttpChat.send("&chat=" + text);
		if (obj != "auto") {
			document.getElementById("textData").value = "";

		}

	}
}

//  This funtion calls java servlet to start a new Game.

function startGame(obj) {



	if (xmlhttp2) {

		xmlhttp2.open("POST", "/InitiateGameServlet.do", true);
		xmlhttp2.onreadystatechange = handleServerResponse2;
		xmlhttp2.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded');
		xmlhttp2.send("Player2=" + obj.name + "&Player1="
				+ document.getElementById("uname").value);

	}
}

//  This funtion calls java servlet to resume existing game.

function resumeGame(obj) {

	if (xmlhttp2) {

		xmlhttp2.open("POST", "/InitiateGameServlet.do", true);
		xmlhttp2.onreadystatechange = handleServerResponse2;
		xmlhttp2.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded');
		xmlhttp2.send("Player2=" + " " + "&Player1="
				+ document.getElementById("uname").value + "&gameId="
				+ obj.name);

	}
}


function spectateGame(obj) {
	sessionStorage.gameId = obj.name;
	sessionStorage.vs = obj.innerHTML;
	window.open("/spectate.jsp", '_blank');
}

// Checks if game already exists> If Yes then resumes it after alerting the user.

function handleServerResponse2() {
	if (xmlhttp2.readyState == 4) {
		if (xmlhttp2.status == 200) {
		//	console.log(xmlhttp2.responseText + "aakash");
			if ((xmlhttp2.responseText) == "true") {
				alert("Game Already in progress");
				window.open("/index.jsp");
			} else
				window.open("/index.jsp");

		} else {
			alert("Error during AJAX call. Please try again");
		}
	}
}

// This function performs to task:
//                                1) Gets username of the online user and populate them on front-end.
//                                2) Gets challenges from the online user and populate them on front-end.

function handleServerResponse() {
	if (xmlhttp.readyState == 4) {
		if (xmlhttp.status == 200) {
			var div = document.getElementById("onlineUsers");
			var div2 = document.getElementById("challenges");
			var div3 = document.getElementById("Spectate");
			div.innerHTML = "";
			div2.innerHTML = "";
			div3.innerHTML = "";
			//alert(xmlhttp.responseText);
			var userName = document.getElementById("uname").value;

			game = (xmlhttp.responseText).split("|");
			data = game[0].split("~");
			challenge = game[1].split("~");
			spectate  = game[3].split("~");
			console.log(game[2]);

			document.getElementById("topPlayer").innerHTML  = game[2];



			for (i = 0; i < data.length; i++) {
				if (data[i] != "null") {
					if ((data[i]).trim() != userName.trim()) {
						var button = document.createElement("button");
						button.name = data[i];
						button.className = "btn btn-primary btn-sm mx-auto mt-3 a";
						button.innerHTML = data[i];

						button.addEventListener("click", function() {
							startGame(this);
						});

						div.appendChild(button);
						div.appendChild(document.createElement("br"));

					}
				}

			}

			//console.log(challenge);
			if (challenge.length > 1)

				for (i = 0; i < challenge.length; i++) {
					if (challenge[i] != "null" && challenge[i] != "") {
						if ((challenge[i]).trim() != userName.trim()) {
							split1 = challenge[i].split(":");
							challenge[i] = split1[0];
							//console.log(challenge.length);
							var button = document.createElement("button");
							button.name = challenge[i];
							button.className = "btn btn-primary btn-sm mx-auto mt-3 a";
							button.innerHTML = split1[1];

							button.addEventListener("click", function() {
								resumeGame(this);
							});

							div2.appendChild(button);
							div2.appendChild(document.createElement("br"));

						}
					}

				}

			if (spectate.length > 1)

				for (i = 0; i < spectate.length; i++) {
					if (spectate[i] != "null" && spectate[i] != "") {
						if ((spectate[i]).trim() != userName.trim()) {
							split1 = spectate[i].split(":");
							spectate[i] = split1[0];
							//console.log(challenge.length);
							var button = document.createElement("button");
							button.name = spectate[i];
							button.className = "btn btn-primary btn-sm mx-auto mt-3 a";
							button.innerHTML = split1[1];

							button.addEventListener("click", function() {
								spectateGame(this);
							});

							div3.appendChild(button);
							div3.appendChild(document.createElement("br"));

						}
					}

				}

		} else {
			alert("Error during AJAX call. Please try again");
		}
	}
}

// This function gets the data and set it to chat text area!!

function handleServerResponseChat() {
	if (xmlhttpChat.readyState == 4) {
		if (xmlhttpChat.status == 200) {
			textarea = document.getElementById("chatArea");

			if(textarea.value != xmlhttpChat.responseText) {
				console.log(textarea.value);
			textarea.values = "";
			console.log(xmlhttpChat.responseText);
			textarea.value = xmlhttpChat.responseText;
			textarea.scrollTop = textarea.scrollHeight;
			}
			//

		} else {
			alert("Error during AJAX call. Please try again");
		}
	}
}
function autosubmit() {
	window.setInterval(ajaxFunction, 5000);

}

function validateForm(obj) {
	//console.log(obj.name);
}