<jsp:include page="includes/header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

  <h1>${board.name}</h1>
 
   <a href="#linkModal" 
      data-gotolink="board" 
      data-boardid="${board.id}" 
      role="button" 
      class="open-linkModal btn btn-success btn-large" 
      data-toggle="modal">
      <i class="icon-heart icon-white"></i> Add Link</a>
   
   <br/> <br/>
  <div class="row-fluid">
    <ul class="thumbnails">
    <c:forEach items="${links}" var="link" varStatus="row">
	  <li class="span3">	  
	  <div class="thumbnail">
	  		<div class="btn-group">
         
		  <a class="btn btn-info long" 
		     href="${link.url}" 
		     target="window">
		     <i class="icon-heart icon-white"></i> ${link.name}</a>

		  <a class="btn btn-info dropdown-toggle" 
		     data-toggle="dropdown" 
		     href="#"><span class="caret"></span></a>

		  <ul class="dropdown-menu">
		    <li>
		       <a href="#linkModal" 
		           data-gotolink="board" 
				   data-boardid="${board.id}" 
				   data-id="${link.id}" 
				   data-name="${link.name}" 
				   data-url="${link.url}" 
				   data-desc="${link.description}" 
				   data-toggle="modal" 
				   class="open-linkModal">
		          <i class="icon-pencil"></i> Edit</a>
		    </li>

		    <li>
		     <a href="#deleteLinkModal" 
               data-linkid="${link.id}" 
               role="button"
               class="open-deleteLinkModal"
               data-toggle="modal"><i class="icon-trash"></i> Delete</a>               
		    </li>

		  </ul>

		</div>     
		<div class="caption">
		
          <a href="${link.url}" target="window">   
                 <c:choose>
      					<c:when test="${link.image != null}">
		      					<img data-src="holder.js/140x140"style="width: 140px; height: 140px;" src="data:image/jpg;base64,${link.imageData}">
		      			</c:when>
	      				<c:otherwise>
		    					<img data-src="holder.js/140x140"  alt="" style="width: 270px; height: 200px;" src="data:image/jpeg;base64,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" >	
	      				</c:otherwise>
	      			</c:choose>
			</a>
           <p>${link.description}</p>           
        </div>
      </div>
      </li>
  	 </c:forEach>
    </ul> 
  </div>

<jsp:include page="includes/footer.jsp"/>