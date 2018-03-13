
import java.sql.*;
import java.util.Calendar;

public class chatDao {


	DatabaseConnect db;
	



	void setChat(String userName, String chat)
			throws ClassNotFoundException, SQLException {

		db = new DatabaseConnect();
		Calendar currenttime = Calendar.getInstance();
		Date sqldate = new Date((currenttime.getTime()).getTime());

		String sql = "insert into chatdata(userId,chat)" + " VALUES" + "(?,?)";
		PreparedStatement st = db.getConnection(sql);

		st.setString(1, userName);
		st.setString(2, chat);
		st.executeUpdate();
		db.closeConnection();

	}
	
	String getChat() throws ClassNotFoundException, SQLException {
		String chat=" ";
		db = new DatabaseConnect();
		String sqlChat = "select IFNULL(GROUP_CONCAT(CONCAT(userId , ': ' , chat,'\n') SEPARATOR  '\n' ),'') as chat from chatdata";
		PreparedStatement st = db.getConnection(sqlChat);
        
		ResultSet rs = st.executeQuery();

		if (rs.next()) {
			
			chat = rs.getString("chat");
		}
		
		db.closeConnection();
		
		return chat;
	}

	
}
