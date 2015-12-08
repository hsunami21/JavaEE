package election.web.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import election.web.exceptions.ElectionException;
import online.election.exceptions.CandidateException;
import wendall.stephen.election.beans.CandidatesLocal;
import wendall.stephen.election.beans.ElectionManagerLocal;
import wendall.stephen.web.helper.VoteCounter;

/**
 * Servlet implementation class VoteServlet
 */
@WebServlet("/vote")
public class VoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private CandidatesLocal candidates;
	@EJB
	private ElectionManagerLocal electionManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VoteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String voterName = (String) session.getAttribute("voterName");
		try {
		if (voterName == null) {
			throw new ElectionException("You must sign in before voting");
		} 
		String candidateName = null;
		for (String cName : candidates.getCandidateNames()) {
			if (request.getParameter(cName) != null) { 
				candidateName = cName;
				break;
			}
		}
		if ( candidateName == null  ) {
			throw new ElectionException("Spoiled Ballot: no candidate selected");
		}
			electionManager.castBallot(voterName, candidateName );
			session.invalidate();
			//candidates.printVoteCount(System.out);
			
			// using a POJO helper just to show how POJOs use EJBs
			VoteCounter vc = new VoteCounter();
			vc.printVoteCount();
			
			request.getRequestDispatcher("/done.html").forward(request,	response);
			return;
		} catch ( ElectionException | CandidateException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/errorPage.jsp").forward(request,
					response);
			return;
		}
	}
}
