package online.election.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import online.election.exceptions.VoterNotRecognizedException;

import javax.persistence.NamedQuery;

@Entity
@Table (name="VOTER", schema ="ELECTION")
@NamedQuery( name="Voter.getByName", query="SELECT v FROM Voter v where v.name = :voterName")
public class Voter {
	@Id
	@Column(name="VPK")
	int key; 
	@Column(name="VNAME")
	String name;
	@Column(name="VID")
	String voterId;   // password
	@Column(name="VOTED")
	Boolean hasVoted;
	
	public Voter() {
		super();
	}
	
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	
	public String getVoterId() {
		return voterId;
	}
	
	public void setVoterId(String id) throws VoterNotRecognizedException {
		if ( id == null || id.isEmpty() ) {
			throw new VoterNotRecognizedException("Attempt to create voter with no student number");
		}
		voterId = id.trim();
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) throws VoterNotRecognizedException {
		if ( name == null || name.isEmpty()) {
			throw new VoterNotRecognizedException("Attempt to create voter with no name");
		}
		this.name = name.trim();
	}

	public boolean isVoted() {
		if ( getHasVoted() == null ) {
			return false;
		}
		return true;
	}
	
	public void setVoted(boolean b) {
		if (b ) { 
			setHasVoted( Boolean.TRUE);
		} else {
			setHasVoted( Boolean.FALSE);
		}
	}
	
	public Boolean getHasVoted() {
		return hasVoted;
	}	

	public void setHasVoted(Boolean hasVoted) {
		this.hasVoted = hasVoted;
	}

}
