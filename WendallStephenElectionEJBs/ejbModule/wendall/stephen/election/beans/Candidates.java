package wendall.stephen.election.beans;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import election.web.exceptions.ElectionException;
import online.election.entities.Candidate;


/**
 * Session Bean implementation class Candidates
 */
@Stateless
public class Candidates implements CandidatesLocal {
	@PersistenceContext (name = "WendallStephenElectionEntities")
	EntityManager em;

	private static List<String> candidateNames = null;
	
	public Candidates () {
	}

	public List<String> getCandidateNames() throws ElectionException {
		if (candidateNames == null) {
			Query q = em.createNamedQuery("Candidate.findAll");
			List<Candidate> candidates = q.getResultList();
			candidateNames = new ArrayList<String>();
			for (Candidate candidate : candidates) {
				candidateNames.add(candidate.getName());
			}
		}		
		return candidateNames;
	}

	public void printVoteCount(PrintStream out) throws ElectionException {
		out.print("Vote count so far: ");
		Query q = em.createNamedQuery("Candidate.findAll");
		List<Candidate> candidates = q.getResultList();
		for ( Candidate c : candidates ) {
			out.printf("[%-12s%4d] ", c.getName(), c.getVotes() );
		}
		out.println();
	}


}
