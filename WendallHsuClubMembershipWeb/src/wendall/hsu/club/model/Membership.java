package wendall.hsu.club.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wendall.hsu.exceptions.InvalidMemberData;
import wendall.hsu.exceptions.MembershipException;

public class Membership {
	private static Membership membership = null;
	private Map<String, Member> members;

	private Membership() throws InvalidMemberData {
		members = new HashMap<String, Member>();
		// for starters the only member is the club administrator
		// key is user name
		members.put("admin", new Member("admin", "Club", "Administrator", "3000") );
	}

	public static synchronized Membership getInstance() {
		try {
			if (membership == null) {
				membership = new Membership();
			}
		} catch (InvalidMemberData imd) {
			// exception should never happen
		}
		return membership;
	}
	
	// returns null if no member has this name/key
	public Member getMember( String userName ) {
		return members.get(userName);
	}
	
	// Overwrite is member with this name exists
	public void addMember (Member member ) {
		members.put( member.getUserName(), member );
	}
	
	public void removeMember ( String name) throws MembershipException {
		if (getMember( name) == null ) {
			throw new MembershipException("Attempt to remove unknown member " + name);
		}
		members.remove( name );
	}
	
	public List<String> getMemberNames() {
		List<String> memberNames = new ArrayList<String>();
		for ( Member member : members.values() ) {
			memberNames.add(member.getUserName() );
		}
		return memberNames;
	}
	
	public boolean authenticate( String userName, String password ) {
		Member member = members.get( userName );
		if ( member == null ) {
			return false;
		}
		return member.getPassword().equals(password);
	}
	
}
