package wendall.hsu.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wendall.hsu.club.model.Member;
import wendall.hsu.club.model.Membership;
import wendall.hsu.exceptions.InvalidMemberData;
import wendall.hsu.exceptions.MembershipException;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();

		String userName = request.getParameter("userName").trim();
		String firstName = request.getParameter("firstName").trim();
		String lastName = request.getParameter("lastName").trim();
		String password = request.getParameter("password").trim();
		
		if (userName == null || userName.isEmpty() ) {
			request.setAttribute("message", "Please enter a user name");
			RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
			rd.forward(request, response);
			return;
		}
		
		try {
			Member member = new Member(userName, firstName, lastName, password);
			Membership.getInstance().addMember(member);
			session.setAttribute("userName", userName);
			session.setAttribute("members", Membership.getInstance().getMemberNames());
			RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
			rd.forward(request, response);
		} catch  (InvalidMemberData imd) {
			System.out.println("Error: Could not create member");
		}
		
	}

}
