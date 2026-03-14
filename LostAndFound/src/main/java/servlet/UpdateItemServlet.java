package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Item;
import service.ItemManager;

import java.io.IOException;

@WebServlet("/updateItem")
public class UpdateItemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect("adminLogin.html");
            return;
        }

        ItemManager manager = (ItemManager) getServletContext().getAttribute("manager");
        if (manager != null) {
            String idStr = request.getParameter("id");
            if (idStr != null) {
                int id = Integer.parseInt(idStr);
                Item item = manager.getItemById(id);
                if (item != null) {
                    String newStatus = "Lost".equals(item.getStatus()) ? "Found" : "Lost";
                    manager.updateItemStatus(id, newStatus);
                }
            }
        }
        response.sendRedirect("adminPanel");
    }
}
