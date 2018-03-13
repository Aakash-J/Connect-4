

function OnCloseWindows(win)
{
alert("Any message" );
}
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
	
// This function calls java servlet to sends information about the game like : username,color,gameid etc.

	function ajaxFunction(obj) {

		//alert(obj.id);

		if (xmlhttp) {
			
			xmlhttp.open("POST", "GameServlet.do", true); 
			xmlhttp.onreadystatechange = handleServerResponse;
			xmlhttp.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded');
			xmlhttp.send("column=" + obj.id + "&userName="
					+ document.getElementById("uname").value + "&color="
					+ document.getElementById("color").value + "&gameId="
					+ document.getElementById("gameId").value);

		}
	}
	
// This function calls a java servlet that get the data to draw board on front-end.

	function getBoard(obj) {

		

		if (xmlhttp) {
		
			xmlhttp.open("POST", "BoardServlet.do", true); 
			xmlhttp.onreadystatechange = handleServerResponse;
			xmlhttp.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded');
			xmlhttp.send("gameId=" + document.getElementById("gameId").value);

		}
	}
	
// This function draws the board and check for turn,winning condition etc.

	function handleServerResponse() {
		if (xmlhttp.readyState == 4) {
			if (xmlhttp.status == 200) {
				//alert(xmlhttp.responseText);
				uname = document.getElementById("uname").value;
				turn = (xmlhttp.responseText).split("@");
				if((turn[0]).trim() != "Won")
				data = (turn[0]).split("~");
				else
				data = (turn[2]).split("~");
				//console.log(turn[1]);
				//document.getElementById("vs").value = uname + "vs" + turn[1];
				if (turn[1].trim() != document.getElementById("uname").value) {
					document.getElementById("turn").value = "Not Your Turn"
					document.addEventListener("click", handler, true);
					document.getElementById("game-board").style.cursor = "not-allowed";
				} else {
					document.getElementById("turn").value = "It's Your Turn"
					document.removeEventListener('click', handler, true);
					document.getElementById("game-board").style.cursor = "pointer";
				}
				if ((turn[0]).trim() == "Won") {
					document.getElementById("turn").value = turn[1] + "Won"
					
					document.addEventListener("click", handler, true);
					document.getElementById("game-board").style.cursor = "not-allowed";
					
					

				} else if (turn[1].trim() == "draw")
					document.getElementById("turn").value = "Match Draw";
				//console.log(data);
				for (i = 0; i < data.length-1; i++) {
					cord = data[i];
					coord = cord.split(",");
					var color = document.getElementById("color").value;
					//  alert(cord);
					if (coord[2] == 'R')
						color = "red";
					else
						color = "yellow";
					document.querySelector(
							'#column-' + coord[1] + ' .row-' + coord[0]
									+ ' circle').setAttribute('class', color);
				}
				if ((turn[0]).trim() == "Won") {
					return;
				}

				// document.myForm.cha.value=xmlhttp.responseText; //Update the HTML Form element
			} else {
				alert("Error during AJAX call. Please try again");
			}
		}
	}

	//blocks the user from click on a piece on board. 

	function handler(e) {
		e.stopPropagation();
		e.preventDefault();
	}

	function getColumn(obj) {

		// alert(obj.id);
		ajaxFunction(obj);

	}
	function autosubmit() {

		window.setInterval(getBoard, 2000);
	}