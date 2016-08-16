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
	private String userId;
	private String firstName;
	private String lastName;
	private int personalProfileId;
	
	public Persons(){
		
	}
	
	public Persons(int id, String userId, String firstName, String lastName, int personalProfileId){
		this.id = id;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.personalProfileId = personalProfileId;
	}
	
	public Persons(String userId, String firstName, String lastName, int personalProfileId){
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.personalProfileId = personalProfileId;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getPersonalProfileId() {
		return personalProfileId;
	}

	public void setPersonalProfileId(int personalProfileId) {
		this.personalProfileId = personalProfileId;
	}
	
}
