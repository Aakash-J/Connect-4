
import java.sql.*;
import java.util.Calendar;

public class GameDao {

	int perc;

	String ins;
	String desc;
	String section[];
	DatabaseConnect db;
	char[][] board = new char[6][7];

	char[][] getBoard(int gameId) throws ClassNotFoundException, SQLException {

		// Connect to Database
		db = new DatabaseConnect();

		String sql = "select Move,color from board where GameId = ?";
		// System.out.println("game id is " + gameId);
		PreparedStatement st = db.getConnection(sql);
		st.setInt(1, gameId);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {

			String move = rs.getString("Move");

			String[] xAndy = move.split(",");

			int x = Integer.valueOf(xAndy[0]);
			int y = Integer.valueOf(xAndy[1]);

			System.out.println(x + " " + y);

			board[x][y] = (rs.getString("color")).charAt(0);

		}

		db.closeConnection();
		return board;

	}

	void setBoard(int gameId, String playerId, String move, String dt, char color)
			throws ClassNotFoundException, SQLException {

		db = new DatabaseConnect();
		Calendar currenttime = Calendar.getInstance();
		Date sqldate = new Date((currenttime.getTime()).getTime());

		String sql = "insert into board(GameId,PlayerId,Move,color)" + " VALUES" + "(?,?,?,?)";
		PreparedStatement st = db.getConnection(sql);

		st.setInt(1, gameId);
		st.setString(2, playerId);
		st.setString(3, move);
		st.setString(4, String.valueOf(color));

		st.executeUpdate();
		db.closeConnection();

		String s[] = getPlayerInfo(gameId);

		db = new DatabaseConnect();
		sql = "update game_status set Turn = ? where gameId = ?";
		st = db.getConnection(sql);

		st.setInt(2, gameId);

		if (s[0].equals(playerId)) {

			st.setString(1, s[1]);

		} else {
			st.setString(1, s[0]);
		}

		st.executeUpdate();
		db.closeConnection();

	}

	int setGame_Staus(String player1Id, String player2Id) throws ClassNotFoundException, SQLException {

		db = new DatabaseConnect();

		String sql = "select max(GameId) as MaxId from game_status";

		PreparedStatement st = db.getConnection(sql);
		ResultSet rs = st.executeQuery();
		int newId = 0;

		if (rs.next()) {

			newId = rs.getInt("MaxId");
			newId = newId + 1;

		} else {
			newId = 1;
		}

		db.closeConnection();

		db = new DatabaseConnect();
		// Calendar currenttime = Calendar.getInstance();
		// Date sqldate = new Date((currenttime.getTime()).getTime());

		String sqlInsert = "insert into game_status(GameId,Player1,Player2,Winner,turn)" + " VALUES" + "(?,?,?,?,?)";
		st = db.getConnection(sqlInsert);

		st.setInt(1, newId);
		st.setString(2, player1Id);
		st.setString(3, player2Id);
		st.setString(4, "");
		st.setString(5, player1Id);

		st.executeUpdate();
		db.closeConnection();
		return newId;

	}

	void updateGame_status(int gameId, String userName) throws ClassNotFoundException, SQLException {

		db = new DatabaseConnect();
		String sqlInsert = "update Game_status set Winner = ? where GameId = ?";
		PreparedStatement st = db.getConnection(sqlInsert);
		st.setString(1, userName);
		st.setInt(2, gameId);

		st.executeUpdate();
		db.closeConnection();

	}

	boolean getGame_status(int gameId) throws ClassNotFoundException, SQLException {

		db = new DatabaseConnect();
		String sqlInsert = "select * from Game_status where GameId = ?";
		PreparedStatement st = db.getConnection(sqlInsert);

		st.setInt(1, gameId);

		ResultSet rs = st.executeQuery();

		if (rs.next()) {

			if (rs.getString("Winner").equals("")) {
				db.closeConnection();
				return true;
			} else {

				db.closeConnection();
				return false;
			}
		}
		db.closeConnection();
		return true;
	}

	String[] getPlayerInfo(int gameId) throws ClassNotFoundException, SQLException {

		db = new DatabaseConnect();
		String[] s = new String[2];
		String sqlSelect = "select Player1,Player2 from Game_status where GameId = ?";
		PreparedStatement st = db.getConnection(sqlSelect);

		st.setInt(1, gameId);

		ResultSet rs = st.executeQuery();

		if (rs.next()) {

			s[0] = rs.getString("Player1");
			s[1] = rs.getString("Player2");

		}
		db.closeConnection();
		return s;

	}

	String getTurn(int gameId) throws ClassNotFoundException, SQLException {

		db = new DatabaseConnect();
		String turn = "";
		String sqlSelect = "select Turn from Game_status where GameId = ?";
		PreparedStatement st = db.getConnection(sqlSelect);

		st.setInt(1, gameId);

		ResultSet rs = st.executeQuery();

		if (rs.next()) {

			turn = rs.getString("Turn");

		}
		db.closeConnection();
		return turn;
	}

	String getWinner(int gameId) throws ClassNotFoundException, SQLException {

		db = new DatabaseConnect();
		String winner = "";
		String sqlSelect = "select Winner from Game_status where GameId = ?";
		PreparedStatement st = db.getConnection(sqlSelect);

		st.setInt(1, gameId);

		ResultSet rs = st.executeQuery();

		if (rs.next()) {

			winner = rs.getString("Winner");

		}
		db.closeConnection();
		return winner;
	}
	String check4ExistingGame(String player1,String player2) throws ClassNotFoundException, SQLException {

		db = new DatabaseConnect();
		String gameId = "";
		String winner = "";
		String sqlSelect = "select * from game_status where Player1 = ? and Player2 = ? and Winner=''";
		PreparedStatement st = db.getConnection(sqlSelect);

		st.setString(1,player1);
		st.setString(2,player2);
		ResultSet rs = st.executeQuery();

		if (rs.next()) {
            
			gameId = String.valueOf(rs.getInt("gameId"));
			db.closeConnection();
			return gameId;

		}
		db.closeConnection();
		return gameId;
	}

}
