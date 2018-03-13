
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/register.do")
public class RegisterServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String name = request.getParameter("UserId");
		String password = request.getParameter("Password");
		
		RegisterDao rd = new RegisterDao();
		if(name.length() < 6 || password.length() < 6){
			
			request.setAttribute("msg", "UserId and password should be atleast 6 Characters");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			
		}
		
	
		else {
		
		if(rd.check(name)){
			request.setAttribute("msg", "UserId Already exists:(");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			
		}
		else {
			
			rd.register(name, password);
			request.setAttribute("msg", "Successfull!!!!!");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		}
		}


	}

	@Override
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub

		doPost(arg0, arg1);
	}

}