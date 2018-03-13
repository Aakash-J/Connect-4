<%@page import="java.util.*"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<% if (session.getAttribute("username") != null) { %>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Connect 4</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom fonts for this template -->
<link href="vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">



<link rel="stylesheet" href="css/style.css">

<script src="js/spectate.js">


</script>


</head>

<body onload="autosubmit()">
	<div class="container">
		<input type="hidden" name="uName" STYLE="color: green"
			value="<%=session.getAttribute("username")%>" /> <input type="hidden"
			name="uName" STYLE="color: green" value="VS" /> <input type="hidden"
			name="vs" STYLE="color: green" id="vs"
			value="<%=session.getAttribute("P2")%>" /> <input type="text"
			name="turn" STYLE="color: red" id="turn" value="" />
			
			<input type="text" id="p1vsp2" STYLE="color: green" />

	</div>
	<div id="game-board">

		<div class="column" id="column-0" data-x="0"
			onclick="getColumn(this);">
			<svg height="84" width="84" class="row-5">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-4">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-3">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-2">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-1">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-0">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
		</div>
		<div class="column" id="column-1" data-x="1"
			onclick="getColumn(this);">
			<svg height="84" width="84" class="row-5">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-4">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-3">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-2">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-1">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-0">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
		</div>
		<div class="column" id="column-2" data-x="2"
			onclick="getColumn(this);">
			<svg height="84" width="84" class="row-5">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-4">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-3">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-2">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-1">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-0">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
		</div>
		<div class="column" id="column-3" data-x="3"
			onclick="getColumn(this);">
			<svg height="84" width="84" class="row-5">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-4">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-3">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-2">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-1">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-0">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
		</div>
		<div class="column" id="column-4" data-x="4"
			onclick="getColumn(this);">
			<svg height="84" width="84" class="row-5">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-4">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-3">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-2">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-1">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-0">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
		</div>
		<div class="column" id="column-5" data-x="5"
			onclick="getColumn(this);">
			<svg height="84" width="84" class="row-5">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-4">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-3">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-2">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-1">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-0">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
		</div>
		<div class="column" id="column-6" data-x="6"
			onclick="getColumn(this);">
			<svg height="84" width="84" class="row-5">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-4">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-3">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-2">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-1">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
			<svg height="84" width="84" class="row-0">
		  <circle cx="42" cy="42" r="35" stroke="#0B4E72" stroke-width="3"
					class="free" />
		</svg>
		</div>
	</div>
	<h2></h2>


	<input type="hidden" name="color" id="color"
		value="<%=session.getAttribute("Color")%>" />
	<input type="hidden" name="userName" id="uname"
		value="<%=session.getAttribute("username")%>" />
	<input type="hidden" name="gameId" id="gameId"
		value="<%=session.getAttribute("gameId")%>" />


<%} else { %>

 <p> Please Login !!!! </p>

   
<% } %>

</body>

</html>


