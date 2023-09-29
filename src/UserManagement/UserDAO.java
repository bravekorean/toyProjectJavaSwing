package UserManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DB.DBConnection;



public class UserDAO extends DBConnection {
	Connection conn=null;
	PreparedStatement pstmt=null;
	Statement stmt=null;
	ResultSet rs=null;
	
	public boolean checkRole(String userid) {
		boolean hasRole = false;
		try {
			conn = getConnection();
			
			String query = "select role from user where userid = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String userRole = rs.getString("role");
				
				if (userRole.equals("admin") ) {
					hasRole = true;
				} else {
					hasRole = false;
				}
			}
			
		} catch (Exception e) {
			System.out.println("role select fail");
			e.printStackTrace();
		}
		return hasRole;
	}
	
	

	public void showUserList() {

		try {
			conn = getConnection();

			stmt= conn.createStatement();
			rs=stmt.executeQuery("select * from user");

			while(rs.next()) {
				User user = new User(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6)
						);

				System.out.println(user);
			}
			close();
		}catch(Exception e) {
			System.out.println("select fail");
			e.printStackTrace();
		}
	}
	
	public ArrayList<User> getUserList() {
		ArrayList<User> userList=new ArrayList<User>();
		
		try {
			conn = getConnection();

			stmt= conn.createStatement();
			rs=stmt.executeQuery("select * from user");

			while(rs.next()) {
				User user = new User(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6));
				
				userList.add(user);
			}
			close();
		}catch(Exception e) {
			System.out.println("select fail");
			e.printStackTrace();
		}
		
		return userList;
	}
	
	public void deleteUser(String id) {
		conn = getConnection();
		
		String query= "delete from user where userid = ?";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("user delete fail");
			e.printStackTrace();
		};
		
	}
	
	public void updateUser(User user) {
		conn = getConnection();

		String query = "update user set userpass = ?, username = ?, userpart = ?, role = ? where userid = ?";
try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getUserpass());
			pstmt.setString(2, user.getUsername());
			pstmt.setString(3, user.getUserpart());
			pstmt.setString(4, user.getRole());
			pstmt.setString(5, user.getUserid());
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("user delete fail");
			e.printStackTrace();
		};
		
	}

	
	

	public void insertUser(User user) {
		conn = getConnection();

		String sql="insert into user(userid,userpass,username,userpart,role) values(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserid());
			pstmt.setString(2, user.getUserpass());
			pstmt.setString(3, user.getUsername());
			pstmt.setString(4, user.getUserpart());
			pstmt.setString(5, user.getRole());
		
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
	}
	
	
	public void close() {
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();

		}catch(Exception e) {
			System.out.println("insert fail");
		}

	}
}
