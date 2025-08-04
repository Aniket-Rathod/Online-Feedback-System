package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/FeedbackFormServlet")
public class FeedbackFormServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/feedback_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "Aniket@63";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            response.sendRedirect("user.html");
            return;
        }

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Feedback Form</title>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("<link href='https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap' rel='stylesheet'>");

        // Custom Styling with Hover Effects
        out.println("<style>");
        out.println("body { background: linear-gradient(135deg, #e0f7fa, #e1bee7); font-family: 'Poppins', sans-serif; margin: 0; padding: 0; }");
        out.println("header { background-color: #343a40; color: white; padding: 20px; position: fixed; top: 0; width: 100%; z-index: 1000; }");
        out.println("header h1 { margin: 0; font-size: 1.8rem; }");
        out.println("header nav a { color: white; margin-left: 20px; text-decoration: none; font-weight: 500; }");
        out.println("header nav a:hover { text-decoration: underline; color: #fdd835; }");
        out.println(".container-wrapper { margin-top: 120px; }");
        out.println(".card { background-color: #ffffffee; border-radius: 15px; box-shadow: 0 4px 20px rgba(0,0,0,0.1); transition: box-shadow 0.3s ease; }");
        out.println(".card:hover { box-shadow: 0 6px 30px rgba(0,0,0,0.2); }");
        out.println("label { font-weight: 500; transition: color 0.2s ease; }");
        out.println("label:hover { color: #007bff; }");
        out.println("select.form-control { border-radius: 8px; transition: box-shadow 0.3s ease; }");
        out.println("select.form-control:hover, select.form-control:focus { box-shadow: 0 0 10px rgba(0,123,255,0.3); outline: none; }");
        out.println(".btn-custom { background-color: #007bff; color: white; font-weight: 600; padding: 10px 20px; border-radius: 8px; transition: background-color 0.3s, transform 0.2s; }");
        out.println(".btn-custom:hover { background-color: #0056b3; transform: scale(1.05); }");
        out.println("</style>");

        out.println("</head>");
        out.println("<body>");

        // Header section
        out.println("<header class='d-flex justify-content-between align-items-center px-4'>");
        out.println("<h1>Online Feedback System</h1>");
        out.println("<nav>");
        out.println("<a href='#'>User</a>");
        out.println("<a href='#' onclick='logoutConfirmation()'>Logout</a>");
        out.println("</nav>");
        out.println("</header>");

        // Feedback Form Content
        out.println("<div class='container container-wrapper'>");
        out.println("<div class='card p-4'>");
        out.println("<h2 class='text-center mb-4'>Feedback Form</h2>");
        out.println("<form action='FeedbackServlet' method='post'>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, question_text FROM questions");

            boolean hasQuestions = false;

            while (rs.next()) {
                hasQuestions = true;
                int questionId = rs.getInt("id");
                String questionText = rs.getString("question_text");

                out.println("<div class='mb-3'>");
                out.println("<label for='rating_" + questionId + "' class='form-label'>" + questionText + "</label>");
                out.println("<select name='rating_" + questionId + "' id='rating_" + questionId + "' class='form-control' required>");
                out.println("<option value='' disabled selected>Select rating</option>");
                out.println("<option value='5'>Excellent (5)</option>");
                out.println("<option value='4'>Very Good (4)</option>");
                out.println("<option value='3'>Good (3)</option>");
                out.println("<option value='2'>Fair (2)</option>");
                out.println("<option value='1'>Poor (1)</option>");
                out.println("</select>");
                out.println("</div>");
            }

            if (!hasQuestions) {
                out.println("<div class='alert alert-warning text-center'>No feedback questions available at the moment.</div>");
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<div class='alert alert-danger text-center'>Unable to load questions. Please try again later.</div>");
        }

        out.println("<div class='text-center'>");
        out.println("<button type='submit' class='btn btn-custom'>Submit Feedback</button>");
        out.println("</div>");

        out.println("</form>");
        out.println("</div>");
        out.println("</div>");

        // JS for logout confirmation
        out.println("<script>");
        out.println("function logoutConfirmation() {");
        out.println("  if (confirm('Logout from the session?')) { window.location.href = 'index.html'; }");
        out.println("}");
        out.println("</script>");

        out.println("</body>");
        out.println("</html>");
    }
}
