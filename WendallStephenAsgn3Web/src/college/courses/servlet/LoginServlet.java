package college.courses.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import college.courses.exceptions.InvalidDataException;
import wendall.stephen.asgn3.model.CatalogManager;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	// set up info for banner 
	public void init() throws ServletException {
		try {
			CatalogManager cm = new CatalogManager();
			System.out.println("INITIATING LOG IN");
			int courseCount = cm.countCourses();
			getServletContext().setAttribute("courseCount", courseCount);
			System.out.println("COURSE COUNT QUERY WORKS");
			getServletContext().setAttribute("professors", cm.getProfessorList() );
			System.out.println("PROFESSOR QUERY WORKS");
			Date lastUpdated = Calendar.getInstance().getTime();
			getServletContext().setAttribute("lastUpdated", lastUpdated);
		} catch (InvalidDataException e) {
			e.printStackTrace();
		}
	}
	
	// used for sign out only
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
		rd.forward(request, response);
	}

    // process sign in from data on login.jsp
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("userName");
		if (name != null) {
			request.setAttribute("message", "You are already signed in as " + name);
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		name = request.getParameter("userName"); 
		if (name == null || name.isEmpty() ) {
			request.setAttribute("message", "We really want to know your name");
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
			return;
		} else {
			session.setAttribute("userName", name);
			RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
			rd.forward(request, response);
		}
	}
}
