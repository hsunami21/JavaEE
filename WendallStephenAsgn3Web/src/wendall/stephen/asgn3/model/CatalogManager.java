package wendall.stephen.asgn3.model;

import java.util.List;

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

	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("WendallStephenAsgn3Persistence");
	EntityManager em = factory.createEntityManager();
	
	
	//  @Override to reduce need to update throws clauses, CourseCatalog interface is dropped
	public Course getCourse(String courseCode) throws CourseNotFoundException, InvalidDataException {
		Course course = em.find(Course.class, courseCode);
		
		if (course == null) { 
			throw new InvalidDataException(courseCode + " does not exist!"); 
		} 
		
		TypedQuery<Course> tq1 = em.createNamedQuery("getCourse", Course.class);
		tq1.setParameter("code", courseCode);
		course = tq1.getSingleResult();
			
		return course;
	}
	
	//  @Override to reduce need to update throws clauses, CourseCatalog interface is dropped
	public Course addCourse(Course c) throws DuplicateCourseException, InvalidDataException {
		Course course = em.find(Course.class, c.getCourseCode());		
		if (course != null) { 
			throw new InvalidDataException(c.getCourseCode() + " already exists!"); 
		} 
		
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();

		return c;
	}

	// dynamic SQL to save precompiling statements statement run less frequently
	public Professor addProfessor(Professor p) throws InvalidDataException {
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();

		return p;
	}

	//  @Override to reduce need to update throws clauses, CourseCatalog interface is dropped
	public Course updateCourse(Course c) throws CourseNotFoundException, InvalidDataException {
		Course course = em.find(Course.class, c.getCourseCode());		
		
		System.out.println(course);
		
		em.getTransaction().begin();
		course.setCourseTitle(c.getCourseTitle());
		course.setCapacity(c.getCapacity());
		course.setEnrolled(c.getEnrolled());
		course.setProfessor(c.getProfessor());		
		em.getTransaction().commit();

		System.out.println(course);
		return course;
	}

	//  @Override to reduce need to update throws clauses, CourseCatalog interface is dropped
	public Course deleteCourse(String courseCode) throws CourseNotFoundException, InvalidDataException {
		Course course = em.find(Course.class, courseCode);
		em.getTransaction().begin();
		em.remove(course);
		em.getTransaction().commit();

		return course;
	}

	// utility method not in CourseCatalog: return number of courses in COURSE table
	public int countCourses() {
		TypedQuery<Long> tq = em.createNamedQuery("countCourses", Long.class);
		return tq.getSingleResult().intValue();
	}

	// utility method not in CourseCatalog: return ArrayList of professors
	public List<Professor> getProfessorList() throws InvalidDataException {
		TypedQuery<Professor> tq = em.createNamedQuery("getAllProfs", Professor.class);
		List<Professor> professors = tq.getResultList();
		return professors;
	}
}
