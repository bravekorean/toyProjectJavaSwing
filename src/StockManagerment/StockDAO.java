package StockManagerment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DB.DBConnection;


public class StockDAO extends DBConnection {
	Connection conn=null;
	PreparedStatement psmt=null;
	Statement stmt=null;
	ResultSet rs=null;
	
	public void close() {
		try {
			
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(psmt!=null) psmt.close();
			if(conn!=null) conn.close();
			
		}catch(Exception e) {
			System.out.println("close fail");
		}
	}
		
	public void showStockList() {
		
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from stock");
			
			while(rs.next()) {
				Stock stock = new Stock(rs.getInt(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getInt(4));
				System.out.println(stock);
				
			}
			
			close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("select fail");
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Stock> getUserList() {
		ArrayList<Stock> stockList=new ArrayList<Stock>();
		
		try {
			conn = getConnection();

			stmt= conn.createStatement();
			rs=stmt.executeQuery("select * from stock");

			while(rs.next()) {
				Stock Stock = new Stock(rs.getInt(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getInt(4));
				
				stockList.add(Stock);
			}
			close();
		}catch(Exception e) {
			System.out.println("selectList fail");
			e.printStackTrace();
		}
		
		return stockList;
	}
	
	public void selectCode(Stock stock) {
		try {
			conn = getConnection();
			String query = "select * from stock where proid = ?";
			psmt = conn.prepareStatement(query);
			psmt.setInt(1, stock.getProid());
			psmt.executeUpdate();
		
					
		} catch (Exception e) {
			System.out.println("selectCode fail");
			e.printStackTrace();
			
		}
	}
	
	public void insertStock(Stock stock) {
		conn = getConnection();

		String sql="insert into stock(proname,stock,price) values(?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, stock.getProname());
			psmt.setInt(2, stock.getStock());
			psmt.setInt(3, stock.getPrice());
		

			psmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
	}
	
	public void deleteStock(int proid) {
		
		conn = getConnection();
		
		String query = "delete from stock where proid = ?";
		
		try {
			psmt = conn.prepareStatement(query);
			psmt.setInt(1, proid);
			
			psmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("delete fail");
			e.printStackTrace();
		}
	}
	
	public void updateStockInfo(String proname, int stock, int price, int proid) {
		
		conn = getConnection();
		
		try {
			String query = "update stock set proname = ?,"
					+ " stock = ?, price = ? "
					+ "where proid = ?";
			
			psmt = conn.prepareStatement(query);
			psmt.setString(1, proname);
			psmt.setInt(2, stock);
			psmt.setInt(3, price);
			psmt.setInt(4, proid);
			
			psmt.executeUpdate();
		
			
		} catch(Exception e) {
			System.out.println("update fail");
			e.printStackTrace();
		}
	}
	
	public void updateStock(int stock, int proid) {
		
		conn = getConnection();
		
		try {
			
			String query = "update stock set stock = ? where proid = ?";
			psmt = conn.prepareStatement(query);
			psmt.setInt(1, stock);
			psmt.setInt(2, proid);
		} catch(Exception e) {
			System.out.println("update fail");
			e.printStackTrace();
		}
		
		
	}
	
public void updatePrice(int price, int proid) {
		
		conn = getConnection();
		
		try {
			
			String query = "update stock set price = ? where proid = ?";
			psmt = conn.prepareStatement(query);
			psmt.setInt(1, price);
			psmt.setInt(2, proid);
		} catch(Exception e) {
			System.out.println("update fail");
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	

	
	}
