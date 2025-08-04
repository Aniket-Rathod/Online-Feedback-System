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
