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
import vn.iotstar.entity.Video;
import vn.iotstar.services.*;
import vn.iotstar.utils.Constant;

@MultipartConfig()
@WebServlet(urlPatterns = { "/admin/videos", "/admin/video/add", "/admin/video/insert", "/admin/video/edit",
		"/admin/video/update", "/admin/video/find", "/admin/video/delete" })
public class VideoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public IVideoService videoService = new VideoServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if (url.contains("/admin/videos")) {
			List<Video> list = videoService.findAll();
			req.setAttribute("listvideo", list);
			req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
		} else if (url.contains("/admin/video/add")) {
			req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
		} else if (url.contains("/admin/video/edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Video video = videoService.findById(id);
			req.setAttribute("video", video);
			req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
		} else if (url.contains("/admin/video/find")) {
			int id = Integer.parseInt(req.getParameter("videoID"));
			List<Video> list = videoService.searchById(id);
			req.setAttribute("listvideo", list);
			req.getRequestDispatcher("/views/admin/video-find.jsp").forward(req, resp);
		} else {
			int id = Integer.parseInt(req.getParameter("id"));
			try {
				videoService.delete(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// chuyển trang
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if (url.contains("/admin/video/insert")) {
			// lấy dữ liệu từ form
			String title = req.getParameter("title");
			String description = req.getParameter("description");
			String poster = req.getParameter("poster");
			Boolean active = Boolean.parseBoolean(req.getParameter("active"));
			Integer views = Integer.parseInt(req.getParameter("views"));
			// đưa dữ liệu vào model
			Video video = new Video();
			video.setDescription(description);
			video.setPoster(poster);
			video.setActive(active);
			video.setViews(views);
			String fname = "";
			String uploadPath = Constant.DIR; // upload vào thư mục bất kỳ
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists())
				uploadDir.mkdir();
			try {
				Part part = req.getPart("title1");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					part.write(uploadPath + "/" + fname);
					video.setTitle(fname);
				} else if (title != null) {
					video.setTitle(title);
				} else {
					video.setTitle("video.mp4");
				}
			} catch (FileNotFoundException fne) {
				fne.printStackTrace();
			}
			// đưa model vào phương thức insert
			videoService.insert(video);
			// chuyển trang
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		}
		if (url.contains("/admin/video/update")) {
			// lấy dữ liệu từ form
			int videoid = Integer.parseInt(req.getParameter("videoid"));
			String title = req.getParameter("title");
			String description = req.getParameter("description");
			String poster = req.getParameter("poster");
			Boolean active = Boolean.parseBoolean(req.getParameter("active"));
			Integer views = Integer.parseInt(req.getParameter("views"));
			// đưa dữ liệu vào model
			Video video = videoService.findById(videoid);
			String fileold = video.getTitle();
			video.setDescription(description);
			video.setPoster(poster);
			video.setActive(active);
			video.setViews(views);
			// Xử lý ảnh
			String fname = "";
			String uploadPath = Constant.DIR; // upload vào thư mục bất kỳ
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists())
				uploadDir.mkdir();
			try {
				Part part = req.getPart("title1");
				if (part.getSize() > 0) {
					// xóa file cũ trên thư mục
					if (!video.getTitle().substring(0, 5).equals("https")) {
						deleteFile(uploadPath + "\\" + fileold);
					}
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					part.write(uploadPath + "/" + fname);
					video.setTitle(fname);
				} else if (title != null) {
					video.setTitle(title);
				} else {
					video.setTitle(fileold);
				}
			} catch (FileNotFoundException fne) {
				fne.printStackTrace();
			}
			// đưa model vào phương thức insert
			videoService.update(video);
			// chuyển trang
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		}
	}

	public static void deleteFile(String filePath) throws IOException {
		Path path = Paths.get(filePath);
		Files.delete(path);
	}
}
