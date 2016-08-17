package backend;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "profile")
@XmlAccessorType(XmlAccessType.FIELD)

@Entity
public class Persons {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private String firstname;
	private String lastname;
	private String userid;
	private int profile_id;
	
	public Persons(){
		
	}
	
	public Persons(int id, String userId, String firstName, String lastName, int personalProfileId){
		this.id = id;
		this.firstname = firstName;
		this.lastname = lastName;
		this.userid = userId;
		this.profile_id = personalProfileId;
	}
	
	public Persons(String userId, String firstName, String lastName, int personalProfileId){
		this.firstname = firstName;
		this.lastname = lastName;
		this.userid = userId;
		this.profile_id = personalProfileId;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstname;
	}

	public void setFirstName(String firstName) {
		this.firstname = firstName;
	}

	public String getLastName() {
		return lastname;
	}

	public void setLastName(String lastName) {
		this.lastname = lastName;
	}
	
	public String getUserId() {
		return userid;
	}

	public void setUserId(String userId) {
		this.userid = userId;
	}
	
	public int getPersonalProfileId() {
		return profile_id;
	}

	public void setPersonalProfileId(int personalProfileId) {
		this.profile_id = personalProfileId;
	}
	
}
