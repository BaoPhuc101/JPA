<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form action="${pageContext.request.contextPath}/admin/video/insert"
	method="post" enctype="multipart/form-data">
	
	<label for="lname">Link video:</label>
	<br>
	<input type="text" id="title" name="title">
	<br>
	<label for="lname">Upload video:</label>
	<br>
	<input type="file" id="title1" name="title1">
	<br>
	<label for="lname">Description:</label>
	<br>
	<input type="text" id="description" name="description">
	<br>
	<label for="lname">Poster:</label>
	<br>
	<input type="text" id="poster" name="poster">
	<br>
	<label for="lname">Active:</label>
	<br>
	<input type="text" id="active" name="active">
	<br>
	<label for="lname">Views:</label>
	<br>
	<input type="text" id="views" name="views">
	<br>
	<br>
	<input type="submit" value="Insert">

</form>