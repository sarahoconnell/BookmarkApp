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
         
		  <a class="btn btn-inverse" 
		     href="${link.url}" target="window">
		     <i class="icon-heart icon-white"></i> ${link.name}</a>

		  <a class="btn btn-inverse dropdown-toggle" 
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
		       <a href="deleteLink?linkid=${link.id}">
		          <i class="icon-trash"></i> Delete</a>
		    </li>

		  </ul>

		</div>     
		<div class="caption">
                 <c:choose>
      					<c:when test="${link.image != null}">

		      					<!-- img data-src="holder.js/140x140" class="img-circle" alt="140x140" style="width: 140px; height: 140px;" src="images/${link.image}">
		      					-->
		      					<img data-src="holder.js/140x140"style="width: 140px; height: 140px;" src="data:image/jpg;base64,${link.imageData}">
		      			</c:when>
	      				<c:otherwise>
		    					<img data-src="holder.js/140x140" class="img-circle" alt="140x140" style="width: 140px; height: 140px;" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIwAAACMCAYAAACuwEE+AAAErUlEQVR4Xu3YwStscRjG8d8QQnZEFkqyY6NE/n0rlOxkS1ZqrCiFe/udOtPcue6YJ889Gc93Vtz7eo/3eT/9zjl6/X7/V+FDAhMm0APMhElR1iQAGCBICQBGiotiwGBASgAwUlwUAwYDUgKAkeKiGDAYkBIAjBQXxYDBgJQAYKS4KAYMBqQEACPFRTFgMCAlABgpLooBgwEpAcBIcVEMGAxICQBGiotiwGBASgAwUlwUAwYDUgKAkeKiGDAYkBIAjBQXxYDBgJQAYKS4KAYMBqQEACPFRTFgMCAlABgpLooBgwEpAcBIcVEMGAxICQBGiotiwGBASgAwUlwUAwYDUgKAkeKiGDAYkBIAjBQXxYDBgJQAYKS4KAYMBqQEACPFRTFgMCAlABgpLooBgwEpAcBIcVEMGAxICQBGiotiwGBASgAwUlwUAwYDUgKAkeKiGDAYkBIAjBQXxYDBgJQAYKS4KAYMBqQEACPFRTFgMCAlABgpLooBgwEpgakH8/7+Xs7Ozsrz83M5OTkpi4uLfwRwd3dXbm5uyvr6etnf32/+r9/vl6urq1J/tn729vbKxsbGRMF1fb2JfqkOi6YazOvrazk9PS1vb2+l1+v9BaZd7tPT0wBM+zNLS0vl6OioXF5eNtjq13Nzc2Oj7/p6HTqY+FJTC2Z4eXXaj8BcX1+Xh4eHUmvX1taaE6Y9cba3t8vOzs7g+3rKzM/PNyfP8vJyA6j+/P39fXMCra6uDnC6rjfpqTbxNjsonGowFxcX5eDgYHBKDN+S2tvO1tZWub29/RRMC6ieOI+Pj+X4+Licn5+X9iSq6P7H9TrYsfUSUwumTeGjZ4r232ZmZsru7m5zarQnTHtqjJ4w7feT3naGn5m+cj3rNjto9iPBDN9K2tvMZ7ekFkzNvJ4y9YQaflAeB/Sr1+tgz7ZL/DgwCwsLzVtTfdAd/aysrJTNzc3mremjZ5j6TNHeyuoD8MvLy19vUKMn2levZ9tkR41+HJjR1+oWQHvCjHtLmp2dbbDVt67Dw8PmpKlfD79BffZarVzvs7eyjgxIl4kDM+7vMP96vhm+Nalgxl1P2tQ3KZ56MN8kx5hfAzAxq/YMChhPjjFdABOzas+ggPHkGNMFMDGr9gwKGE+OMV0AE7Nqz6CA8eQY0wUwMav2DAoYT44xXQATs2rPoIDx5BjTBTAxq/YMChhPjjFdABOzas+ggPHkGNMFMDGr9gwKGE+OMV0AE7Nqz6CA8eQY0wUwMav2DAoYT44xXQATs2rPoIDx5BjTBTAxq/YMChhPjjFdABOzas+ggPHkGNMFMDGr9gwKGE+OMV0AE7Nqz6CA8eQY0wUwMav2DAoYT44xXQATs2rPoIDx5BjTBTAxq/YMChhPjjFdABOzas+ggPHkGNMFMDGr9gwKGE+OMV0AE7Nqz6CA8eQY0wUwMav2DAoYT44xXQATs2rPoIDx5BjTBTAxq/YMChhPjjFdABOzas+ggPHkGNMFMDGr9gwKGE+OMV0AE7Nqz6CA8eQY0wUwMav2DAoYT44xXQATs2rPoIDx5BjTBTAxq/YMChhPjjFdABOzas+ggPHkGNMFMDGr9gz6G1HzSbXtC7t7AAAAAElFTkSuQmCC">	
	      				</c:otherwise>
	      			</c:choose>


           <p>${link.description}</p>           
        </div>
      </div>
      </li>
  	 </c:forEach>
    </ul> 
  </div>

<jsp:include page="includes/footer.jsp"/>