<jsp:include page="includes/header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>  

<h1> User Management</h1>
 	 
<table class="table table-striped">
   <thead>
     <tr>
       <th>User Name</th>
       <th>Twitter ID</th>
       <th>Enabled</th>
       <th>Actions</th>
     </tr>
   </thead>
   <tbody>
   <c:forEach items="${users}" var="user" varStatus="row">
     <tr>
       <td>${user.name}</td>
       <td>
       <a href="http://twitter.com/${user.twitterId}" target="_blank">${user.twitterId}</a></td>
       <td>
       <c:choose>
			<c:when test="${user.enabled == true}">
				<i class="icon-ok"></i>
			</c:when>
			<c:otherwise>
				<i class="icon-remove"></i>	
			</c:otherwise>
	   </c:choose>
       </td>
       <td>
       	<button class="btn btn-info" onClick="getBoards('${user.id}' , '${user.name}')">
       	   Details
	    </button> 
        <c:if test="${user.id != currentUser.id && user.id != '001'}">
            <a href="#deleteUserModal" 
               data-userid="${user.id}" 
               role="button"
               class="open-deleteUserModal  btn btn-danger"
               data-toggle="modal">Delete</a>
            <c:choose>
				<c:when test="${user.enabled == true}">
					<a href="#lockoutUserModal" 
			         	data-userid="${user.id}"
			         	data-enabled="false"
			         	role="button"
			         	class="open-lockoutUserModal btn btn-warning"
			         	data-toggle="modal">Lockout</a>				
			  	</c:when>
			 	<c:otherwise>			 
					 <a href="#enableUserModal" 
				        data-userid="${user.id}" 
						data-enabled="true"
						role="button" 
						class="open-enableUserModal btn btn-warning" 
						data-toggle="modal">Enable</a>					
				</c:otherwise>
			 </c:choose>
        </c:if>
       </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<jsp:include page="includes/footer.jsp"/>