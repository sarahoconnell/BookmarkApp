<jsp:include page="includes/header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

  <h1>Bookmark Application</h1>
 
   <a href="#linkModal" role="button" class="btn" data-toggle="modal">Add Link</a>&nbsp;
   <a href="#boardModal" role="button" class="btn" data-toggle="modal">Create Board</a>

  <h4>Boards</h4>
  <c:forEach items="${boards}" var="board" varStatus="row">
		${board.name} - ${board.description}	
  </c:forEach>


<!-- Link Modal -->
<div id="linkModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
    <h3 id="myModalLabel">Add Link</h3>
  </div>
  <form class="form-horizontal" action="createLink" method="post">
  <div class="modal-body">    
	  <div class="control-group">
	    <label class="control-label" for="name">URL</label>
	    <div class="controls">
	      <input type="text" id="url" name="url" placeholder="http://awsomelink.here">
	    </div>
	  </div>
	  <div class="control-group">
	    <label class="control-label" for="password">Description</label>
	    <div class="controls">
	      <input type="password" id="description" name="description" placeholder="Description">
	    </div>
	  </div>	
	  <div class="control-group">
	    <label class="control-label" for="boardId">Board</label>
	    <div class="controls">
	     <select id="boardId" name="boardId">
	        <c:forEach items="${boards}" var="board" varStatus="row">
		 		<option value="${board.id}">${board.name}</option>	
  			</c:forEach>
		</select>
	    </div>
	  </div>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
    <button class="btn btn-primary">Save changes</button>
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
	      <input type="hidden" id="userId" name="userId" value="${user.id}">
	    </div>
	  </div>
	  <div class="control-group">
	    <label class="control-label" for="password">Description</label>
	    <div class="controls">
	      <input type="password" id="description" name="description" placeholder="Description">
	    </div>
	  </div>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
    <button class="btn btn-primary">Save changes</button>
  </div>
	</form>
</div>

<jsp:include page="includes/footer.jsp"/>

