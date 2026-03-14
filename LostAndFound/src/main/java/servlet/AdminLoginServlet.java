package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String password = request.getParameter("password");

        if ("apollo7".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("admin", true);
            response.sendRedirect("adminPanel");
        } else {
            response.setContentType("text/html");
            response.getWriter().println("Invalid login. <br><a href='adminLogin.html'>Try again</a>");
        }
    }
}
