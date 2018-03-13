import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/insertChat.do")
public class chatInsertServlet extends HttpServlet {

	String secsplit[];

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		
		
		
		String chat = request.getParameter("chat");
		PrintWriter out = response.getWriter();
		System.out.println("aakash"+chat+"aakash");
		chatDao cd = new chatDao();
		
		
		if(chat.equals("")) {
			System.out.println("aakash"+chat+"aakash");
			try {
				out.print(cd.getChat());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else {
	   
	   HttpSession session = request.getSession();
	   String username = (String) session.getAttribute("username");
	   try {
		cd.setChat(username, chat);
		out.print(cd.getChat());
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		//PrintWriter out = response.getWriter();
   
	}
	
	}
}