package backend;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "profile")
@XmlAccessorType(XmlAccessType.FIELD)

public class Profile {
	private int id;
	private String name;
	private int profileCompetenceTableId;
	private String description;
	
	public Profile(){
		
	}
	
	public Profile(int id, String name, int profileCompetenceTableId, String description){
		this.id = id;
		this.name = name;
		this.profileCompetenceTableId = profileCompetenceTableId;
		this.description = description;
	}
	
	public Profile(String name, int profileCompetenceTableId, String description){
		this.name = name;
		this.profileCompetenceTableId = profileCompetenceTableId;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProfileCompetenceTableId() {
		return profileCompetenceTableId;
	}

	public void setProfileCompetenceTableId(int profileCompetenceTableId) {
		this.profileCompetenceTableId = profileCompetenceTableId;
	}

	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}

	
}
	


