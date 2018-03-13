import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/BoardServlet.do")
public class BoardServlet extends HttpServlet {

	String secsplit[];

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String gameId = request.getParameter("gameId");
		System.out.println("test:" + gameId);
		PrintWriter out = response.getWriter();
		String data1="";	

		char[][] board = new char[6][7];
		char[][] board1 = new char[6][7];
		GameDao gd = new GameDao();
		String turn = "";
		try {

			if (gd.getGame_status(Integer.valueOf(gameId))) {
				board = gd.getBoard(Integer.valueOf(gameId));
				turn = gd.getTurn(Integer.valueOf(gameId));

			} else{
				board1[0][0] = 'W';
				board = gd.getBoard(Integer.valueOf(gameId));
			}
			
			
			for (int i = board.length - 1; i >= 0; i--) {

				for (int j = 0; j < board[i].length; j++) {
					if ((board[i][j]) == 'R' || (board[i][j]) == 'Y')

						if (board[i][j] == 'R')
							data1 = data1 + i + "," + j + "," + "R" + "~";
						else
							data1 = data1 + i + "," + j + "," + "Y" + "~";
				}

			}
				
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String data = "";

		if (board1[0][0] == 'W') {
			String winner = "";
			try {
				winner = gd.getWinner(Integer.valueOf(gameId));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			data = "Won";
			turn = winner;
			System.out.println("pkaternsdakjdnaj12" + data + data1);
			out.println(data + "@" + turn + "@" +data1);
		} else if (board1[0][0] == 'D') {

			out.println("Match Draw");
		} else {

			for (int i = board.length - 1; i >= 0; i--) {

				for (int j = 0; j < board[i].length; j++) {
					if ((board1[i][j]) == 'R' || (board[i][j]) == 'Y')

						if (board[i][j] == 'R')
							data = data + i + "," + j + "," + "R" + "~";
						else
							data = data + i + "," + j + "," + "Y" + "~";
				}

			}
			out.println(data1 + "@" + turn + "@" +data1);

		}
	}

}