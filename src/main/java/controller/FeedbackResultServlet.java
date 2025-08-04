package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbconn.DBConnection;

@WebServlet("/FeedbackResultServlet")
public class FeedbackResultServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Feedback Results - Online Feedback System</title>");
        out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css'>");
        out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js'></script>");
        out.println("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js'></script>");
        out.println("<link href='https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap' rel='stylesheet'>");

        out.println("<style>");
        out.println("* { box-sizing: border-box; font-family: 'Roboto', sans-serif; margin: 0; padding: 0; }");
        out.println("body { background: linear-gradient(120deg, #f6d365, #fda085); min-height: 100vh; }");
        out.println(".header { background-color: #333; color: white; padding: 20px 30px; display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; }");
        out.println(".header h1 { font-size: 24px; font-weight: 700; }");
        out.println(".nav-links { list-style: none; display: flex; align-items: center; }");
        out.println(".nav-links li { margin-left: 15px; }");
        out.println(".nav-links a, .nav-links button { color: white; text-decoration: none; font-size: 16px; background: none; border: none; cursor: pointer; padding: 8px 12px; transition: color 0.3s ease; }");
        out.println(".nav-links a:hover, .nav-links button:hover { color: #ffd700; text-decoration: underline; }");
        out.println(".container-fluid { padding: 20px; }");
        out.println(".sidenav { background-color: #ffffffd9; border-radius: 12px; padding: 20px; height: 100%; box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); }");
        out.println(".sidenav h2 { font-size: 20px; margin-bottom: 15px; color: #333; }");
        out.println(".sidenav .nav-pills > li > a { color: #333; font-weight: 500; border-radius: 8px; transition: background 0.3s ease; }");
        out.println(".sidenav .nav-pills > li.active > a, .sidenav .nav-pills > li > a:hover { background-color: #007bff; color: white; }");
        out.println(".form-box { background: white; padding: 30px; border-radius: 12px; box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1); animation: fadeIn 0.6s ease-in-out; }");
        out.println(".form-box h2 { margin-bottom: 20px; color: #333; font-weight: 600; }");
        out.println("table { margin-top: 20px; }");
        out.println("th, td { text-align: center; vertical-align: middle; }");
        out.println("th { background-color: #007bff; color: white; }");
        out.println("@keyframes fadeIn { from { opacity: 0; transform: translateY(20px); } to { opacity: 1; transform: translateY(0); } }");
        out.println("</style>");
        out.println("</head>");

        out.println("<body>");
        out.println("<header class='header'>");
        out.println("<h1>Online Feedback System</h1>");
        out.println("<nav>");
        out.println("<ul class='nav-links'>");
        out.println("<li><a href='#'>Admin</a></li>");
        out.println("<li><button class='btn btn-danger' onclick='showLogoutPopup()'>Logout</button></li>");
        out.println("</ul>");
        out.println("</nav>");
        out.println("</header>");

        out.println("<div class='container-fluid'>");
        out.println("<div class='row'>");
        out.println("<div class='col-sm-3 sidenav hidden-xs'>");
        out.println("<h2>Dashboard</h2>");
        out.println("<ul class='nav nav-pills nav-stacked'>");
        out.println("<li class='active'><a href='questions.html'>Questions</a></li>");
        out.println("<li class='active'><a href='FeedbackResultServlet'>Reviews</a></li>");
        out.println("</ul>");
        out.println("</div>");

        out.println("<div class='col-sm-9'>");
        out.println("<div class='form-box'>");
        out.println("<h2>Feedback Results</h2>");
        out.println("<div class='table-responsive'>");
        out.println("<table class='table table-bordered table-striped'>");
        out.println("<thead><tr><th>User ID</th><th>User Name</th><th>Average Rating</th><th>Overall Rating (%)</th></tr></thead>");
        out.println("<tbody>");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                "SELECT u.id AS user_id, u.name AS user_name, " +
                "AVG(f.answer) AS avg_rating, (AVG(f.answer) / 5) * 100 AS overall_percentage " +
                "FROM feedback f JOIN users u ON f.user_id = u.id " +
                "GROUP BY u.id, u.name ORDER BY u.id");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String userName = rs.getString("user_name");
                double avgRating = rs.getDouble("avg_rating");
                double overallPercentage = rs.getDouble("overall_percentage");

                out.println("<tr>");
                out.println("<td>" + userId + "</td>");
                out.println("<td>" + userName + "</td>");
                out.println("<td>" + String.format("%.2f", avgRating) + "</td>");
                out.println("<td>" + String.format("%.2f", overallPercentage) + "%</td>");
                out.println("</tr>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<tr><td colspan='4'>Error fetching feedback data.</td></tr>");
        }

        out.println("</tbody>");
        out.println("</table>");
        out.println("</div>");
        out.println("<a href='dash.html'>Back to Dashboard</a>");
        out.println("</div></div></div></div>");

        out.println("<div id='logoutPopup' class='modal fade' role='dialog'>");
        out.println("<div class='modal-dialog'>");
        out.println("<div class='modal-content'>");
        out.println("<div class='modal-header'>");
        out.println("<button type='button' class='close' data-dismiss='modal'>&times;</button>");
        out.println("<h4 class='modal-title'>Logout</h4>");
        out.println("</div><div class='modal-body'><p>You have been successfully logged out.</p></div>");
        out.println("<div class='modal-footer'><button type='button' class='btn btn-primary' onclick='redirectToHome()'>OK</button></div>");
        out.println("</div></div></div>");

        out.println("<script>");
        out.println("function showLogoutPopup() { $('#logoutPopup').modal('show'); }");
        out.println("function redirectToHome() { window.location.href = 'index.html'; }");
        out.println("</script>");
        out.println("</body></html>");
    }
}
