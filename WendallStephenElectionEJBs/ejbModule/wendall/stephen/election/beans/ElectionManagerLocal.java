package wendall.stephen.election.beans;

import javax.ejb.Local;

import election.web.exceptions.ElectionException;
import online.election.exceptions.CandidateException;

@Local
public interface ElectionManagerLocal {

	public void castBallot(String voterName, String candidateName) throws ElectionException, CandidateException;
	public String getVoteCount() throws ElectionException;

}
