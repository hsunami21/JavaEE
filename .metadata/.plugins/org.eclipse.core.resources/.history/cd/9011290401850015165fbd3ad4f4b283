package college.courses.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import college.courses.exceptions.InvalidCommandException;
import wendall.stephen.asgn3.data.Course;
import wendall.stephen.asgn3.model.CatalogManager;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	// process get or add command from /main.jsp
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// make sure user is logged in
		HttpSession session = request.getSession();
		if (session.getAttribute("userName") == null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		// get form data - which button user clicked
		String command = request.getParameter("submit");
		try {
			switch (command) {
			// User request: get course by course code
			case "Get": {
				String code = request.getParameter("courseCode");
				if (code == null || code.isEmpty()) {
					throw new InvalidCommandException("code code cannot be empty");
				}
				CatalogManager cm = new CatalogManager();
				Course course = cm.getCourse(code.trim().toUpperCase());
//				cm.release();
				session.setAttribute("course", course);
				request.getRequestDispatcher("/displayCourse.jsp").forward(request, response);
				return;
			}
				// User request: add a new course
			case "Add": {
				session.setAttribute("course", null);
				request.getRequestDispatcher("/addCourse.jsp").forward(request, response);
				return;
			}
			default: {
				throw new InvalidCommandException("Unrecognized command:" + command);
			}
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
	}

}
