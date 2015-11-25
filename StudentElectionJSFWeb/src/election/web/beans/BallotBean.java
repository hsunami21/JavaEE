package election.web.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import election.web.exceptions.StudentNotRecognizedException;
import election.web.exceptions.VotingException;
import election.web.model.Candidate;
import election.web.model.Candidates;
import election.web.model.Student;
import election.web.model.StudentBody;

@Named(value = "ballotBean")
@ApplicationScoped

public class BallotBean {
	private List<String> candidateNames;
	private String nextPage;

	@Inject
	private StudentBean studentBean;

	BallotBean() {
		setCandidateNames(Candidates.getInstance().getBallotNames());
	}

	public List<String> getCandidateNames() {
		return candidateNames;
	}

	public void setCandidateNames(List<String> candidateNames) {
		this.candidateNames = candidateNames;
	}

	public String getNextPage() {
		return nextPage;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}
	
	public List<Candidate> results() {
		List<Candidate> results = new ArrayList<Candidate>();
		Candidates candidates = Candidates.getInstance();
		for ( String cName : candidates.getBallotNames() ) {
			int votes = 0;
			try {
				votes = candidates.getVotes(cName);
			} catch ( VotingException ve) {
				System.out.println("No results for candidate with name " + cName );
				FacesMessage facesMessage = new FacesMessage(ve.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			}
			results.add( new Candidate(cName, votes) );
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		}
		return results;
	}

	public void vote(String candidateName) {
		String studentId = studentBean.getStudentId();
		if (studentId == null ) {		
			FacesMessage facesMessage = new FacesMessage("You must sign in before you vote");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			setNextPage("signin");
			return;
		}
		Student student = StudentBody.getInstance().getStudent(studentId);
		student.setVoted(true);
		try {
		studentBean.setStudentId(null);
		} catch (StudentNotRecognizedException e) {
			// clearing student ID so it does not appear on signin page
		}
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		try {
			for (String cName : candidateNames) {
				if (cName.equals(candidateName)) {
					Candidates.getInstance().voteFor(cName);
					Candidates.getInstance().printVoteCount(System.out);
					setNextPage("thankyou");
					return;
				}
			}
			throw new VotingException("Candidate name " + candidateName + " not recogonized");
		} catch (VotingException ve) {
			FacesMessage facesMessage = new FacesMessage(ve.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			setNextPage("errorPage");
		}
	}
}
