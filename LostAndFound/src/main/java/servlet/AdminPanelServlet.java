package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Item;
import service.ItemManager;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/adminPanel")
public class AdminPanelServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect("adminLogin.html");
            return;
        }

        ItemManager manager = (ItemManager) getServletContext().getAttribute("manager");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html><html><head><title>Admin Panel</title><link rel='stylesheet' href='style.css'></head><body>");
        out.println("<div class='container'>");
        out.println("<div style='text-align: right;'><a href='logout'><button>Logout</button></a></div>");
        out.println("<h2>Admin Panel</h2>");
        out.println("<div style='text-align: center; margin-bottom: 20px;'><a href='addItem'><button>Add New Item</button></a></div><hr>");

        if (manager != null) {
            out.println("<div class='items-container'>");
            for (Item item : manager.getAllItems()) {
                out.println("<div class='item-card'>");
                out.println(item.display() + "<br>");
                if (item.getImagePath() != null) {
                    out.println("<img src='" + item.getImagePath() + "' style='max-width: 100%; border-radius: 4px; margin-top: 10px;'><br>");
                }
                out.println("<div style='margin-top: 10px;'>");
                out.println("<a href='updateItem?id=" + item.getId() + "'><button style='padding: 5px 10px; font-size: 12px;'>Toggle Status</button></a> ");
                out.println("<a href='deleteItem?id=" + item.getId() + "'><button style='padding: 5px 10px; font-size: 12px; background-color: #e74c3c;'>Delete</button></a>");
                out.println("</div>");
                out.println("</div>");
            }
            out.println("</div>");
        }
        out.println("</div></body></html>");
    }
}
