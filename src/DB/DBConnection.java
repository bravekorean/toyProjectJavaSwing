package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	Connection conn=null;

	public Connection getConnection () {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url="jdbc:mysql://localhost:3306/stockdb";
			String id="root";
			String pwd="1234";
			conn = DriverManager.getConnection(url, id, pwd);
//			System.out.println("���� ����");
				
		}catch(Exception e) {
			System.out.println("���� ����");
			e.printStackTrace();
		}
		return conn;
	}
	
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new DBConnection().getConnection();

	}

}
