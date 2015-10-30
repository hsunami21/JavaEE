package wendall.stephen.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import election.database.connection.DbConnect;
import election.web.model.Candidates;
import election.web.model.StudentBody;
import wendall.stephen.exceptions.DataSourceConnectException;
import wendall.stephen.exceptions.DataSourceNameException;
import wendall.stephen.exceptions.VotingException;

/**
 * Servlet implementation class VoteServlet
 */
@WebServlet("/vote")
public class VoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoteServlet() {
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
		String student = (String) request.getSession().getAttribute("student");
		try {
				//System.out.println(request.getParameter("Vote").toString());
				if (!(boolean)request.getSession().getAttribute("voted"))
				{
					if (DbConnect.voteFor(student, request.getParameter("Vote").toString()))
					{
						request.getSession().setAttribute("voted", true);
						RequestDispatcher rd = request.getRequestDispatcher("/vote.jsp");
						rd.forward(request, response);
					}
				}
				else
					throw new VotingException("Already Voted");
				//Candidates.getInstance().voteFor(request.getParameter("Vote").toString());
				//StudentBody.getInstance().getStudent(student).setVoted(true
			
		} catch (VotingException | DataSourceNameException | DataSourceConnectException | SQLException e) {
			request.setAttribute("error", "There is a problem. You cannot vote twice.");
			
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
		
		
		
	}

}
