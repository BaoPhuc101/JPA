<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
</head>
<body>
	<h3>TRANG CHỦ ADMIN</h3>
	<div>
		<a href="${pageContext.request.contextPath}/admin/categories">
			<button>Category</button>
		</a>
	</div>

	<div>
		<a href="${pageContext.request.contextPath}/admin/videos">
			<button>Video</button>
		</a>
	</div>

	<div>
		<a href="${pageContext.request.contextPath}/admin/users">
			<button>User</button>
		</a>
	</div>
</body>
</html>