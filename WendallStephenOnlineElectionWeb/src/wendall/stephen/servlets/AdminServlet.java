package wendall.stephen.servlets;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

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
		Candidates candidates  = Candidates.getInstance();
		
		try {
			int total = 0;
			ArrayList<Integer> votes = new ArrayList<>();
			
			for (int i = 0; i < candidates.getBallotNames().size() ; i++)
			{
				votes.add(candidates.getVotes(candidates.getBallotNames().get(i)));
				total += candidates.getVotes(candidates.getBallotNames().get(i));
			}
			request.getSession().setAttribute("candidates", candidates.getBallotNames());
			request.getSession().setAttribute("votes", votes);
			request.setAttribute("total", total);

			RequestDispatcher rd = request.getRequestDispatcher("/admin.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		
	}

}
