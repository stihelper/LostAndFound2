package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Item;
import service.ItemManager;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/viewItems")
public class ViewItemsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        ItemManager manager = (ItemManager) getServletContext().getAttribute("manager");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head><title>Lost & Found Items</title><link rel='stylesheet' href='style.css'></head><body>");
        out.println("<div class='container'>");
        out.println("<h2>Lost & Found Items</h2>");
        out.println("<div style='text-align: center; margin-bottom: 20px;'><a href='index.html'><button>Home</button></a></div><hr>");

        if (manager != null && !manager.getAllItems().isEmpty()) {
            out.println("<div class='items-container'>");
            for (Item item : manager.getAllItems()) {
                out.println("<div class='item-card'>");
                out.println(item.display() + "<br>");
                if (item.getImagePath() != null) {
                    out.println("<img src='" + item.getImagePath() + "' style='max-width: 100%; border-radius: 4px; margin-top: 10px;'><br>");
                }
                out.println("</div>");
            }
            out.println("</div>");
        } else {
            out.println("<p style='text-align: center;'>No items available.</p>");
        }
        out.println("</div></body></html>");
    }
}
