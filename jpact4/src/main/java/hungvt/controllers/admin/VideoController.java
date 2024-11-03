package hungvt.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import hungvt.entity.Category;
import hungvt.entity.Video;
import hungvt.services.IVideoService;
import hungvt.services.impl.VideoService;
import hungvt.utils.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import hungvt.services.ICategoryService;
import hungvt.services.impl.CategoryService;
import hungvt.entity.Category;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
    maxFileSize = 1024 * 1024 * 5, 
    maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = {
    "/admin/videos", 
    "/admin/video/add",
    "/admin/video/insert", 
    "/admin/video/edit", 
    "/admin/video/update",
    "/admin/video/delete", 
    "/admin/video/search"})


public class VideoController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    public IVideoService videoService = new VideoService();
    public ICategoryService categoryService = new CategoryService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        if (url.contains("videos")) {
            List<Video> list = videoService.getAllVideos();
            req.setAttribute("listVideos", list);
            req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
            
        } else if (url.contains("add")) {
        	List<Category> categories = categoryService.findAll();
            req.setAttribute("categories", categories);
        	req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
        	
        } else if (url.contains("edit")) {
            long id = Long.parseLong(req.getParameter("id"));
            Video video = videoService.getVideoById(id);
            req.setAttribute("video", video);
            req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
            
        } else if (url.contains("delete")) {
            long id = Long.parseLong(req.getParameter("id"));
            try {
                videoService.deleteVideo(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        if (url.contains("update")) {
            String videoId = req.getParameter("videoId");
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            String category = req.getParameter("category");
            String status = req.getParameter("status");
            Video video = new Video();
            video.setVideoId(videoId);
            video.setTitle(title);
            video.setDescription(description);
            video.setCategory(new Category());
            video.setStatus(status);
            
            // Xử lý hình ảnh
            String fileName = handleFileUpload(req);
            video.setImages(fileName);

            videoService.updateVideo(video);
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        } else if (url.contains("insert")) {
            Video video = new Video();
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            String category = req.getParameter("category");
            String status = req.getParameter("status");
            video.setTitle(title);
            video.setDescription(description);
            video.setCategory(new Category());
            video.setStatus(status);

            // Xử lý hình ảnh
            String fileName = handleFileUpload(req);
            video.setImages(fileName);

            videoService.createVideo(video);
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
            System.out.println("Redirecting to: " + req.getContextPath() + "/admin/videos");

        }
    }

    private String handleFileUpload(HttpServletRequest req) {
        String fileName = "default.png"; // Tên file mặc định
        String uploadPath = Constant.DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            Part part = req.getPart("images");
            if (part.getSize() > 0) {
                String originalFileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                int index = originalFileName.lastIndexOf(".");
                String ext = originalFileName.substring(index + 1);
                fileName = System.currentTimeMillis() + "." + ext;
                part.write(uploadPath + "/" + fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }
}
