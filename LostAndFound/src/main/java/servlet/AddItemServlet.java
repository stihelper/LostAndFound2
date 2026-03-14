package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Item;
import service.ItemManager;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addItem")
@MultipartConfig
public class AddItemServlet extends HttpServlet {

    private String uploadPath;

    @Override
    public void init() throws ServletException {
        if (getServletContext().getAttribute("manager") == null) {
            getServletContext().setAttribute("manager", new ItemManager());
        }
        uploadPath = getServletContext().getRealPath("") + File.separator + "images";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect("adminLogin.html");
            return;
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html><html><head><title>Add New Item</title><link rel='stylesheet' href='style.css'></head><body>");
        out.println("<div class='container'>");
        out.println("<h2>Add New Item</h2>");
        out.println("<form action='addItem' method='post' enctype='multipart/form-data'>");
        out.println("Name: <input type='text' name='name' required><br>");
        out.println("Description: <input type='text' name='description' required><br>");
        out.println("Image: <input type='file' name='image'><br><br>");
        out.println("<button type='submit'>Add Item</button>");
        out.println("</form>");
        out.println("<br><div style='text-align: center;'><a href='adminPanel'><button>Back to Admin Panel</button></a></div>");
        out.println("</div></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect("adminLogin.html");
            return;
        }

        ItemManager manager = (ItemManager) getServletContext().getAttribute("manager");
        String name = request.getParameter("name");
        String desc = request.getParameter("description");
        String status = "Lost";

        Part filePart = request.getPart("image");
        String fileName = System.currentTimeMillis() + "_" + filePart.getSubmittedFileName();
        filePart.write(uploadPath + File.separator + fileName);
        String imagePath = "images/" + fileName;

        try {
            Item item = new Item(0, name, desc, status, imagePath, null);
            manager.addItem(item);
        } catch (Exception e) {
            System.err.println("=== ERROR: Failed to save item to database ===");
            e.printStackTrace();
        }

        response.sendRedirect("adminPanel");
    }
}
