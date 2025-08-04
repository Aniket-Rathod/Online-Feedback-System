package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.AdminDAO;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AdminDAO adminDAO = new AdminDAO();
        boolean isValidAdmin = adminDAO.validateAdmin(email, password);

        if (isValidAdmin) {
            response.sendRedirect("dash.html"); // Redirect to admin panel
        } else {
            response.setContentType("text/html");
            response.getWriter().println("<script type='text/javascript'>");
            response.getWriter().println("alert('Invalid credentials! Please try again.');");
            response.getWriter().println("window.location.href = 'admin.html';");
            response.getWriter().println("</script>");
        }
    }
}
