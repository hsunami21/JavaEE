package wendall.stephen.asgn3.model;

import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import college.courses.exceptions.CourseNotFoundException;
import college.courses.exceptions.DuplicateCourseException;
import college.courses.exceptions.InvalidDataException;
import wendall.stephen.asgn3.data.Course;
import wendall.stephen.asgn3.data.Professor;

public class CatalogManager {	
	private static InitialContext ctx = null;
	
	// NULL POINTER EXCEPTION WITH THIS
//	private static EntityManagerFactory factory = null;
//	
//	public CatalogManager() { 
//		if ( factory == null ) {  
//			try { 
//				ctx = new InitialContext(); 
//				factory = (EntityManagerFactory) ctx.lookup("java:/CollegeDS"); 
//			} catch (NamingException ne) { 
//				ne.printStackTrace(); 
//			} 
//		}
//	}
		
	// UNEXPECTED TOKEN WITH COUNTCOURSES WITH THIS
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("WendallStephenAsgn3Persistence");
	EntityManager em = factory.createEntityManager();
	
	
//	private static DataSource ds = null;
//	private Connection conn = null;
	
//// SQL statements for precompilation
//	private static String getCourseSQL = "SELECT * FROM COLLEGE.COURSE WHERE CourseCode =  ? ";
//	private PreparedStatement getCourse = null;
//	private static String getProfessorSQL = "SELECT * FROM COLLEGE.PROFESSOR WHERE PROFID = ? ";
//	private PreparedStatement getProfessor = null;
//	private static String addCourseSQL = "INSERT INTO COLLEGE.COURSE (COURSECODE, COURSETITLE, CAPACITY, ENROLLED, PROFID ) VALUES (?, ?, ?, ?, ? )";
//	private PreparedStatement addCourse = null;
////	private static String addProfessorSQL = "INSERT INTO COLLEGE.PROFESSOR ( GIVENNAME, FAMILYNAME ) VALUES ( ? ? )";
////	PreparedStatement addProfessor = null;
//	private static String updateCourseSQL = "UPDATE COLLEGE.COURSE SET COURSETITLE = ?, CAPACITY = ?, ENROLLED = ?, PROFID = ? WHERE COURSECODE = ? ";
//	private PreparedStatement updateCourse = null;
//	private static String deleteCourseSQL ="DELETE FROM COLLEGE.COURSE WHERE COURSECODE = ?";
//	private PreparedStatement deleteCourse = null;
//	private static String countCoursesSQL ="SELECT COUNT(*) FROM COLLEGE.COURSE";
//	private PreparedStatement countCourses = null;
//	public static String getAllProfsSQL = "SELECT * FROM COLLEGE.PROFESSOR";
//	public PreparedStatement getAllProfs = null;
	
//	static { // for efficiency do jndi operations only once
//		try {
//			ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:/CollegeDS");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
	
	// get data source connection and precompile commonly used SQL statements
//	public CatalogManager() throws DataSourceNameException, DataSourceConnectException {
//		try {
//			/* Minimal gain from prepared statements over dynamic SQL 
//			 * in a Web app where every HTTP request takes a new connection 
//			 * from the datasource pool. 	*/ 
//			conn = ds.getConnection();
//			getCourse = conn.prepareStatement(getCourseSQL);
//			getProfessor = conn.prepareStatement(getProfessorSQL);
//			addCourse = conn.prepareStatement(addCourseSQL);
//			updateCourse = conn.prepareStatement(updateCourseSQL);
//			deleteCourse = conn.prepareStatement(deleteCourseSQL);
//			countCourses = conn.prepareStatement(countCoursesSQL);
//			getAllProfs = conn.prepareStatement(getAllProfsSQL);			
//		} catch (SQLException e) {
//			System.err.println(e.getMessage());
//			throw new DataSourceConnectException("Sorry, we are experiencing technical difficulties");
//		}
//	}

	/* This design lets client code do several DB operations with one pooled connection and
	 * makes no assumption how client code combines operations in a logical unit of work. 
	 * In other words, the client (a servlet) uses a new CatalogManager for each user request. 
	 * When the client creates the CatalogManager with the new operator, it requests a  
	 * connection from the pool. Because the Java language does not have explicit destructors,
	 * the client must tell the CatalogManager object when to release the connect back to the 
	 * pool by calling the release method.
	 */
	
	// close a connection to release it to the data source pool
//	public void release() throws SQLException {
//		conn.close();
//		conn = null;
//		
//	}

	//  @Override to reduce need to update throws clauses, CourseCatalog interface is dropped
	public Course getCourse(String courseCode) throws CourseNotFoundException, InvalidDataException {
		Course course = null;
		int profId = 0;
//		ResultSet rs = null;
//		try {	
//			getCourse.setString(1, courseCode);
//			rs = getCourse.executeQuery();
//			// zero or one course may be found because course code is primary key
//			if (! rs.next()) {
//				throw new CourseNotFoundException("No course with code " + courseCode);
//			}
//			// build Course object from row in COURSE table
//			String courseTitle = rs.getString("COURSETITLE");
//			profId = rs.getInt("PROFID");
//			course = new Course(courseCode, courseTitle);
//			course.setCapacity(rs.getInt("CAPACITY"));
//			course.setEnrolled(rs.getInt("ENROLLED"));
//			// profId 0 in DB table to professor field null in Course object
//			if (profId != 0) {
//				getProfessor.setInt(1, profId);
//				rs = getProfessor.executeQuery();
//				//  one professor must be found because profId is primary key
//				rs.next();
//				String firstName = rs.getString("GIVENNAME");
//				String lastName = rs.getString("FAMILYNAME");
//				// build Professor object from row in PROFESSOR table
//				course.setProfessor(new Professor(profId, firstName, lastName));
//			}
			TypedQuery<Course> tq1 = em.createNamedQuery("getCourse", Course.class);
			tq1.setParameter("code", courseCode);
			course = tq1.getSingleResult();
//			profId = course.getProfessor().getProfId();
//			if (profId != 0) {
//				TypedQuery<Professor> tq2 = em.createNamedQuery("getProf", Professor.class);
//				tq2.setParameter("profId", profId);
//				Professor prof = tq2.getSingleResult();
//				course.setProfessor(prof);
//			}
			
			return course;
//		} catch (CourseNotFoundException ide) {
//			// should never happen reading data from database
//			return null;
//		}
	}
//
	//  @Override to reduce need to update throws clauses, CourseCatalog interface is dropped
	public Course addCourse(Course c) throws DuplicateCourseException, InvalidDataException {
//		try {
//			getCourse(c.getCourseCode());
//			throw new DuplicateCourseException("Attempt to add second course with code " + c.getCourseCode());
//		} catch (CourseNotFoundException cnfe) {
			em.getTransaction().begin();
			em.persist(c);
			em.getTransaction().commit();
//			addCourse.setString(1, c.getCourseCode());
//			addCourse.setString(2, c.getCourseTitle());
//			addCourse.setInt(3, c.getCapacity());
//			addCourse.setInt(4, c.getEnrolled());
//			// business rule: when cannot add a professor and course at the same time 
//			// a new course can have existing professor or professor TBA
//			Professor professor = c.getProfessor();
//			if (professor == null) {
//				addCourse.setNull(5, java.sql.Types.INTEGER);
//			} else {
//				addCourse.setInt(5, professor.getProfId());
//			}
//			addCourse.executeUpdate();
//		}
		return c;
	}
//	
//	// dynamic SQL to save precompiling statements statement run less frequently
//	public Professor addProfessor(Professor p) throws SQLException {
//		Statement s = conn.createStatement();
//		s.executeUpdate("INSERT INTO COLLEGE.PROFESSOR ( GIVENNAME, FAMILYNAME ) VALUES ('"
//				+ p.getFirstName() + "', '" + p.getLastName() + "')" );
//		ResultSet rs = s.executeQuery("SELECT MAX(PROFID) FROM COLLEGE.PROFESSOR");
//		rs.next();
//		int profId = rs.getInt(1);
//		try {
//		p.setProfId(profId);
//		System.out.println("Added Professor " + p.getProfId() + ": " + p );
//		} catch (InvalidDataException ide) {
//			// profId assigned by database 
//		}
//		return p;
//	}
//
	//  @Override to reduce need to update throws clauses, CourseCatalog interface is dropped
	public Course updateCourse(Course c) throws CourseNotFoundException, InvalidDataException {
		// throw exception if course not in database
		Course oldCourse = getCourse(c.getCourseCode());
//		// check if anything to change
//		if (oldCourse.equals(c)) {
//			return oldCourse;
//		}
//		try {
		oldCourse = em.find(Course.class, c.getCourseCode());
		System.out.println(oldCourse);
		em.getTransaction().begin();
		oldCourse.setCourseTitle(c.getCourseTitle());
		oldCourse.setCapacity(c.getCapacity());
		oldCourse.setEnrolled(c.getEnrolled());
		oldCourse.setProfessor(c.getProfessor());
//			// to ensure professor cannot be added unless course also added
//			// turn on JDBC transactional processing
//			conn.setAutoCommit(false);
//			updateCourse.setString(1, c.getCourseTitle());
//			updateCourse.setInt(2, c.getCapacity());
//			updateCourse.setInt(3, c.getEnrolled());
			// update may cause insert new professor
			Professor professor = c.getProfessor();
			// for referencial integrity on COURSE.PROFID insert professor first
			if (professor != null) {
				if (professor.getProfId() == 0) {
					em.persist(professor);
				}
			}
//				updateCourse.setInt(4, professor.getProfId());
//			} else {
//				updateCourse.setNull(4, java.sql.Types.INTEGER);
//			}
		em.getTransaction().commit();

//			// update the course 
//			updateCourse.setString(5, c.getCourseCode());
//			updateCourse.executeUpdate();
//			// commit transaction if no database exceptions
//			conn.commit();
			System.out.println(oldCourse);
			return oldCourse;
//		} catch (InvalidDataException ide) {
//			// undo add professor if course cannot be updated
//			conn.rollback();
//			throw ide;
//		}
//		// turn off JDBC transaction processing
//		} finally { 
//			conn.setAutoCommit(false);
	}
//
	//  @Override to reduce need to update throws clauses, CourseCatalog interface is dropped
	public Course deleteCourse(String courseCode) throws CourseNotFoundException, InvalidDataException {
//		// throw exception if course not in database
		Course course = getCourse(courseCode);
		course = em.find(Course.class, courseCode);
		em.getTransaction().begin();
		em.remove(course);
		em.getTransaction().commit();
		
//		deleteCourse.setString( 1,  courseCode);
//		deleteCourse.executeUpdate();
		return course;
	}
//
	// utility method not in CourseCatalog: return number of courses in COURSE table
	public int countCourses() {
//		ResultSet rs = countCourses.executeQuery();
//		rs.next();
		TypedQuery<Long> tq = em.createNamedQuery("countCourses", Long.class);
		return tq.getSingleResult().intValue();
	}
//
//
	// utility method not in CourseCatalog: return ArrayList of professors
	public List<Professor> getProfessorList() throws InvalidDataException {
		TypedQuery<Professor> tq = em.createNamedQuery("getAllProfs", Professor.class);
		List<Professor> professors = tq.getResultList();
		return professors;
	}
}
