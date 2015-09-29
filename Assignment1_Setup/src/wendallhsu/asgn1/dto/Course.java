package wendallhsu.asgn1.dto;

import wendallhsu.asgn1.exceptions.InvalidDataException;

public class Course {
	
	private String courseCode;
	private String courseTitle;
	private Professor professor;
	
	public Course(String courseCode) throws InvalidDataException {
		super();

		if (courseCode.equalsIgnoreCase("") || courseCode == null)
			throw new InvalidDataException("Course code cannot be empty.");
		else
		{
			this.courseCode = courseCode;
			this.courseTitle = null;
			this.professor = null;
		}
	}
	
	public Course(String courseCode, String courseTitle) throws InvalidDataException {
		super();
		
		if (courseCode.equalsIgnoreCase("") || courseCode == null)
			throw new InvalidDataException("Course code cannot be empty.");
		else
		{
			this.courseCode = courseCode;
			this.courseTitle = courseTitle;
			this.professor = null;
		}
	
	}
	
	public Course(String courseCode, Professor professor) throws InvalidDataException {
		super();
		
		if (courseCode.equalsIgnoreCase("") || courseCode == null)
			throw new InvalidDataException("Course code cannot be empty.");
		else
		{
			this.courseCode = courseCode;
			this.professor = professor;
		}
	}
	
	public Course(String courseCode, String courseTitle, Professor professor) throws InvalidDataException {
		super();
		
		if (courseCode.equalsIgnoreCase("") || courseCode == null)
			throw new InvalidDataException("Course code cannot be empty.");
		else
		{
			this.courseCode = courseCode;
			this.courseTitle = courseTitle;
			this.professor = professor;
		}
	}

	public String getCourseCode() {
		return courseCode;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) throws InvalidDataException {
		
		if (courseTitle.equalsIgnoreCase("") || courseTitle == null)
			throw new InvalidDataException("Course title cannot be empty.");
		
		this.courseTitle = courseTitle;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public String toString() {
		
		if (getProfessor() != null)
			return courseCode + " - " + courseTitle + " - Prof. " + professor;
		else
			return courseCode + " - " + courseTitle + " - Prof. N/A";
	}

}
