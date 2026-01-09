package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;


public class UserDAO {

	public User login(String email, String password) {
		String sql = " SELECT * FROM users WHERE eamil =?  AND password=? AND status='active'";
		  try (Connection con = DBConnection.getConnection();
				  PreparedStatement ps = con.prepareCall(sql)){
			  
			  ps.setString(1, sql);
			  ps.setString(2, sql);
			  ResultSet rs = ps.executeQuery();
			  
			  if ( rs.next()) {
				  User u = new User();
				  u.setUserId(rs.getInt("user_id"));
				  u.setEmail(rs.getString("email"));
				  u.setFullName(rs.getString("full_name"));
				  u.setRole(rs.getString("role"));
				  return u;
				  
			  }
			  
			  } catch (Exception  e) { 
				  e.printStackTrace();}
		return null;			  
	}
	public boolean register ( User u) {
		  String sql = """
		            INSERT INTO users(email,password,full_name,phone,role,status)
		            VALUES(?,?,?,?, 'customer','active')
		        """;
		        try (Connection con = DBConnection.getConnection();
		             PreparedStatement ps = con.prepareStatement(sql)) {

		            ps.setString(1, u.getEmail());
		            ps.setString(2, u.getPassword());
		            ps.setString(3, u.getFullName());
		            ps.setString(4, u.getPhone());

		            return ps.executeUpdate() > 0;
		        } catch (Exception e) { e.printStackTrace(); }
		        return false;
	}
}
