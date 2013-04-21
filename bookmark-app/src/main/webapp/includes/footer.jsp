
<!--  Delete Board Modal -->
<div id="deleteBoardModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<form id="modal-form" action="deleteBoard" method="POST">
  	
	<div class="modal-header">
    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
    	<h3 id="myModalLabel">Delete Board</h3>
  	</div>
  	
  	<div class="modal-body">
      <p>You are about to delete a board, this procedure is irreversible.</p>
      <p>Do you want to proceed?</p>
      <input type="hidden" id="boardid" name="boardid"/>
    </div>
    <div class="modal-footer">
      <button class="btn btn-danger">Yes</button>
      <a href="javascript:$('#deleteBoardModal').modal('hide')" class="btn secondary">No</a>
    </div>
    </form>
</div>

<!--  Delete Link Modal -->
<div id="deleteLinkModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<form id="modal-form" action="deleteLink" method="POST">
  	
	<div class="modal-header">
    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
    	<h3 id="myModalLabel">Delete Link</h3>
  	</div>
  	
  	<div class="modal-body">
      <p>You are about to delete a link, this procedure is irreversible.</p>
      <p>Do you want to proceed?</p>
      <input type="hidden" id="linkid" name="linkid"/>
    </div>
    <div class="modal-footer">
      <button class="btn btn-danger">Yes</button>
      <a href="javascript:$('#deleteLinkModal').modal('hide')" class="btn secondary">No</a>
    </div>
    </form>
</div>

<!--   User Details  Modal -->
<div id="userBoardsModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
    	<h3 id="myModalLabel">User Details</h3>
  	</div>
  	<div id="userboards" class="modal-body">
      
    </div>
    <div class="modal-footer">
      <a href="javascript:$('#userBoardsModal').modal('hide')" class="btn secondary">Close</a>
    </div>
</div>


<!--  Delete User Modal -->
<div id="deleteUserModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<form id="modal-form" action="removeUser" method="POST">
  	
	<div class="modal-header">
    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
    	<h3 id="myModalLabel">Delete User</h3>
  	</div>
  	
  	<div class="modal-body">
      <p>You are about to delete a user, this procedure is irreversible.</p>
      <p>Do you want to proceed?</p>
      <input type="hidden" id="userId" name="userId"/>
    </div>
    <div class="modal-footer">
      <button class="btn btn-danger">Yes</button>
      <a href="javascript:$('#deleteUserModal').modal('hide')" class="btn secondary">No</a>
    </div>
    </form>
</div>


<!--  Lockout User Modal -->
<div id="lockoutUserModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
    	<h3 id="myModalLabel">Lockout User</h3>
  	</div>
  	
  	<form action="toggleUserEnable"  method="POST">
  	<div class="modal-body">
      <p>You are about to lockout this user.</p>
      <p>Do you want to proceed?</p>
      <input type="hidden" id="userId" name="userId"/>
      <input type="hidden" id="enabled" name="enabled"/>
    </div>
    <div class="modal-footer">
      <button class="btn btn-warning">Yes</button>
      <a href="javascript:$('#lockoutUserModal').modal('hide')" class="btn secondary">No</a>
    </div>
    </form>
</div>



<!--  Enable User Modal -->
<div id="enableUserModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
    	<h3 id="myModalLabel">Enable User</h3>
  	</div>
  	
  	<form action="toggleUserEnable" method="POST">
  	<div class="modal-body">
      <p>You are about to re-enable this user.</p>
      <p>Do you want to proceed?</p>
      <input type="hidden" id="userId" name="userId"/>
      <input type="hidden" id="enabled" name="enabled"/>
    </div>
    <div class="modal-footer">
      <button class="btn btn-warning">Yes</button>
      <a href="javascript:$('#enableUserModal').modal('hide')" class="btn secondary">No</a>
    </div>
    </form>
</div>


<!-- Link Modal -->
<div id="linkModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
    <h3 id="myModalLabel">Add Link</h3>
  </div>
  <form id="saveLinkForm" class="form-horizontal" action="createLink" method="post">
  <div class="modal-body">   
  	  <div class="alert alert-block alert-error fade in" id="formErrorsDivLink" style="display:none">   
      </div> 
	  <div class="control-group">
	    <label class="control-label required_marker" for="url">URL</label>
	    <div class="controls">
	      <input type="text" 
	             id="url" 
	             name="url" 
	             placeholder="http://awsomelink.here"
	             required="true"
	             />	
	             
	      <input type="hidden" id="boardId" name="boardId">
	      <input type="hidden" id="id" name="id" >
	      <input type="hidden" id="gotolink" name="gotolink">
	    </div>
	  </div>
	  <div class="control-group">
	    <label class="control-label" for="name">Name</label>
	    <div class="controls">
	      <input type="text" 
	             id="name" 
	             name="name" 
	             placeholder="Name" 
	             maxlength="20">
	    </div>
	  </div>
	  <div class="control-group">
	    <label class="control-label" for="description">Description</label>
	    <div class="controls">
	      <textarea id="description" name="description" placeholder="Description" rows="3"></textarea>	    </div>
	  </div>	
  </div>
  <div class="modal-footer">
   <img src="/bookmark-app/static/images/loading.gif" id="loading-indicator" style="display:none" />
    <button type="button"
            class="saveLink btn btn-primary" 
            id="saveLink" 
            name="saveLink">Save changes</button>
    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
  </div>
	</form>
</div>

<!-- Board Modal -->
<div id="boardModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
    <h3 id="myModalLabel">Create Board</h3>
  </div>
  <form id="saveBoardForm" class="form-horizontal" action="createBoard" method="post">
  <div class="modal-body">  
  	  <div class="alert alert-block alert-error fade in" id="formErrorsDivBoard" style="display:none">   
      </div>   
	  <div class="control-group">
	    <label class="control-label" for="name">Name</label>
	    <div class="controls">
	      <input type="text" id="name" name="name" placeholder="Name" maxlength="20">
	      <input type="hidden" id="id" name="id" >
	      <input type="text" id="img" name="img" value="icon-star">
	      <input type="hidden" id="ispublic" name="ispublic" value="false">
	    </div>
	  </div>
	  <div class="control-group">
	    <label class="control-label" for="ispublic">Public</label>
	    <div class="controls">
           <input type="checkbox" name="ispublicRadio" id="ispublicRadio" onclick="toggleIsPublic()">
	    </div>
	  </div>
	  <div class="control-group">
	    <label class="control-label" for="description">Description</label>
	    <div class="controls">
	      <textarea id="description" name="description" placeholder="Description" rows="3"></textarea>
	    </div>
	  </div>
	  <div align="center">
	  <section id="icons-web-app" class="row">
	 	
	 	<div class="span1">
		 	 <ul class="the-icons">
			 <li><i class="icon-star img_thumbnail selected" id="icon-star"></i></li>
		      <li><i class="icon-tag img_thumbnail" id="icon-tag"></i></li>
		      <li><i class="icon-tasks img_thumbnail" id="icon-thumbnail"></i></li>
		      <li><i class="icon-lightbulb img_thumbnail" id="icon-lightbulb"></i></li>
		      <li><i class="icon-stethoscope img_thumbnail" id="icon-stethoscope"></i></li>
		      <li><i class="icon-suitcase img_thumbnail" id="icon-suitcase"></i></li>
		      <li><i class="icon-bell-alt img_thumbnail" id="icon-bell-alt"></i></li>
		      <li><i class="icon-food img_thumbnail" id="icon-food""></i></li>
		      <li><i class="icon-fighter-jet img_thumbnail" id="icon-fighter-jet"></i></li>
		      <li><i class="icon-building img_thumbnail" id="icon-building"></i></li>
		      
			 </ul>
	 	</div>
	 	<div class="span1">
		 	 <ul class="the-icons">
		 	 <li><i class="icon-medkit img_thumbnail" id="icon-medkit"></i></li>
		      <li><i class="icon-bell-alt img_thumbnail" id="icon-bell-alt"></i></li>
		      <li><i class="icon-bolt img_thumbnail" id="icon-bolt"></i> </li>
		      <li><i class="icon-book img_thumbnail" id="icon-book"></i></li>
		      <li><i class="icon-desktop img_thumbnail" id="icon-desktop"></i> </li>
		       <li><i class="icon-mobile-phone img_thumbnail" id="icon-mobile-phone"></i> </li>
		      <li><i class="icon-github img_thumbnail" id="icon-github"></i> </li>
		      <li><i class="icon-bar-chart img_thumbnail" id="icon-bar-chart"></i> </li>
		      <li><i class="icon-calendar img_thumbnail" id="icon-calendar"></i></li>
		      <li><i class="icon-camera img_thumbnail" id="icon-camera"></i> </li>
		     
			 </ul>
	 	</div>
  		<div class="span1">
		    <ul class="the-icons">
		       <li><i class="icon-camera-retro img_thumbnail" id="icon-camera-retro"></i></li>
		      <li><i class="icon-certificate img_thumbnail" id="icon-certificate"></i></li>
		      <li><i class="icon-cloud img_thumbnail" id="icon-cloud"></i> </li>
		      <li><i class="icon-comment img_thumbnail" id="icon-comment"></i></li>
		      <li><i class="icon-inbox img_thumbnail" id="icon-inbox"></i> </li>
		      <li><i class="icon-home img_thumbnail" id="icon-home"></i> </li>
		      <li><i class="icon-globe img_thumbnail" id="icon-globe"></i> </li>
		      <li><i class="icon-flag img_thumbnail" id="icon-flag"></i></li>
		      <li><i class="icon-picture img_thumbnail" id="icon-picture"></i></li>
		      <li><i class="icon-pushpin img_thumbnail" id="icon-pushpin"></i></li>
		      
		    </ul>
		  </div>
		  <div class="span1">
		  <ul class="the-icons">
		      <li><i class="icon-cog img_thumbnail" id="icon-cog"></i></li>
		      <li><i class="icon-cogs img_thumbnail" id="icon-cogs"></i></li>
		      <li><i class="icon-comment img_thumbnail" id="icon-comment"></i></li>
		      <li><i class="icon-trophy img_thumbnail" id="icon-trophy"></i></li>
		      <li><i class="icon-question-sign img_thumbnail" id="icon-question-sign"></i></li>
		      <li><i class="icon-random img_thumbnail" id="icon-random"></i></li>
		      <li><i class="icon-credit-card img_thumbnail" id="icon-credit-card"></i></li>
		      <li><i class="icon-download img_thumbnail" id="icon-download"></i></li>
		      <li><i class="icon-key img_thumbnail" id="icon-key"></i></li>
		      <li><i class="icon-road img_thumbnail" id="icon-road"></i></li>
		      
			     
		  </ul></div>
		  <div class="span1">
		    <ul class="the-icons">
		     <li><i class="icon-google-plus img_thumbnail" id="icon-google-plus"></i></li>
		      <li><i class="icon-beaker img_thumbnail" id="icon-beaker"></i></li>
		      <li><i class="icon-film img_thumbnail" id="icon-film"></i></li>
		      <li><i class="icon-fire img_thumbnail" id="icon-fire"></i></li>
		      <li><i class="icon-gift img_thumbnail" id="icon-gift"></i></li>
		      <li><i class="icon-glass img_thumbnail" id="icon-glass"></i></li>
		      <li><i class="icon-group img_thumbnail" id="icon-group"></i></li>
		      <li><i class="icon-headphones img_thumbnail" id="icon-headphones"></i></li>
		      <li><i class="icon-heart img_thumbnail" id="icon-heart"></i></li>
		      <li><i class="icon-home img_thumbnail" id="icon-home"></i></li>
		      
		          </ul>
		  </div>
		  
		</section>
	  </div>
  </div>
  <div class="modal-footer">
     <button type="button"
             class="saveBoard btn btn-primary" 
             id="saveBoard" 
             name="saveBoard">Save changes</button>
    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
    
  </div>
	</form>
</div>



      <hr>

      <footer>
        <p>&copy; Sarah 2013</p>
      </footer>

 
 </div> <!-- /container -->
	
	
<script src="http://code.jquery.com/jquery.js"></script>
<script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/js/bootstrap.min.js"></script>
<script src="/bookmark-app/static/js/jquery.isotope.min.js"></script>
<script src="/bookmark-app/static/js/script.js"></script>

</body>
</html>