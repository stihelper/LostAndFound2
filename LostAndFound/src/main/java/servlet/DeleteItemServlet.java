package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import service.ItemManager;

import java.io.IOException;

@WebServlet("/deleteItem")
public class DeleteItemServlet extends HttpServlet {

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
            int id = Integer.parseInt(request.getParameter("id"));
            manager.deleteItem(id);
        }

        response.sendRedirect("adminPanel");
    }
}