package wendall.hsu.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wendall.hsu.club.model.Membership;
import wendall.hsu.exceptions.InvalidMemberData;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		String command = request.getParameter("submit");
		String name = (String) session.getAttribute("userName");
		
		try {
			switch (command) {
				case "Sign out": {
					request.getSession().invalidate();
					request.getRequestDispatcher("/index.jsp").forward(request, response);
					return;
				}
				case "Quit club": {
					Membership.getInstance().removeMember(name);
					request.getSession().invalidate();
					request.getRequestDispatcher("/index.jsp").forward(request, response);
					return;
				}
			}
		} catch (Exception e) {
			System.out.println("Error signing out");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("userName");
		
		if (name != null) {
			request.setAttribute("message", "Attempt to sign in when already signed in as " + name);
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}

		String userName = request.getParameter("userName").trim();
		String password = request.getParameter("password").trim();
		
		try {
			name = request.getParameter("userName"); 
			if (name == null || name.isEmpty() ) {
				request.setAttribute("message", "Please enter a user name");
				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
				return;
			} else if (Membership.getInstance().authenticate(userName, password)) {
				session.setAttribute("userName", Membership.getInstance().getMember(userName).getUserName());
				session.setAttribute("members", Membership.getInstance().getMemberNames());
				RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
				rd.forward(request, response);
			}
			else {
				throw new InvalidMemberData();
			}
		} catch (InvalidMemberData imd) {
			request.setAttribute("message", "Could not authenticate " + userName);
			request.getRequestDispatcher("/errorLogin.jsp").forward(request, response);
			return;
		}
		
		
		
		
	}

}
