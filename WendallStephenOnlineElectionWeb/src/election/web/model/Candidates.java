package election.web.model;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wendall.stephen.exceptions.VotingException;

public class Candidates {
	private static Candidates candidates = null;
	Map <String, Integer> vodeCount = null;

	private Candidates() {
		vodeCount = new HashMap<String, Integer>();
		vodeCount.put( "Aakash Khan", 0 );
		vodeCount.put( "Marion Wells", 0 );
		vodeCount.put( "Damien Yu", 0 );
	}

	public static synchronized Candidates getInstance() {
		if ( candidates == null ) {
			candidates = new Candidates();
		}
		return candidates;
	}
	
	public List<String> getBallotNames() {
		return new ArrayList<String> ( vodeCount.keySet() );
	}
	
	public int getVotes( String name ) throws VotingException {
		if( vodeCount.get( name ) == null ) {
			throw new VotingException("Candidate name " + name + " not regonized");
		}
		return vodeCount.get( name );
	}
	
	public void voteFor( String name ) throws VotingException {
		int votes = getVotes( name ) + 1;
		vodeCount.put( name , votes );		
	}
	
	public void printVoteCount( PrintStream out ) {
		out.print("Vote count so far: ");
		for ( String s : getBallotNames() ) {
			out.printf("[%-12s%4d] ", s, vodeCount.get(s) );
		}
		out.println();
	}
	
}
