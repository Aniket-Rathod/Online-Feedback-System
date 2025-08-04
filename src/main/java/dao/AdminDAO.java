package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dbconn.DBConnection;

public class AdminDAO {
    public boolean validateAdmin(String email, String password) {
        boolean status = false;
        try {
            Connection conn = DBConnection.getConnection(); // Get database connection
            String sql = "SELECT * FROM admin WHERE email = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            status = rs.next(); // If a record exists, login is successful
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
