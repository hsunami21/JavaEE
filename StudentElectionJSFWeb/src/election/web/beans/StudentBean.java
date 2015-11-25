package election.web.beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import election.web.exceptions.StudentNotRecognizedException;
import election.web.model.Student;
import election.web.model.StudentBody;

@Named(value = "studentBean")
@SessionScoped
public class StudentBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String studentId;
	private String password;
	private boolean hasVoted;
	private boolean administrator;

	private String nextPage;

	public StudentBean() {
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String id) throws StudentNotRecognizedException {
		if (id == null || id.isEmpty()) {
			throw new StudentNotRecognizedException("Attempt to create voter with no student number");
		}
		studentId = id.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws StudentNotRecognizedException {
		if (password == null || password.isEmpty()) {
			throw new StudentNotRecognizedException("Attempt to create voter with no name");
		}
		this.password = password.trim();
	}

	public boolean isAdministrator() {
		return administrator;
	}

	public boolean isVoted() {
		return hasVoted;
	}

	public void setVoted(boolean hasVoted) {
		this.hasVoted = hasVoted;
	}

	public String getNextPage() {
		return nextPage;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

	public void signin() {
		StudentBody sb = StudentBody.getInstance();
		if (sb.authenticate(getStudentId(), getPassword())) {
			Student student = sb.getStudent(studentId);
			if (student.isAdministrator()) {
				setNextPage("results");
				return;
			}
			if (student.isVoted()) {
				System.out.println("Student trying to vote twice " + getStudentId());
				FacesMessage facesMessage = new FacesMessage(getStudentId() + " has already voted");
				FacesContext.getCurrentInstance().addMessage("login", facesMessage);
				setNextPage("signin");
				return;
			}
			System.out.println("Successful login: " + student);
			setNextPage("ballot");
			return;
		}
		FacesMessage facesMessage = new FacesMessage("Authentication failed, please try again");
		FacesContext.getCurrentInstance().addMessage("login", facesMessage);
		setNextPage("signin");
		return;
	}
}
