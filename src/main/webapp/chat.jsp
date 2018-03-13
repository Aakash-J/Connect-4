<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<%
	if (session.getAttribute("username") != null) {
%>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<meta name="description" content="">
<meta name="author" content="">
<title>Chat</title>

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom fonts for this template -->
<link href="vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- Custom styles for this template -->

<script src="js/chat.js">
</script>


<style type="text/css">
#outOf {
	width: 40px;
	text-align: center;
}

.card-body {
	height: 310px;
	overflow-y: scroll;
}

.a {
	width: 120px;
}

.col-sm-6 b {
	overflow-x: hidden;
	overflow-y: hidden;
}
</style>

</head>

<body class="bg-warning" onload="autosubmit()">

	<div class="container">
			


		<div class="row">
		<div class="col-sm-12">
			
			<div class="card-header" style="text-align: center">
						<font size="4" color="green">Have fun with connect4</font><br />
						
					</div>
				
				
			</div>
		
		</div>

		<div class="row">
		
			<div class="col-sm-2">
			<div class="card mx-auto mt-5" width="900">
			<div class="card-header">
						<font size="4" color="green">Leaders Board </font><br />
						<br />
						<div id="topPlayer">
						
						</div>
					</div>
				
					</div>
			</div>

			<div class="col-sm-4 b">
				<div class="card mx-auto mt-5" width="900">
					<div class="card-header">

						<div style="text-align: center;">
							UserId: <font size="3" color="green"><%=session.getAttribute("username")%></font>
							<form action="/logout.do" method="post">
								<input type="submit" name="btnVal" value="Logout"
									class="btn btn-primary mx-auto mt-3">


							</form>
						</div>
					</div>
					<div class="card-body">

						<div class="form-group">
							<textarea class="form-control" readonly rows="6"
								style="max-height: 400px; min-height: 100px; resize: none"
								name="chatArea" id="chatArea" required></textarea>
						</div>


						<input type="text" id="textData" class="form-control"
							placeholder="Enter Chat"> <input type="button"
							name="btnVal" value="Send" onclick="return xmlhttpChatInsert();"
							class="btn btn-primary mx-auto mt-3">

					</div>

				</div>
			</div>
			<div class="col-sm-2">
				<div class="card mx-auto mt-5" height="900">
					<div class="card-header">Challenge</div>
					<div class="card-header">
						<font size="2" color="green">Accept
							Challenge </font>
					</div>
					<div class="card-body">
						<div class="form-group" id="challenges"></div>
					</div>
				</div>
			</div>
			<div class="col-sm-2">
				<div class="card mx-auto mt-5" height="900">
				<div class="card-header">Online Users</div>
					<div class="card-header">
						<font size="2" color="green">Click to
							Challenge </font>
					</div>
					<div class="card-body">
						<div class="form-group" id="onlineUsers"></div>
						<input type="hidden" name="userName" id="uname"
							value="<%=session.getAttribute("username")%>" />
					</div>
				</div>
			</div>
			<div class="col-sm-2">
				<div class="card mx-auto mt-5" height="900">
				<div class="card-header">Spectate Game</div>
					<div class="card-header">
						<font size="2" color="green">Click to
							Spectate </font>
					</div>
					<div class="card-body">
						<div class="form-group" id="Spectate"></div>
						<input type="hidden" name="userName" id="uname"
							value="<%=session.getAttribute("username")%>" />
					</div>
				</div>
			</div>



		</div>

		<!-- Bootstrap core JavaScript -->
		<script src="vendor/jquery/jquery.min.js"></script>
		<script src="vendor/popper/popper.min.js"></script>
		<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
		<%
			} else {
		%>

		<p>Please Login !!!!</p>


		<%
			}
		%>
	</div>
</body>

</html>

