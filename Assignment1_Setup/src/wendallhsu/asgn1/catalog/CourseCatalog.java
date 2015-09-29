package wendallhsu.asgn1.catalog;

import wendallhsu.asgn1.dto.Course;
import wendallhsu.asgn1.exceptions.CourseNotFoundException;
import wendallhsu.asgn1.exceptions.DuplicateCourseException;

public interface CourseCatalog {
	
	public Course addCourse(Course c) throws DuplicateCourseException;
	public Course getCourse(String courseCode) throws CourseNotFoundException;
	public Course updateCourse(Course c) throws CourseNotFoundException;
	public Course deleteCourse(String courseCode) throws CourseNotFoundException;

}
