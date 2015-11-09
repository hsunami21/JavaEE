package college.courses.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wendall.stephen.asgn3.data.Course;
import wendall.stephen.asgn3.data.Professor;
import wendall.stephen.asgn3.model.CatalogManager;

@WebServlet("/add")
public class AddCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddCourseServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	// process add course from data on addCourse.jsp
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// make sure user is logged in
		HttpSession session = request.getSession();
		if (session.getAttribute("userName") == null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		// User clicked Cancel
		if (request.getParameter("cancel") != null) {
			session.setAttribute("course", null);
			request.getRequestDispatcher("/main.jsp").forward(request, response);
			return;
		}
		try {
			// process form data
			Course course = null;
			String courseCode = request.getParameter("courseCode");
			if (courseCode != null) {
				courseCode = courseCode.trim().toUpperCase();
			}
			String courseTitle = request.getParameter("courseTitle");
			if (courseTitle != null) {
				courseTitle = courseTitle.trim();
			}
			int capacity = 0;
			int enrolled = 0;
			String input = request.getParameter("capacity");
			if (input != null) {  // possible NumberFormat Exception
				capacity = Integer.parseInt(input.trim());
			}
			// build course object and insert into database
			CatalogManager cm = new CatalogManager();
			course = new Course(courseCode, courseTitle);
			course.setCapacity(capacity);
			course.setEnrolled(enrolled);
			course.setProfessor(getProfFromList(request.getParameter("profName")));
			System.out.println("Adding " + course);
			cm.addCourse(course);
//			cm.release();
			session.setAttribute("course", null);
			Integer cc = (Integer) getServletContext().getAttribute("courseCount");
			getServletContext().setAttribute("courseCount", cc + 1);
			request.getRequestDispatcher("/main.jsp").forward(request, response);
			return;

		} catch (Exception e) {
			e.printStackTrace(System.out);
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
	}
	
	// get professor with ID selected from the drop down list
	private Professor getProfFromList(String profName) {		
		if (profName.equals("TBA")) {
			return null;
		}
		int pid = Integer.parseInt(profName);
		System.out.println("PROF ID = " + pid);
		for (Professor prof : (List<Professor>) getServletContext().getAttribute("professors")) {
			if (pid == prof.getProfId()) {
				return prof;
			}
		}
		// should never reach the next line
		return null;
	}
}
