package election.web.model;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import election.web.exceptions.VotingException;

public class Candidates {
	private static Candidates candidates = null;
	Map <String, Integer> voteCount = null;

	private Candidates() {
		voteCount = new HashMap<String, Integer>();
		voteCount.put( "Aakash Khan", 0 );
		voteCount.put( "Marion Wells", 0 );
		voteCount.put( "Damien Yu", 0 );
	}

	public static synchronized Candidates getInstance() {
		if ( candidates == null ) {
			candidates = new Candidates();
		}
		return candidates;
	}
	
	public List<String> getBallotNames() {
		return new ArrayList<String> ( voteCount.keySet() );
	}
	
	public int getVotes( String name ) throws VotingException {
		if( voteCount.get( name ) == null ) {
			throw new VotingException("Candidate name " + name + " not regonized");
		}
		return voteCount.get( name );
	}
	
	public void voteFor( String name ) throws VotingException {
		int votes = getVotes( name ) + 1;
		voteCount.put( name , votes );		
	}
	
	public void printVoteCount( PrintStream out ) {
		out.print("Vote count so far: ");
		for ( String s : getBallotNames() ) {
			out.printf("[%-12s%4d] ", s, voteCount.get(s) );
		}
		out.println();
	}
	
}
