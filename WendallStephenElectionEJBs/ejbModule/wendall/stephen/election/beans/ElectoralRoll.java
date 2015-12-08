package wendall.stephen.election.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import election.web.exceptions.ElectionException;
import online.election.entities.Voter;


/**
 * Session Bean implementation class ElectoralRoll
 */
@Stateless
public class ElectoralRoll implements ElectoralRollLocal {
	@PersistenceContext (name = "WendallStephenElectionEntities")
	EntityManager em;

    public ElectoralRoll() {
        // TODO Auto-generated constructor stub
    }
    
    public boolean authenticate(String voterName, String voterId) throws ElectionException {
		Voter voter = getVoterByName(em, voterName);
		if (voter == null) {
			return false;
		}
		if (!voter.getVoterId().equals(voterId)) {
			return false;
		}
		// you could argue the next condition belongs in login servlet
		if (voter.isVoted()) {
			throw new ElectionException("Attempt to vote twice: " + voterName);
		}
		return true;
	}

	// passing entity managers as argument so method can be called inside a transaction
	public Voter getVoterByName(EntityManager em, String voterName) throws ElectionException {
		Query q = em.createNamedQuery("Voter.getByName");
		// assuming all voters have a unique name
		q.setParameter("voterName", voterName);
		List<Voter> voters = q.getResultList();
		if (voters.size() != 1) {
			return null;
		}
		return voters.get(0);
	}

}
