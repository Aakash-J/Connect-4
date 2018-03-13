
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String name = request.getParameter("InputUserName1");
		String password = request.getParameter("InputPassword1");

		LoginDao dao = new LoginDao();

		if (dao.check(name, password)) {
			HttpSession session = request.getSession();
			session.setAttribute("username", name);

			request.getRequestDispatcher("/chat.jsp").forward(request, response);
		} else {

			request.getRequestDispatcher("/login.jsp").forward(request, response);

		}

	}

	@Override
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub

		doPost(arg0, arg1);
	}

}