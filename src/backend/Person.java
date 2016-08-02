package backend;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "profile")
@XmlAccessorType(XmlAccessType.FIELD)

public class Person {
	private int id;
	private int userId;
	private String firstName;
	private String lastName;
	private int personalProfileId;
	
	public Person(){
		
	}
	
	public Person(int id, int userId, String firstName, String lastName, int personalProfileId){
		
	}
	
	public Person(int userId, String firstName, String lastName, int personalProfileId){
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
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
