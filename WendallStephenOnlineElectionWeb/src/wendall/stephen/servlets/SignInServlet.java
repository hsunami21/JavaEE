package wendall.stephen.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import election.web.model.StudentBody;
import wendall.stephen.exceptions.StudentNotRecognizedException;

/**
 * Servlet implementation class SignInServlet
 */
@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher rd;
	//private javax.servlet.RequestDispatcher rd;
       
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
		StudentBody sb = StudentBody.getInstance();

		System.out.println("Do Get Called");
			String studentID = request.getParameter("studentID").trim();
			String password = request.getParameter("password").trim();
			if (sb.authenticate(studentID, password)) {
				request.getSession().setAttribute("student", studentID);
				RequestDispatcher rd = request.getRequestDispatcher("/signin.jsp");
				rd.forward(request, response);
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		StudentBody sb = StudentBody.getInstance();
			String studentID = request.getParameter("studentID").trim();
			String password = request.getParameter("password").trim();

				if (sb.authenticate(studentID, password))
				{
					request.getSession().setAttribute("student", studentID);
					rd = request.getRequestDispatcher("/signin.jsp");
					rd.forward(request, response);
				}
				else
				{
					rd = request.getRequestDispatcher("/error.jsp");
					request.setAttribute("error", "Username/Password combination is wrong!");
					rd.forward(request, response);
				}
					
			
	}

}
