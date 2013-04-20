<jsp:include page="includes/header.jsp"/>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	    <c:if test="${not empty error}">
	   	<div class="alert alert-block alert-error fade in">
            <button type="button" class="close" data-dismiss="alert">×</button>
           <p>${error}</p>
        </div>
        </c:if>
	 	
	 	
	 	 <c:if test="${not empty message}">
	   	<div class="alert alert-block  alert-success fade in">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <p>${message}</p>
        </div>
        </c:if>
	
	   
      <form class="form-horizontal" action="createUser" method="post">
	  <div class="control-group">
	    <label class="control-label" for="name">Name</label>
	    <div class="controls">
	      <input type="text" id="name" name="name" placeholder="Name">
	    </div>
	  </div>
	  <div class="control-group">
	    <label class="control-label" for="password">Password</label>
	    <div class="controls">
	      <input type="password" id="password" name="password" placeholder="Password">
	    </div>
	  </div>	  
	  <div class="control-group">
	    <label class="control-label" for="password2">Confirm Password</label>
	    <div class="controls">
	      <input type="password" id="password2" name="password2" placeholder="Confirm Password">
	    </div>
	  </div>
	  <div class="control-group">
	    <label class="control-label" for="twitterId">TwitterId</label>
	    <div class="controls">
	      <input type="text" id="twitterId" name="twitterId" placeholder="TwitterId">
	    </div>
	  </div>
	  <div class="control-group">
	    <div class="controls">
	      <button type="submit" class="btn">Register</button>
	    </div>
	  </div>
	</form>
      
   <jsp:include page="includes/footer.jsp"/>
