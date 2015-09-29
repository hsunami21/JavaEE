package wendallhsu.asgn1.dto;

import wendallhsu.asgn1.exceptions.InvalidDataException;

public class Professor {

	private String profName;
	
	public Professor(String profName) {
		
		this.profName = profName;
	}

	public String getProfName() {
		return profName;
	}

	public void setProfName(String profName) throws InvalidDataException {
		
		if (profName.equalsIgnoreCase("") || profName == null)
			throw new InvalidDataException("Professor name cannot be empty.");
		
		this.profName = profName;
	}
	
	public String toString() {
		return profName;
	}
	
}
