<jsp:include page="includes/header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<h3>Public Boards</h3>
  <div class="row-fluid">
    <ul class="thumbnails">
    <c:forEach items="${publicBoards}" var="board" varStatus="row">
      
	  <li class="span3">	  
	  <div class="thumbnail">	  
         <div class="btn-group">         
		  <a class="btn btn-info long" 
		     href="viewPublicBoard?boardid=${board.id}">
		     <i class="icon-folder-open icon-white"></i> ${board.name}</a>
		</div>     
         <div class="caption">
          <a href="viewPublicBoard?boardid=${board.id}"><i class="${board.img} zoomed"></i></a>  
          <p>${board.description}</p>   
        </div> 
      </div>
      </li>
  	 </c:forEach>
    </ul> 
  </div>
  
<jsp:include page="includes/footer.jsp"/>

