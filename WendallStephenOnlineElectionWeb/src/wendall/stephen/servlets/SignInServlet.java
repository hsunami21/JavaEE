package wendall.stephen.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import election.web.model.Candidates;
import election.web.model.StudentBody;
import wendall.stephen.exceptions.StudentNotRecognizedException;

/**
 * Servlet implementation class SignInServlet
 */
@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInServlet() {
        super();
        System.out.println("Gets Here");
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
			
		String studentID = request.getParameter("studentID").trim();
		String password = request.getParameter("password").trim();

		try {
			if (StudentBody.getInstance().authenticate(studentID, password))
			{
				if (StudentBody.getInstance().getStudent(studentID).isAdministrator())
				{
					request.getSession().setAttribute("admin", studentID);
					RequestDispatcher rd = request.getRequestDispatcher("/admin");
					rd.forward(request, response);
				}
				else
				{
					request.getSession().setAttribute("candidates", Candidates.getInstance().getBallotNames());
					System.out.println(request.getSession().getAttribute("candidates"));
					request.getSession().setAttribute("student", studentID);
					RequestDispatcher rd = request.getRequestDispatcher("/signin.jsp");
					rd.forward(request, response);
				}
			}
			else
				throw new StudentNotRecognizedException("Cannot authenticate");
			
		} catch (StudentNotRecognizedException e) {
			request.setAttribute("error", "There is a problem. Student " + studentID + " cannot be authenticated.");
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);		
		}		
			
	}

}
