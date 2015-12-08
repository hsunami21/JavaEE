package wendall.stephen.election.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import election.web.exceptions.ElectionException;
import online.election.entities.Candidate;
import online.election.entities.Voter;
import online.election.exceptions.CandidateException;


/**
 * Session Bean implementation class ElectionManager
 */
@Stateless
public class ElectionManager implements ElectionManagerLocal {
	@PersistenceContext (name = "WendallStephenElectionEntities")
	EntityManager em;

    public ElectionManager() throws ElectionException {
    
    }
    
    public String getVoteCount() throws ElectionException {
		String tmp = "Vote count so far:";
		Query q = em.createNamedQuery("Candidate.findAll");
		List<Candidate> candidates = q.getResultList();
		for ( Candidate c : candidates ) {
			tmp += "\n" + c.getName() + "   " + c.getVotes();
		}
		return tmp;
	} 

	// A transaction is required to update both VOTER and CANDIDATE tables
	// Advanced thought: transactional processing should ensure vote count not corrupted
	// by multithreading but that may depend on locking strategy of database
	// Synchronizing this method could hurt performance at peak voting times
	public void castBallot(String voterName, String candidateName) throws ElectionException, CandidateException {
		Query q = em.createNamedQuery("getCandidateByName");
		q.setParameter("candidateName", candidateName);
		List<Candidate> candidates = q.getResultList();
		if (candidates.size() != 1) {
			// assuming all candidates have a unique name
			throw new ElectionException("Attempt to vote for unknown candidate");
		}
		Candidate candidate = candidates.get(0);
		candidate.setVotes(candidate.getVotes() + 1);
		Voter voter = new ElectoralRoll().getVoterByName(em, voterName);
		if (voter.isVoted()) {
			throw new ElectionException("Attempt to vote twice: " + voterName);
		}
		voter.setVoted(true);
	}
}
