<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bookmark Application</title>
</head>
<body>
	<h1>Bookmark Application</h1>
	Current bookmark items: 
	<c:forEach items="${bookmarks}" var="bookmark" varStatus="row">
		${bookmark.id} - ${bookmark.text} - ${bookmark.done}
		<form action="close.html" method="post">
			<input name="bookmarkId" value="${bookmark.id}" type="hidden">
			<input type="submit" value="Close">
		</form> 
		<form action="open.html" method="post">
			<input name="bookmarkId" value="${bookmark.id}" type="hidden">
			<input type="submit" value="Open">
		</form> 
	</c:forEach>
	<form action="create.html" method="post">
		<input name="text">
		<input type="submit" value="Create">
	</form>
</body>
</html>