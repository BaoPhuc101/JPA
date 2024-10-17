<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<form action="<c:url value="/admin/video/update"/>" method="post" enctype="multipart/form-data">
	<input type="text" name="videoid" value="${video.videoId}" hidden="hidden"> 
	
	<label for="lname">Link Video:</label><br> 
	<input type="text" id="title" name="title" value="${video.title}"><br>
	<c:if test="${video.title.substring(0,5)=='https'}">
		<c:url value="${video.title }" var="imgUrl"></c:url>
	</c:if>
	<c:if test="${video.title.substring(0,5)!='https'}">
		<c:url value="/video?fname=${video.title }" var="imgUrl"></c:url>
	</c:if>
	<video controls height="100" width="250" src="${imgUrl}" /></video> <br> 
	
	<label for="lname">Upload Video:</label><br>
	<input type="file" id="title1" name="title1"><br>
	
	<label for="lname">Description:</label><br>
	<input type="text" id="description" name="description"><br>
	
	<label for="lname">Poster:</label><br>
	<input type="text" id="poster" name="poster"><br>
	
	<label for="lname">Active:</label><br>
	<input type="text" id="active" name="active"><br>
	
	<label for="lname">Views:</label><br>
	<input type="text" id="views" name="views"><br>
	
	<br> <input type="submit" value="Update">

</form>
