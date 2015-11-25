package wendall.hsu.club.model;

import wendall.hsu.exceptions.InvalidMemberData;

public class Member {
	protected String userName;
	protected String firstName;
	protected String lastName;
	protected String password;

	public Member(String userName, String firstName, String lastName, String password) throws InvalidMemberData {
		setUserName(userName);
		setFirstName(firstName);
		setLastName(lastName);
		setPassword(password);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) throws InvalidMemberData {
		if ( userName == null || userName.trim().isEmpty() ) {
			throw new InvalidMemberData ("No user name supplied");
		}
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) throws InvalidMemberData {
		if ( firstName == null || firstName.trim().isEmpty() ) {
			throw new InvalidMemberData ("No user name supplied");
		}
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) throws InvalidMemberData {
		if ( lastName == null || lastName.trim().isEmpty() ) {
			throw new InvalidMemberData ("No user name supplied");
		}
		this.lastName = lastName;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws InvalidMemberData {
		if ( password == null || password.trim().isEmpty() ) {
			throw new InvalidMemberData ("No password supplied");
		}
		this.password = password;
	}
	
	public boolean equals (Member m ) {
		if ( ! getUserName().equals(getUserName() ) )  {
			return false;
		}
		if ( ! getFirstName().equals(getFirstName() ) )  {
			return false;
		}
		if ( ! getLastName().equals(getLastName() ) )  {
			return false;
		}
		if ( ! getPassword().equals(getUserName()) )  {
			return false;
		}
		return true;
	}
	

}
