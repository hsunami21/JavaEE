package wendallhsu.asgn1.catalog;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import wendallhsu.asgn1.dto.Course;
import wendallhsu.asgn1.exceptions.CourseNotFoundException;
import wendallhsu.asgn1.exceptions.DuplicateCourseException;

public class CatalogManager implements CourseCatalog {
	
	private static CatalogManager cm = null;
	private Map<String, Course> courses = null;

	private CatalogManager()
	{
		courses = new ConcurrentHashMap<>();
	}
	
	// global way to access single instance
	public synchronized static CatalogManager getInstance()
	{
		if (cm == null)
			cm = new CatalogManager();
		return cm;
	}
	
	public Collection<Course> getAllCourses() {

		return courses.values();
	}
	
	@Override
	public Course addCourse(Course c) throws DuplicateCourseException {
		// TODO Auto-generated method stub

		if (courses.containsKey(c.getCourseCode()))
			throw new DuplicateCourseException(c.getCourseCode() + " already exists.");
		else
		{
			System.out.println(c.getCourseCode() + " added successfully.");
			return courses.put(c.getCourseCode(), c);
		}
			
	}

	@Override
	public Course getCourse(String courseCode) throws CourseNotFoundException {
		// TODO Auto-generated method stub

		if (courses.containsKey(courseCode))
			return courses.get(courseCode);
		else
			throw new CourseNotFoundException("Course not found.");

	}

	@Override
	public Course updateCourse(Course c) throws CourseNotFoundException {
		// TODO Auto-generated method stub

		if (courses.containsValue(c))
		{
			courses.put(c.getCourseCode(), c);
			return courses.get(c.getCourseCode());
		}
		else
			throw new CourseNotFoundException("Course not found.");

	}

	@Override
	public Course deleteCourse(String courseCode) throws CourseNotFoundException {
		// TODO Auto-generated method stub
		
		if (courses.containsKey(courseCode))
		{
			courses.remove(courseCode);
			System.out.println(courseCode + " deleted successfully.");
			return courses.get(courseCode);
		}
		else
			throw new CourseNotFoundException("Course not found.");
		
	}
	

}
