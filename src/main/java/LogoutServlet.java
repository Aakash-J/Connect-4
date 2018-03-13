

import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
@SuppressWarnings("serial")

@WebServlet(urlPatterns = "/logout.do")
public class LogoutServlet extends HttpServlet {  
        protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException {  
            response.setContentType("text/html");  
         
            
            HttpSession session=request.getSession();  
            
            LoginDao ld = new LoginDao();
            ld.logout(session.getAttribute("username").toString());
            session.invalidate();  
         //  request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);  
            response.sendRedirect("/login.jsp");
            System.out.print("You are successfully logged out!");  
              
        }
}  