import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/GameServlet.do")
public class GameServlet extends HttpServlet {

	String secsplit[];

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String column = request.getParameter("column");
		String userName = request.getParameter("userName");
		String color = request.getParameter("color");
		String gameId = request.getParameter("gameId");
		// System.out.println("usernaem is "+ userName);
		PrintWriter out = response.getWriter();
		String[] cord = column.split("-");
		char[][] board = null;
		ConnectFourGame cg = new ConnectFourGame();
		GameDao gd = new GameDao();
		String turn = "";
		try {
			board = cg.playGame(Integer.parseInt(cord[1]), userName, color.charAt(0), gameId);
			turn = gd.getTurn(Integer.valueOf(gameId));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch 
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String data = "";

		if (board[0][0] == 'W') {

			data = "Won";
			System.out.println("pkaternsdakjdnaj" + data);
			out.println(data + "@" + turn);
		} else if (board[0][0] == 'D') {

			out.println("Match Draw");
		} else {
			for (int i = board.length - 1; i >= 0; i--) {

				for (int j = 0; j < board[i].length; j++) {
					if ((board[i][j]) == 'R' || (board[i][j]) == 'Y')

						if (board[i][j] == 'R')
							data = data + i + "," + j + "," + "R" + "~";
						else
							data = data + i + "," + j + "," + "Y" + "~";
				}

			}
			out.println(data + "@" + turn + "@" + data);
		}

	}

}






