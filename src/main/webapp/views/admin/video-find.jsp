<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<table border="1" width="100%">
	<tr>
		<th>VideoID</th>
		<th>Title</th>
		<th>Description</th>
		<th>Poster</th>
		<th>Active</th>
		<th>Views</th>
	</tr>
	
	<c:forEach items="${listvideo}" var="video">
		<tr>
			<td>${video.videoId}</td>
			<td>
				<c:if test="${video.title.substring(0, 5) != 'https'}">
					<c:url value="/video?fname=${video.title}" var="imgUrl"></c:url>
				</c:if>
				
				<c:if test="${video.title.substring(0, 5) == 'https'}">
					<c:url value="${video.title}" var="imgUrl"></c:url>
				</c:if>
				
				<video controls height="150" width="200" src="${imgUrl}" ></video>
				
			</td>
			<td>${video.description}</td>
			<td>${video.poster}</td>
			<td>${video.active}</td>
			<td>${video.views}</td>
		</tr>
	</c:forEach>

</table>
<a href="${pageContext.request.contextPath}/admin/videos">Trở về trang chính</a>
