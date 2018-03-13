

import java.sql.*;
import java.util.ArrayList;

public class RegisterDao {

	 String sql= "select * from users where userId=?";
     String usern = "";	 
     String imgPath = "";	
     String registerSql= "insert into users values (?,?,'of')";
  
     public boolean check(String uname) {
		 
		try {
			
			DatabaseConnect db = new DatabaseConnect();
			
			PreparedStatement st = db.getConnection(sql);
			
			//System.out.println(uname + "  "+ pass);
			st.setString(1, uname);
			
			
			ResultSet rs = st.executeQuery();
		
			if(rs.next()) {
				
			
				return true;
			}
			
		} catch (Exception e)  {
			
			e.printStackTrace();
		}
		
		 
		 		return false;
		
	 }
     
     
     public void register(String uname,String password) {
		 
 		try {
 			
 			DatabaseConnect db = new DatabaseConnect();
 			
 			PreparedStatement st = db.getConnection(registerSql);	 
 				
			 st.setString(1, uname);
			 st.setString(2, password);
				
		   st.executeUpdate();
		   	db.closeConnection();
			
	  
	 
}
catch (Exception e)  {
			
			e.printStackTrace();
		}
     }
}
