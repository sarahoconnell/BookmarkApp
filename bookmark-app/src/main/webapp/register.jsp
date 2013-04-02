<jsp:include page="includes/header.jsp"/>
	   
	   ${error} 
	   ${message} 
	   
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
