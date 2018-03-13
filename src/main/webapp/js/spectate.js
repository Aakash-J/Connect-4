


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

	function getBoard(obj) {

		//  alert(obj.id);
       document.addEventListener("click", handler, true);
       document.getElementById("p1vsp2").value = sessionStorage.vs;
		if (xmlhttp) {

			xmlhttp.open("POST", "BoardServlet.do", true); //getname will be the servlet name
			xmlhttp.onreadystatechange = handleServerResponse;
			xmlhttp.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded');
			console.log("s"+ sessionStorage.gameId);
			xmlhttp.send("gameId=" + sessionStorage.gameId);

		}
	}

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


			} else {
				alert("Error during AJAX call. Please try again");
			}
		}
	}


	function handler(e) {
		e.stopPropagation();
		e.preventDefault();
	}


	function autosubmit() {

		window.setInterval(getBoard, 1000);
	}