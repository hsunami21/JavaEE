package wendall.stephen.asgn3.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import college.courses.exceptions.InvalidDataException;

@Entity
@Table (name="PROFESSOR", schema="COLLEGE")
@NamedQueries ({
	@NamedQuery (name="getAllProfs", query="SELECT p FROM Professor p")
})
public class Professor {
	private String firstName = null;
	private String lastName = null;
	private int profId =0 ;
	private Set<Course> courseCollection = new HashSet<Course>(0);
	
	public Professor() throws InvalidDataException {}
	
	// professor created with no profId when creating a new professor
	// primary key profId set by database using autoincrement
	public Professor( String firstName, String lastName)
			throws InvalidDataException {
		setFirstName(firstName);
		setLastName(lastName);
	}

	// professor created with profId when building with data from database
	public Professor( int profId, String firstName, String lastName)
			throws InvalidDataException {
		this(firstName, lastName);
		setProfId(profId);
	}
	
	public Professor( int profId, String firstName, String lastName, Set<Course> courseCollection)
			throws InvalidDataException {
		this(profId, firstName, lastName);
		this.courseCollection = courseCollection;
	}

	public void setProfId( int profId) throws InvalidDataException {
		if ( profId < 1000 || profId > 1999) {
			throw new InvalidDataException("Proressor must have 4-digit ID starting 1ddd");
		}
		this.profId = profId;
	}
	
	@Id
	@Column (name="PROFID", nullable=false)
	public int getProfId () {
		return profId;
	}
	
	@Column (name="GIVENNAME", nullable=false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) throws InvalidDataException {
		if (firstName == null || firstName.isEmpty()) {
			throw new InvalidDataException("Proressor must have a first name");
		}
		this.firstName = firstName;
	}
	
	@Column (name="FAMILYNAME", nullable=false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) throws InvalidDataException {
		if (lastName == null || lastName.isEmpty()) {
			throw new InvalidDataException("Proressor must have a last name");
		}
		this.lastName = lastName;
	}
	public String toString() {
		return firstName + " " + lastName;
	}
	
	public boolean equals( Professor p) {
		if ( ! getFirstName().equals(p.getFirstName() ) ) {
			return false;
		}
		if ( ! getLastName().equals(p.getLastName() ) ) {
			return false;
		}
		if ( getProfId() == p.getProfId() ) {
			return true;
		}
		// 2 profs with same name if profId not yet set for either of them
		return ( getProfId() == 0 || p.getProfId() == 0 ) ;
	}

	@OneToMany (mappedBy="professor", fetch=FetchType.LAZY, cascade=CascadeType.REMOVE)
	public Set<Course> getCourseCollection() {
		return courseCollection;
	}
	
	public void setCourseCollection(Set<Course> courseCollection) {
		this.courseCollection = courseCollection;
	}
}
