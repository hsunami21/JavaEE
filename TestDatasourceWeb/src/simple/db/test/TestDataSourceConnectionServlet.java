package simple.db.test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestDataSourceConnectionServlet
 */
@WebServlet("/TestDS")
public class TestDataSourceConnectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestDataSourceConnectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jndiName = request.getParameter("jndiname");
		RequestDispatcher rd = request.getRequestDispatcher("results.jsp");
		String message ="Testing a data source connection";
		try {
		DataSourceTester dst = new DataSourceTester(jndiName);
		message ="Connection to datasource successful";
		} catch ( Exception e ) {
			message = "Exception:  " + e.getClass() + ". ";
			message += "See console for stack trace.";
		}
		request.setAttribute("message", message);
		rd.forward(request, response);
	}

}
