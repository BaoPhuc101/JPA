<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form action="${pageContext.request.contextPath}/admin/user/insert"
	method="post" enctype="multipart/form-data">
	
	<label for="fname">UserName:</label>
	<br>
	<input type="text" id="username" name="username">
	<br>
	<label for="lname">Link images:</label>
	<br>
	<input type="text" id="images" name="images">
	<br>
	<label for="lname">Upload images:</label>
	<br>
	<input type="file" id="images1" name="images1">
	<br>
	<label for="lname">Password:</label>
	<br>
	<input type="text" id="password" name="password">
	<br>
	<label for="lname">FullName:</label>
	<br>
	<input type="text" id="fullname" name="fullname">
	<br>
	<label for="lname">Email:</label>
	<br>
	<input type="text" id="email" name="email">
	<br>
	<label for="lname">Phone:</label>
	<br>
	<input type="text" id="phone" name="phone">
	<br>
	<label for="lname">CreatedDate:</label>
	<br>
	<input type="text" id="createdDate" name="createdDate">
	<br>
	<br>
	<input type="submit" value="Insert">

</form>