package wendall.stephen.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import election.database.connection.DbConnect;
import election.web.model.Candidates;
import election.web.model.Student;
import election.web.model.StudentBody;
import wendall.stephen.exceptions.DataSourceConnectException;
import wendall.stephen.exceptions.DataSourceNameException;
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
			Student tmp = DbConnect.getStudentLogin(studentID, password);
			if (tmp != null)
			{
				request.getSession().setAttribute("candidates", Candidates.getInstance().getBallotNames());
				System.out.println(request.getSession().getAttribute("candidates"));
				request.getSession().setAttribute("student", studentID);
				request.getSession().setAttribute("voted", tmp.isVoted());
				rd = request.getRequestDispatcher("/signin.jsp");
				rd.forward(request, response);
			}
			else
			{
				request.setAttribute("error", "There is a problem. Student " + studentID + " cannot be authenticated.");
				rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		/*
		try {
			if (StudentBody.getInstance().authenticate(studentID, password))
			{
				if (StudentBody.getInstance().getStudent(studentID).isAdministrator())
				{
					request.getSession().setAttribute("admin", studentID);
					rd = request.getRequestDispatcher("/admin");
					rd.forward(request, response);
				}
				else
				{
					request.getSession().setAttribute("candidates", Candidates.getInstance().getBallotNames());
					System.out.println(request.getSession().getAttribute("candidates"));
					request.getSession().setAttribute("student", studentID);
					rd = request.getRequestDispatcher("/signin.jsp");
					rd.forward(request, response);
				}
			}
			else
				throw new StudentNotRecognizedException("Cannot authenticate");
			
		} catch (StudentNotRecognizedException e) {
			request.setAttribute("error", "There is a problem. Student " + studentID + " cannot be authenticated.");
			rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);		
		}		
		*/
			
	}

}
