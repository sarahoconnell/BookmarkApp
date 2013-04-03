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
<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.min.css" rel="stylesheet">
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
          <a class="brand" href="dashboard">Dashboard</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a href="#">Home</a></li>
              <li><a href="#about">About</a></li>
              <li><a href="#contact">Contact</a></li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="#">Action</a></li>
                  <li><a href="#">Another action</a></li>
                  <li><a href="#">Something else here</a></li>
                  <li class="divider"></li>
                  <li class="nav-header">Nav header</li>
                  <li><a href="#">Separated link</a></li>
                  <li><a href="#">One more separated link</a></li>
                </ul>
              </li>
            </ul>
            <c:if test="${not empty error}">
				<div class="pull-right error">
					Your login attempt was not successful, try again.   
			              ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}<br>
				</div>
			</c:if>
			<security:authorize access="isAuthenticated()">
			   <div class="pull-right user-header">Hello, <security:authentication property="principal.username" />!
			   	   <a href="<c:url value="/j_spring_security_logout" />" class="user-header"> Logout</a>
			   </div>
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