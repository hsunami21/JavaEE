package election.web.model;

// this JavaBean was added to support the results for the administrator

public class Candidate {
	String name;
	int votes;

	public Candidate(String name, int votes) {
		setName(name);
		setVotes(votes);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

}
