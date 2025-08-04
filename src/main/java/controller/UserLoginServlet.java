package controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbconn.DBConnection;

@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("email");
        String password = request.getParameter("password");
        
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            response.getWriter().println("<script>alert('Email and Password cannot be empty!');window.location.href='user.html';</script>");
            return;
        }

        Integer userId = getUserId(username, password);
        if (userId != null) {
            // ✅ Create a session and store user_id
            HttpSession session = request.getSession();
            session.setAttribute("user_id", userId);
            session.setAttribute("email", username);
            
            System.out.println("Login successful. User ID: " + userId);
            
            // ✅ Redirect to the feedback form
            response.sendRedirect("FeedbackFormServlet"); 
        } else {
            // ❌ If login fails, show an error message
            response.setContentType("text/html");
            response.getWriter().println("<script>alert('Invalid email or password!');window.location.href='user.html';</script>");
        }
    }

    private Integer getUserId(String username, String password) {
        String query = "SELECT id FROM users WHERE email = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");  // ✅ Return user_id
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // ❌ User not found
    }
}

