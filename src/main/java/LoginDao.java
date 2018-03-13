

import java.sql.*;
import java.util.ArrayList;

public class LoginDao {

	 String sql= "select * from users where userId=? and password=?";
     String usern = "";	 
     String imgPath = "";	
     String updateSql= "update users set status = ? where userId=?";
  
     public boolean check(String uname, String pass) {
		 
		try {
			
			DatabaseConnect db = new DatabaseConnect();
			
			PreparedStatement st = db.getConnection(sql);
			
			//System.out.println(uname + "  "+ pass);
			st.setString(1, uname);
			st.setString(2, pass);
			
			ResultSet rs = st.executeQuery();
		
			if(rs.next()) {
				
			usern =	rs.getString("userId");
			rs.close();	
			
			 st = db.getConnection(updateSql);	
			 st.setString(1, "on");
			 st.setString(2, uname);
				
		   st.executeUpdate();
		    
			db.closeConnection();
			
			
			
				return true;
			}
			
		} catch (Exception e)  {
			
			e.printStackTrace();
		}
		
		 
		 		return false;
		
	 }
     
     
     public void logout(String uname) {
		 
 		try {
 			
 			DatabaseConnect db = new DatabaseConnect();
 			
 			PreparedStatement st = db.getConnection(sql);	 
 			st = db.getConnection(updateSql);	
			 st.setString(1, "of");
			 st.setString(2, uname);
				
		   st.executeUpdate();
		    
			db.closeConnection();
			
	  
	 
}
catch (Exception e)  {
			
			e.printStackTrace();
		}
     }
}
