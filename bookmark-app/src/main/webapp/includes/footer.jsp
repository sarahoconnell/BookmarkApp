
<!--  Delete User Modal -->
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
	<form id="modal-form" action="removeUser">
  	
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
  	
  	<form action="toggleUserEnable">
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
  	
  	<form action="toggleUserEnable">
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
  <form class="form-horizontal" action="createLink" method="post">
  <div class="modal-body">    
	  <div class="control-group">
	    <label class="control-label" for="url">URL</label>
	    <div class="controls">
	      <input type="text" id="url" name="url" placeholder="http://awsomelink.here">
	      <input type="hidden" id="boardId" name="boardId">
	      <input type="hidden" id="id" name="id" >
	      <input type="hidden" id="gotolink" name="gotolink">
	    </div>
	  </div>
	  <div class="control-group">
	    <label class="control-label" for="name">Name</label>
	    <div class="controls">
	      <input type="text" id="name" name="name" placeholder="Name">
	    </div>
	  </div>
	  <div class="control-group">
	    <label class="control-label" for="description">Description</label>
	    <div class="controls">
	      <textarea id="description" name="description" placeholder="Description" rows="3"></textarea>	    </div>
	  </div>	
  </div>
  <div class="modal-footer">
    <button class="btn btn-primary">Save changes</button>
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
  <form class="form-horizontal" action="createBoard" method="post">
  <div class="modal-body">    
	  <div class="control-group">
	    <label class="control-label" for="name">Name</label>
	    <div class="controls">
	      <input type="text" id="name" name="name" placeholder="Name">
	      <input type="hidden" id="id" name="id" >
	    </div>
	  </div>
	  <div class="control-group">
	    <label class="control-label" for="description">Description</label>
	    <div class="controls">
	      <textarea id="description" name="description" placeholder="Description" rows="3"></textarea>
	    </div>
	  </div>
  </div>
  <div class="modal-footer">
    <button class="btn btn-primary">Save changes</button>
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
<script>
$(document).on("click", ".open-linkModal", function () {
    var boardId = $(this).data('boardid');
    $(".modal-body #boardId").val( boardId );

    var id = $(this).data('id');
    $(".modal-body #id").val( id );
    
    var name = $(this).data('name');
    $(".modal-body #name").val( name );
    
    var description = $(this).data('desc');
    $(".modal-body #description").val( description );
    
    var url = $(this).data('url');
    $(".modal-body #url").val( url );
    
    var gotolink = $(this).data('gotolink');
    $(".modal-body #gotolink").val( gotolink );
    
    $('#linkModal').modal('show');
});




$(document).on("click", ".open-deleteUserModal", function () {
     var userId = $(this).data('userid');
     $(".modal-body #userId").val( userId );
});

$(document).on("click", ".open-lockoutUserModal", function () {
    var userId = $(this).data('userid');
    var enabled = $(this).data('enabled');
    $(".modal-body #userId").val( userId );
    $(".modal-body #enabled").val( enabled );
});

$(document).on("click", ".open-enableUserModal", function () {
    var userId = $(this).data('userid');
    var enabled = $(this).data('enabled');
    $(".modal-body #userId").val( userId );
    $(".modal-body #enabled").val( enabled );
});


$(document).on("click", ".open-boardModal", function () {
    var id = $(this).data('id');
    $(".modal-body #id").val( id );
   
    var name = $(this).data('name');
    $(".modal-body #name").val( name );
    
    var description = $(this).data('desc');
    $(".modal-body #description").val( description );
    
    $('#boardModal').modal('show');
});
</script>
</body>
</html>