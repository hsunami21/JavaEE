package wendall.stephen.asgn3.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import college.courses.exceptions.InvalidDataException;

@Entity
@Table (name="COURSE", schema="COLLEGE")
@NamedQueries ({
	@NamedQuery (name="countCourses", query="SELECT COUNT(c) FROM Course c"),
	@NamedQuery (name="getCourse", query="SELECT c FROM Course c WHERE c.courseCode =  :code")
})
public class Course implements Serializable {

	private static final long serialVersionUID = 1L;
	private String courseCode = null;
	private String courseTitle = null;
	private int capacity = 0;
	private int enrolled = 0;
	private Professor professor = null;

	public Course() {}
	
	public Course( String courseCode) throws InvalidDataException {
		super();
		setCourseCode(courseCode);
		setCapacity(25);
		setEnrolled(0);
	}
	
	public Course(String courseCode, String courseTitle) throws InvalidDataException  {
		this( courseCode );
		setCourseTitle(courseTitle);
	}
	
	public Course(String code, String title, Professor professor) throws InvalidDataException   {
		this(code, title);
		this.professor = professor;
	}
	
	@Id
	@Column (name="COURSECODE", nullable=false)
	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) throws InvalidDataException {
		if ( courseCode == null || courseCode.isEmpty() ) {
			throw new InvalidDataException("Course must have a course code");
		}
		// match regular expression: 3 or 4 letters + optional space + 3 digits
		if ( ! courseCode.matches("[a-z|A-Z]{3,4} ?[0-9]{3}") )
			throw new InvalidDataException("Course code must be 3 or 4 letters followed by 3 or 4 digits ");
		this.courseCode = courseCode;
	}

	@Column (name="COURSETITLE", nullable=false)
	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		// a course can have an empty or null title
		this.courseTitle = courseTitle;
	}

	@Column (name="CAPACITY")
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) throws InvalidDataException {
		if (capacity < 0 || capacity > 200 ) {
			throw new InvalidDataException("Course capacity must be 0 to 200 students");
		}
		this.capacity = capacity;
	}

	@Column (name="ENROLLED")
	public int getEnrolled() {
		return enrolled;
	}

	public void setEnrolled(int enrolled) throws InvalidDataException {
		if ( enrolled < 0 || enrolled > getCapacity() ) {
			throw new InvalidDataException("Enrolled must between between 0 and course capacity");
		}
		this.enrolled = enrolled;
	}

	@ManyToOne (fetch=FetchType.LAZY)
	@JoinColumn (name="PROFID")
	public Professor getProfessor () {
		return professor;
	}

	public void setProfessor(Professor professor) {
		// professor is null when professor not assigned
		this.professor = professor;
	}

	public String toString() {
		String output = getCourseCode() + ": " + getCourseTitle() ;
		output += " [Enrollment " + getEnrolled() + "/" + getCapacity() +"]";
		if (getProfessor() != null ) {
			output += " Professor " + getProfessor();
		}
		return output;
	}

	public boolean equals(Course c) {
		if ( ! getCourseCode().equals( c.getCourseCode() ) ) {
			return false;
		}
		if ( ! getCourseTitle().equals( c.getCourseTitle() ) ) {
			return false;
		}
		if ( getCapacity() != c.getCapacity() ) {
			return false;
		}
		if ( getEnrolled() != c.getEnrolled() ) {
			return false;
		}
		if ( getProfessor() == null  ) {
			return  c.getProfessor() == null  ; 
		}
		if ( c.getProfessor() == null  ) {
			return  getProfessor() == null  ; 
		}
		return getProfessor().equals( c.getProfessor() );
	}
	
}
