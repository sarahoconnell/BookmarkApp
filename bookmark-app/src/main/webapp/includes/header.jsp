<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Developer Pinterest</title>
  <style>
      body {        
        padding-top: 60px;
        padding-bottom: 40px;
      }
    </style>
<link href='http://fonts.googleapis.com/css?family=Open+Sans:600' rel='stylesheet' type='text/css'>
<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.min.css" rel="stylesheet">
<link href="//netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css" rel="stylesheet">
<link href="/bookmark-app/static/css/style.css" rel="stylesheet">
</head>
<body>
    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="dashboard"><i class="icon-folder-open icon-white"></i>&nbsp;Dashboard</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
          	 <security:authorize ifAnyGranted="ROLE_ADMIN">
      		 <li><a href="admin">Manage Users</a></li>
      		 </security:authorize> 
      		 
		     <li><a href="showPublicBoards">Public Boards</a></li>
         	
            </ul>
      		<security:authorize access="isAuthenticated()">
			<p class="navbar-text pull-right">
             Hello, <a href="#" class="navbar-link"><security:authentication property="principal.username" /></a>
            | <a href="<c:url value="/j_spring_security_logout" />" class="user-header"> Logout</a>
			</p>
			</security:authorize>
			
			
			<security:authorize access="! isAuthenticated()">
			    <form class="navbar-form pull-right" action="<c:url value='j_spring_security_check' />" method='POST'>
	              <input class="span2" type="text" placeholder="Name" name="j_username">
	              <input class="span2" type="password" placeholder="Password" name="j_password">
	              <button type="submit" name="submit"  class="btn">Sign in</button>
	            </form>
			</security:authorize>
			
		
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container">