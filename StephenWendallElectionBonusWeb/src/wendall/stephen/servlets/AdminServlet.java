package wendall.stephen.servlets;

import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import election.web.model.Candidates;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher rd;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
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
		Candidates candidate  = Candidates.getInstance();
		
		try {
			int can1 = candidate.getVotes(candidate.getBallotNames().get(0));
			int can2 = candidate.getVotes(candidate.getBallotNames().get(1));
			int can3 = candidate.getVotes(candidate.getBallotNames().get(2));
			int total = can1 + can2 + can3;
			
			request.setAttribute("candidate1", can1);
			request.setAttribute("candidate2", can2);
			request.setAttribute("candidate3", can3);
			request.setAttribute("total", total);

			rd = request.getRequestDispatcher("/admin.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		
	}

}
