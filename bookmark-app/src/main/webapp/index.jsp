<jsp:include page="includes/header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	    <c:if test="${not empty error}">
		
		<div class="alert alert-block alert-error fade in">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <h4 class="alert-heading"> ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</h4>
            	<p>	Your login attempt was not successful, try again.   
			 </p>
            
          </div>
		</c:if>
			
			
       <div class="hero-unit">
        <h1>Bookmark App!</h1>  
        <p>This application allows you to manage your favourite bookmarks.</p>
        <p><a href="registerUser" class="btn btn-primary btn-large">Register »</a>&nbsp; <a href="showPublicBoards" class="btn btn-primary btn-large">Public Boards »</a></p>
      </div>
      
 <jsp:include page="includes/footer.jsp"/>
   