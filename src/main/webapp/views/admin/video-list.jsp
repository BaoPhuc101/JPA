<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<a href="${pageContext.request.contextPath}/admin/video/add">Add Video</a>

<form action="${pageContext.request.contextPath}/admin/video/find" method="get">
	<label for="fname">Find Video by ID:</label>
	<input type="text" id="videoID" name="videoID">
	<input type="submit" value="SEARCH">
</form>

<table border="1" width="100%">
	<tr>
		<th>STT</th>
		<th>Title</th>
		<th>VideoID</th>
		<th>Description</th>
		<th>Poster</th>
		<th>Active</th>
		<th>Views</th>
		<th>Action</th>
	</tr>
	
	<c:forEach items="${listvideo}" var="video" varStatus="STT">
		<tr>
			<td>${STT.index+1}</td>
			<td>
				<c:if test="${video.title.substring(0, 5) != 'https'}">
					<c:url value="/video?fname=${video.title}" var="imgUrl"></c:url>
				</c:if>
				
				<c:if test="${video.title.substring(0, 5) == 'https'}">
					<c:url value="${video.title}" var="imgUrl"></c:url>
				</c:if>
				
				<video controls height="150" width="200" src="${imgUrl}" ></video>
				
			</td>
			<td>${video.videoId}</td>
			<td>${video.description}</td>
			<td>${video.poster}</td>
			<td>${video.active}</td>
			<td>${video.views}</td>
			<td>
				<a href="<c:url value='/admin/video/edit?id=${video.videoId}'/>">Sửa</a>
				| <a
				href="<c:url value='/admin/video/delete?id=${video.videoId}'/>">Xóa</a></td>
		</tr>
	</c:forEach>

</table>
