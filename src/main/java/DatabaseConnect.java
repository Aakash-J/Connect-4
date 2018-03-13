

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnect {
	
	
	String url = "jdbc:mysql://localhost/connect4?autoReconnect=true&useSSL=false";
	String username = "root";
	String password = "Aakash00@@";
	Connection con;
	 
	 
	 PreparedStatement getConnection(String sql) throws SQLException, ClassNotFoundException {
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			PreparedStatement st = con.prepareStatement(sql);
			
			return st;
				
		}
	 
	 void closeConnection() throws SQLException
	 {
		 con.close();
	 }
}

