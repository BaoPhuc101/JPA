<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<a href="${pageContext.request.contextPath}/admin/user/add">Add User</a>
<table border="1" width="100%">
	<tr>
		<th>STT</th>
		<th>Images</th>
		<th>UserID</th>
		<th>UserName</th>
		<th>Password</th>
		<th>FullName</th>
		<th>Email</th>
		<th>Phone</th>
		<th>CreatedDate</th>
		<th>Action</th>
	</tr>
	
	<c:forEach items="${listuser}" var="user" varStatus="STT">
		<tr>
			<td>${STT.index+1}</td>
			<td>
			
				<c:if test="${user.images.substring(0, 5) != 'https'}">
					<c:url value="/image?fname=${user.images}" var="imgUrl"></c:url>
				</c:if>
				
				<c:if test="${user.images.substring(0, 5) == 'https'}">
					<c:url value="${user.images}" var="imgUrl"></c:url>
				</c:if>
				
				<img height="150" width="200" src="${imgUrl}" />
				
			</td>
			<td>${user.userId}</td>
			<td>${user.username}</td>
			<td>${user.password}</td>
			<td>${user.fullname}</td>
			<td>${user.email}</td>
			<td>${user.phone}</td>
			<td>${user.createdDate}</td>
			<td>
				<a href="<c:url value='/admin/user/edit?id=${user.userId}'/>">Sửa</a>
				| <a
				href="<c:url value='/admin/user/delete?id=${user.userId}'/>">Xóa</a></td>
		</tr>
	</c:forEach>
</table>