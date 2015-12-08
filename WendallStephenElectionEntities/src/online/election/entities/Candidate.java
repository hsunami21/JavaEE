package online.election.entities;

import java.io.Serializable;
import javax.persistence.*;

import online.election.exceptions.CandidateException;


/**
 * The persistent class for the candidate database table.
 * 
 */
@Entity
@Table (name="CANDIDATE", schema="ELECTION")
@NamedQueries( {
@NamedQuery(name="Candidate.findAll", query="SELECT c FROM Candidate c"),
@NamedQuery(name="getCandidateByName", query="SELECT c from Candidate c where c.name = :candidateName") 
} )
public class Candidate implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private int cid;
	@Column(name="CNAME")
	private String name;
	@Column(name="CVOTES")
	private int votes;

	public Candidate() {
	}

	public int getCid() {
		return this.cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) throws CandidateException {
		if ( name == null || name.isEmpty() )
			throw new CandidateException ("Candidate must have a printable name");
		this.name = name;
	}

	public int getVotes() {
		return this.votes;
	}

	public void setVotes(int votes) throws CandidateException {
		if ( votes < 0 ) {
			throw new CandidateException("Votes for a candidate must be a postive number");
		}
		this.votes = votes;
	}

}