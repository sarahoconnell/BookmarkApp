<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bookmark Application</title>
  <style>
      body {        
        padding-top: 60px;
        padding-bottom: 40px;
      }
    </style>
<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.min.css" rel="stylesheet">
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
          <a class="brand" href="#">Project name</a>
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
            
            <c:if test="${empty user.name}">
			     <form class="navbar-form pull-right">
	              <input class="span2" type="text" placeholder="Email">
	              <input class="span2" type="password" placeholder="Password">
	              <button type="submit" class="btn">Sign in</button>
	            </form>
			</c:if>
			<c:if test="${not empty user.name}">
			   <div class="pull-right" style="font-color:red">
			     Hello, ${user.name}!
			     <!-- TODO MAKE THIS GOOD -->
			   </div>
			</c:if>
                
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container">

    
      <p>

	     <h1>Bookmark Application</h1>
		Current bookmark items: 
		<c:forEach items="${bookmarks}" var="bookmark" varStatus="row">
			${bookmark.id} - ${bookmark.text} - ${bookmark.done}
			<form action="close.html" method="post">
				<input name="bookmarkId" value="${bookmark.id}" type="hidden">
				<input type="submit" value="Close">
			</form> 
			<form action="open.html" method="post">
				<input name="bookmarkId" value="${bookmark.id}" type="hidden">
				<input type="submit" value="Open">
			</form> 
		</c:forEach>
		<form action="create.html" method="post">
		    <input class="span2" type="text" name="text"  placeholder="bookmark">
			<input type="submit" value="Create" class="btn">
		</form>
		
		${user.id} 
		${user.name} 
		${user.password} 
		${user.twitterId} 
	</p>

      <hr>

      <footer>
        <p>&copy; Company 2013</p>
      </footer>


 </div> <!-- /container -->
	
	
<script src="http://code.jquery.com/jquery.js"></script>
<script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/js/bootstrap.min.js"></script>
</body>
</html>