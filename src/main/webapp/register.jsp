<%@page import="java.util.*"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <meta name="description" content="">
    <meta name="author" content="">
    <title>Connect4</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">


  </head>

  <body class="bg-dark">

    <div class="container">

      <div class="card card-register mx-auto mt-5">
        <div class="card-header">
          Register an Account
        </div>
        <div class="card-body">
          <form action="/register.do" method="post">
            <div class="form-group">
              <div class="form-row">
                <div class="col-md-6">
                  <label for="UserId">UserId</label>
                  <input type="text" maxlength="8" class="form-control" name="UserId"  placeholder="Enter UserId">
                </div>
                <div class="col-md-6">
                  <label for="Password">Password</label>
                  <input type="password"  maxlength="8" class="form-control" name="Password"  placeholder="Enter Password">
                </div>
              </div>
            </div>
              <input type="submit" value= "Register" class="btn btn-primary btn-block">
          </form>
          <div class="text-center">
          
          <% if(request.getAttribute("msg")!=null) { %>
           <label><%=request.getAttribute("msg")%></label>
           
           <% }%> 
            <a class="d-block small mt-3" href="login.jsp">Login Page</a>
           
          </div>
         
          
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/popper/popper.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

  </body>

</html>
