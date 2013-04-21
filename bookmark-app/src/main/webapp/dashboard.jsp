<jsp:include page="includes/header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<a href="#boardModal" 
   role="button" 
   class="btn btn-success btn-large open-boardModal" 
   data-toggle="modal"
   data-id="NEW" >
   <i class="icon-folder-close icon-white"></i> Create Board</a>
 
<br>
<br>

<h3>Your Public Boards</h3>
  <div class="row-fluid">
    <ul class="thumbnails">
    <c:forEach items="${publicBoards}" var="board" varStatus="row">
      
	  <li class="span3">	  
	  <div class="thumbnail">	  
         <div class="btn-group">         
		  <a class="btn btn-info long" 
		     href="viewBoard?boardid=${board.id}">
		     <i class="icon-folder-open icon-white"></i> ${board.name}</a>
		     
		  <a class="btn btn-info dropdown-toggle" 
		     data-toggle="dropdown" 
		     href="#"><span class="caret"></span></a>
		       
		  <ul class="dropdown-menu">
		    <li>
		       <a href="#boardModal" 
		          data-id="${board.id}" 
		          data-name="${board.name}" 
		          data-desc="${board.description}" 
		          data-img="${board.img}" 
		          data-ispublic="${board.isPublic}" 
		          data-toggle="modal" 
		          class="open-boardModal">
		          <i class="icon-pencil"></i> Edit</a>
		    </li>
		     <li>
		     <a href="#deleteBoardModal" 
               data-boardid="${board.id}" 
               role="button"
               class="open-deleteBoardModal"
               data-toggle="modal"><i class="icon-trash"></i> Delete</a>               
		    </li>
		    <li class="divider"></li>
		    <li>
		       <a href="#linkModal" 
		          data-gotolink="dashboard" 
		          data-boardid="${board.id}" 
		          data-toggle="modal" 
		          class="open-linkModal">
		          <i class="icon-heart"></i> Add Link</a>
		    </li>
		  </ul>		  
		</div>     
         <div class="caption">
          <a href="viewBoard?boardid=${board.id}"><i class="${board.img} zoomed"></i></a>   
        </div> 
      </div>
      </li>
  	 </c:forEach>
    </ul> 
  </div>
  
  
<hr>

<h3>Your Private Boards</h3>
  <div class="row-fluid">
    <ul class="thumbnails">
    <c:forEach items="${privateBoards}" var="board" varStatus="row">
      
	  <li class="span3">	  
	  <div class="thumbnail">	  
         <div class="btn-group">         
		  <a class="btn btn-warning long" 
		     href="viewBoard?boardid=${board.id}">
		     <i class="icon-folder-open icon-white"></i> ${board.name}</a>
		     
		  <a class="btn btn-warning dropdown-toggle" 
		     data-toggle="dropdown" 
		     href="#"><span class="caret"></span></a>
		       
		  <ul class="dropdown-menu">
		    <li>
		       <a href="#boardModal" 
		          data-id="${board.id}" 
		          data-name="${board.name}" 
		          data-desc="${board.description}" 
		          data-img="${board.img}" 
		          data-ispublic="${board.isPublic}" 
		          data-toggle="modal" 
		          class="open-boardModal">
		          <i class="icon-pencil"></i> Edit</a>
		    </li>
		     <li>
		     <a href="#deleteBoardModal" 
               data-boardid="${board.id}" 
               role="button"
               class="open-deleteBoardModal"
               data-toggle="modal"><i class="icon-trash"></i> Delete</a>               
		    </li>
		    <li class="divider"></li>
		    <li>
		       <a href="#linkModal" 
		          data-gotolink="dashboard" 
		          data-boardid="${board.id}" 
		          data-toggle="modal" 
		          class="open-linkModal">
		          <i class="icon-heart"></i> Add Link</a>
		    </li>
		  </ul>		  
		</div>     
         <div class="caption">
          <a href="viewBoard?boardid=${board.id}"><i class="${board.img} zoomed"></i></a>   
        </div> 
      </div>
      </li>
  	 </c:forEach>
    </ul> 
  </div>

<jsp:include page="includes/footer.jsp"/>

