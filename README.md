# Online-Feedback-System: 
Welcome to the Online Feedback System, a web-based application. This application makes it simple for users to submit feedback on courses, 
while allowing administrators to manage feedback questions and view results—all from a clean web interface.

@ Project Overview:
The Online Feedback System is designed to streamline the collection and analysis of feedback. 
Users can register, log in, and respond to feedback questions created by the administrator. 
Admins can manage questions, assign subjects and marks, view detailed feedback results, and 
calculate overall ratings—making feedback management and analysis easier than ever.

@ Features
For Users
Easy Registration: Sign up with your name, email, and password.
Secure Login: Log in with your email and password.
Feedback Submission: Answer feedback questions (set by Admins) with a simple 1–5 rating scale.
Profile-based Storage: Your feedback is saved and linked to your profile.

For Admins
Admin Login: Access secured with email and password.
Question Management: Add new questions with associated subject and scoring marks. Edit or delete existing questions. See all questions in one place.

@ Feedback Results:
View feedback responses per user and per question.
See overall ratings with a user-friendly summary.

@ Technologies Used
Frontend: HTML5, CSS3 (No JavaScript, No JSP)
Backend: Java Servlets
Database: MySQL (Accessed via JDBC)
Web Server: Apache Tomcat

@ System Requirements
Java JDK (version as per your Tomcat/Servlet container)
Apache Tomcat (Servlet container)
MySQL Community Edition
A modern Web Browser (for app access)

@ Setup Instructions
1. Install MySQL and create the database: Use the provided setup.sql file to create necessary tables.
2. Add a sample administrator: Insert admin credentials into the database (e.g., email: example123@gmail.com, password: Exp@123).
3. Configure the database connection (DBUtil.java or similar utility), avoid hardcoding sensitive data.
4. Set up Apache Tomcat and deploy the compiled project WAR or folder.
5. Map Servlets in web.xml: Configure URL patterns for all servlets.
6. Access the application in your browser at http://localhost:8080/[YourAppName]/.

@ Application Structure:
 Pages:
 1. index.html: Introduction and navigation for Users/Admins.
 2. register.html: User Registration.
 3. user_login.html: User Login page.
 4. admin_login.html: Admin Login page.
 5. Dynamic forms for feedback and admin actions via Servlets.
 6. CSS file (styles.css) for unified styles.

 Servlets:
 1. IntroServlet.java: Navigation controller.
 2. UserRegisterServlet.java: Handles user registration.
 3. UserLoginServlet.java: Manages user login and dashboard redirection.
 4. FeedbackServlet.java: Fetches questions, processes feedback.
 5. AdminLoginServlet.java: Secures admin access.
 6. QuestionServlet.java: Question CRUD operations.
 7. ResultServlet.java: Generates reports and calculates ratings.

 @ Usage Guide:
  User Workflow
  1. Visit Home: Go to index.html.
  2. Register: Click "Submit Feedback" and sign up if new.
  3. Login: Enter your email and password.
  4. Submit Feedback: Answer questions (e.g., rate course content from 1 to 5) and submit your responses.

 Admin Workflow
 1. Visit Home: Go to index.html.
 2. Login as Admin: Click "Admin Login" and enter credentials.
 3. Manage Questions: Add new questions with the subject and max marks. Edit or delete questions as needed.
 4. View Results: Check feedback provided by users. Review calculated overall and per-question ratings.

 @ Database Design:
 Three core tables:
 - users: Stores user info.
 CREATE TABLE users (
 id INT AUTO_INCREMENT PRIMARY KEY,
 name VARCHAR(50) NOT NULL,
 email VARCHAR(50) UNIQUE NOT NULL,
 password VARCHAR(50) NOT NULL
 );

 - questions: Stores feedback questions.
 CREATE TABLE questions (
 id INT AUTO_INCREMENT PRIMARY KEY,
 question_text VARCHAR(200) NOT NULL,
 subject VARCHAR(50) NOT NULL,
 max_marks INT NOT NULL
 );

  - feedback: Stores users' answers.
 CREATE TABLE feedback (
 id INT AUTO_INCREMENT PRIMARY KEY,
 user_id INT,
 question_id INT,
 answer INT NOT NULL,
 submission_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 FOREIGN KEY (user_id) REFERENCES users(id),
 FOREIGN KEY (question_id) REFERENCES questions(id)
 );
 
