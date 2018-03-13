
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/online.do")
public class OnlineUserServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		// System.out.println("aakash");
		String user = request.getParameter("Player1");
		OnlineUserDao os = new OnlineUserDao();

		String users = os.check();

		String game = os.checkChallenge(user);
		String top = os.getTopPlayers();
		String spectate  = os.spectate(user);
		System.out.println(game);
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		// session.setAttribute("name", dao.usern);
		String resp = users + "|" + game + "|" + top + "|" + spectate;
		out.println(resp);

	}

}