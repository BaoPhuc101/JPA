<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<form action="<c:url value="/admin/user/update"/>" method="post" enctype="multipart/form-data">
	<input type="text" name="userid" value="${user.userId}" hidden="hidden"> 
	
	<label for="fname">UserName:</label><br>
	<input type="text" id="username" name="username" value="${user.username}"><br> 
	
	<label for="lname">Link images:</label><br> 
	<input type="text" id="images" name="images" value="${user.images}"><br>
	<c:if test="${user.images.substring(0,5)=='https'}">
		<c:url value="${user.images }" var="imgUrl"></c:url>
	</c:if>
	<c:if test="${user.images.substring(0,5)!='https'}">
		<c:url value="/image?fname=${user.images }" var="imgUrl"></c:url>
	</c:if>
	<img height="100" width="250" src="${imgUrl}" /><br> 
	
	<label for="lname">Upload images:</label><br>
	<input type="file" id="images1" name="images1"><br>
	
	<label for="lname">Password:</label><br>
	<input type="text" id="password" name="password"><br>
	
	<label for="lname">FullName:</label><br>
	<input type="text" id="fullname" name="fullname"><br>
	
	<label for="lname">Email:</label><br>
	<input type="text" id="email" name="email"><br>
	
	<label for="lname">Phone:</label><br>
	<input type="text" id="phone" name="phone"><br>
	
	<label for="lname">CreatedDate:</label><br>
	<input type="text" id="createdDate" name="createdDate"><br>
	
	<br> <input type="submit" value="Update">

</form>
