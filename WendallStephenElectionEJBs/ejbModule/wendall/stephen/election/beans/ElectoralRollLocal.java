package wendall.stephen.election.beans;

import javax.ejb.Local;
import javax.persistence.EntityManager;

import election.web.exceptions.ElectionException;
import online.election.entities.Voter;

@Local
public interface ElectoralRollLocal {

	public boolean authenticate(String voterName, String voterId) throws ElectionException;
	public Voter getVoterByName(EntityManager em, String voterName) throws ElectionException;
}
