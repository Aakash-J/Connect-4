import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/InitiateGameServlet.do")
public class InitiateGame extends HttpServlet {

	String secsplit[];

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String Player2 = request.getParameter("Player2").trim();
		String Player1 = request.getParameter("Player1").trim();

		PrintWriter out = response.getWriter();
		GameDao gd = new GameDao();
		HttpSession session = request.getSession();

		try {
			String checkGame = gd.check4ExistingGame(Player1, Player2);
			if(checkGame.equals("")) {
			
			if (!(Player2.equals(""))) {
				System.out.println(Player2 + Player1);
				out = response.getWriter();
				session = request.getSession();
				session.setAttribute("Color", 'R');
				session.setAttribute("P2", Player2);

				try {
					int newId = gd.setGame_Staus(Player1, Player2);
					session.setAttribute("gameId", newId);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				String gameId = request.getParameter("gameId").trim();
				// System.out.println("Game id is " + gameId);
				out = response.getWriter();
				try {
					String players[] = gd.getPlayerInfo(Integer.valueOf(gameId));
					System.out.println(players[1]);
					session = request.getSession();
					session.setAttribute("Color", 'Y');
					session.setAttribute("gameId", gameId);
					session.setAttribute("P2", players[0]);
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
			}
			// String[] cord = column.split("-");
			// char[][] board = null;
			// ConnectFourGame cg = new ConnectFourGame();
			// try {
			// board = cg.playGame(Integer.parseInt(cord[1]),userName,'R');
			// } catch (NumberFormatException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// } catch (ClassNotFoundException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// } catch (SQLException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			//
			// out.println("hi");
			out.print("false");
			}
			else {
				
				session.setAttribute("gameId", checkGame);
				session.setAttribute("Color", 'R');
				session.setAttribute("P2", Player2);
				out.print("true");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch bloc
			e.printStackTrace();
		}
       
		//request.getRequestDispatcher("/chat.jsp").forward(request, response);

	}

}