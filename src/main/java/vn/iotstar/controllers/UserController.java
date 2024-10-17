package vn.iotstar.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iotstar.entity.User;
import vn.iotstar.services.*;
import vn.iotstar.utils.Constant;

@MultipartConfig()
@WebServlet(urlPatterns = { "/admin/users", "/admin/user/add", "/admin/user/insert", "/admin/user/edit",
		"/admin/user/update", "/admin/user/delete" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public IUserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if (url.contains("/admin/users")) {
			List<User> list = userService.findAll();
			req.setAttribute("listuser", list);
			req.getRequestDispatcher("/views/admin/user-list.jsp").forward(req, resp);
		} else if (url.contains("/admin/user/add")) {
			req.getRequestDispatcher("/views/admin/user-add.jsp").forward(req, resp);
		} else if (url.contains("/admin/user/edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			User user = userService.findById(id);
			req.setAttribute("user", user);
			req.getRequestDispatcher("/views/admin/user-edit.jsp").forward(req, resp);
		} else {
			int id = Integer.parseInt(req.getParameter("id"));
			try {
				userService.delete(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// chuyển trang
			resp.sendRedirect(req.getContextPath() + "/admin/users");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if (url.contains("/admin/user/insert")) {
			// lấy dữ liệu từ form
			String username = req.getParameter("username");
			String images = req.getParameter("images");
			String password = req.getParameter("password");
			String fullname = req.getParameter("fullname");
			String email = req.getParameter("email");
			String phone = req.getParameter("phone");
			Date createdDate = Date.valueOf(req.getParameter("createdDate"));
			// đưa dữ liệu vào model
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			user.setFullname(fullname);
			user.setPhone(phone);
			user.setCreatedDate(createdDate);
			String fname = "";
			String uploadPath = Constant.DIR; // upload vào thư mục bất kỳ
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists())
				uploadDir.mkdir();
			try {
				Part part = req.getPart("images1");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					part.write(uploadPath + "/" + fname);
					user.setImages(fname);
				} else if (images != null) {
					user.setImages(images);
				} else {
					user.setImages("avatar.png");
				}
			} catch (FileNotFoundException fne) {
				fne.printStackTrace();
			}
			// đưa model vào phương thức insert
			userService.insert(user);
			// chuyển trang
			resp.sendRedirect(req.getContextPath() + "/admin/users");
		}
		if (url.contains("/admin/user/update")) {
			// lấy dữ liệu từ form
			int userid = Integer.parseInt(req.getParameter("userid"));
			String username = req.getParameter("username");
			String images = req.getParameter("images");
			String password = req.getParameter("password");
			String fullname = req.getParameter("fullname");
			String email = req.getParameter("email");
			String phone = req.getParameter("phone");
			Date createdDate = Date.valueOf(req.getParameter("createdDate"));
			// đưa dữ liệu vào model
			User user = userService.findById(userid);
			String fileold = user.getImages();
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			user.setFullname(fullname);
			user.setPhone(phone);
			user.setCreatedDate(createdDate);
			// Xử lý ảnh
			String fname = "";
			String uploadPath = Constant.DIR; // upload vào thư mục bất kỳ
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists())
				uploadDir.mkdir();
			try {
				Part part = req.getPart("images1");
				if (part.getSize() > 0) {
					// xóa file cũ trên thư mục
					if (!user.getImages().substring(0, 5).equals("https")) {
						deleteFile(uploadPath + "\\" + fileold);
					}
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					part.write(uploadPath + "/" + fname);
					user.setImages(fname);
				} else if (images != null) {
					user.setImages(images);
				} else {
					user.setImages(fileold);
				}
			} catch (FileNotFoundException fne) {
				fne.printStackTrace();
			}
			// đưa model vào phương thức insert
			userService.update(user);
			// chuyển trang
			resp.sendRedirect(req.getContextPath() + "/admin/users");
		}
	}

	public static void deleteFile(String filePath) throws IOException {
		Path path = Paths.get(filePath);
		Files.delete(path);
	}
}
