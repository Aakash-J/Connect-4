
import java.sql.*;
import java.util.ArrayList;

public class OnlineUserDao {

	String sql = "select * from users where status=?";
	String challengeSql = "select * from game_status where Player2=? and Winner = ''";
	String topSql = "select Winner,count(Winner) as count from game_status where Winner != '' group by Winner order by count desc limit 5";
	String spectateSql = "select * from game_status where Player2!=? and Player1!=? and Winner = ''";
	String usern = "";
	String users = null;

	public String check() {

		try {

			DatabaseConnect db = new DatabaseConnect();

			PreparedStatement st = db.getConnection(sql);

			// System.out.println(uname + " "+ pass);
			st.setString(1, "on");

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				users = users + "~" + (rs.getString("userId"));

			}
			db.closeConnection();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return users;
	}

	public String checkChallenge(String user) {
		String game = "";
		String player ="";
		try {

			DatabaseConnect db = new DatabaseConnect();

			PreparedStatement st = db.getConnection(challengeSql);

			// System.out.println(uname + " "+ pass);
			st.setString(1, user);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				game = game + "~" + String.valueOf((rs.getInt("GameId")));
				game = game + ":" + rs.getString("Player1");

			}
			db.closeConnection();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return game;
	}
	
	public String getTopPlayers() {
		String top = "";
		
		try {

			DatabaseConnect db = new DatabaseConnect();

			PreparedStatement st = db.getConnection(topSql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				top = top + rs.getString("Winner")+ ":" + rs.getString("count") + "<br />" ; 
				

			}
			db.closeConnection();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return top;
	}
	
	public String spectate(String user) {
		String game = "";
		String player ="";
		try {

			DatabaseConnect db = new DatabaseConnect();

			PreparedStatement st = db.getConnection(spectateSql);

			// System.out.println(uname + " "+ pass);
			st.setString(1, user);
			st.setString(2, user);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				game = game + "~" + String.valueOf((rs.getInt("GameId")));
				game = game + ":" + rs.getString("Player1") + " vs " + rs.getString("Player2");

			}
			db.closeConnection();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return game;
	}

}
