package college.courses.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import college.courses.exceptions.InvalidDataException;
import wendall.stephen.asgn3.data.Course;
import wendall.stephen.asgn3.data.Professor;
import wendall.stephen.asgn3.model.CatalogManager;

@WebServlet("/update")
public class UpdateCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateCourseServlet() {
		super();
	}

	// process delete course in doGet - works because delete needs no form data
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// make sure user has logged in 
		HttpSession session = request.getSession();
		if (session.getAttribute("userName") == null ) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		// make sure user has selected a course 
		 if (session.getAttribute("course") == null ) {
			request.getRequestDispatcher("/main.jsp").forward(request, response);
			return;
		}
		// current course is stored in HttpSession by MainServlet
		Course course = (Course) session.getAttribute("course");
		try {
			CatalogManager cm = new CatalogManager();
			System.out.println("Deleting " + course);
			cm.deleteCourse(course.getCourseCode());
			System.out.println("DELETE COURSE WORKS");
			session.setAttribute("course", null);
			Integer cc = (Integer) getServletContext().getAttribute("courseCount");
			getServletContext().setAttribute("courseCount", cc - 1);
			request.getRequestDispatcher("/main.jsp").forward(request, response);
			return;
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			e.printStackTrace(System.out);
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
	}

	// process update course
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// make sure user is logged in
		HttpSession session = request.getSession();
		if (session.getAttribute("userName") == null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		// do nothing if user clicked cancel
		String command = request.getParameter("submit");
		if (command.equals("Cancel")) {
			request.getRequestDispatcher("/main.jsp").forward(request, response);
			return;
		}
		// make sure user has selected a course 
		 if (session.getAttribute("course") == null ) {
			request.getRequestDispatcher("/main.jsp").forward(request, response);
			return;
		}
		// current course is stored in HttpSession by MainServlet
		Course course = (Course) session.getAttribute("course");
		try {
			CatalogManager cm = new CatalogManager();
			// process form data
			String courseTitle = request.getParameter("courseTitle");
			if (courseTitle != null) {
				course.setCourseTitle(courseTitle.trim());
			}
			int capacity = 0;
			int enrolled = 0;
			String input = request.getParameter("capacity");
			if (input != null) {
				// may throw NumberFormatException
				capacity = Integer.parseInt(input.trim());
			}
			course.setCapacity(capacity);
			course.setEnrolled(enrolled);
			// on update a new professor may be created
			Professor professor = null;
			String profName = request.getParameter("profName").trim();
			// use professor name typed in to create or get professor by name
			// if no professor name entered use professor select from drop down list
			if (profName.isEmpty()) {
				professor = getProfFromList(request.getParameter("profId"));
			} else {
				professor = getProfFromName(profName);
			}
			course.setProfessor(professor);
			System.out.println("Updating " + course);
			cm.updateCourse(course);
			System.out.println("UPDATE COURSE WORKS");
			getServletContext().setAttribute("professors", cm.getProfessorList());
			session.setAttribute("course", null);
			request.getRequestDispatcher("/main.jsp").forward(request, response);
			return;
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			e.printStackTrace(System.out);
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
	}

	private Professor getProfFromName(String profName) throws InvalidDataException {
		// professor name entered in entry field
		String firstName = "";
		String lastName = "";
		int space = profName.indexOf(' ');
		{
			firstName = profName.substring(0, space);
		}
		if (space < profName.length()) {
			lastName = profName.substring(space).trim();
		}
		for (Professor prof : (List<Professor>) getServletContext().getAttribute("professors")) {
			if (prof.getFirstName().equals(firstName) && prof.getLastName().equals(lastName)) {
				return prof;
			}
			
		}
		
		Professor p = new Professor(firstName, lastName);
		p.setProfId(getProfId());
		CatalogManager cm = new CatalogManager();
		cm.addProfessor(p);
		
		return p;
	}

	private Professor getProfFromList(String profName) {
		// professor name selected from the drop down list
		if (profName.equals("TBA")) {
			return null;
		}
		int pid = Integer.parseInt(profName);
		for (Professor prof : (List<Professor>) getServletContext().getAttribute("professors")) {
			if (pid == prof.getProfId()) {
				return prof;
			}
		}
		// should never reach the next line
		return null;
	}
	
	private int getProfId() {
		int max = 0;
		for (Professor prof : (List<Professor>)getServletContext().getAttribute("professors")) {
			if (prof.getProfId() > max)
				max = prof.getProfId();
		}
		
		System.out.println("GETPROFID(): " + (max+1));
		return max + 1;
	 }
}
