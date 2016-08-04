package backend;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "profile")
@XmlAccessorType(XmlAccessType.FIELD)

public class PersonalCompetenceLevel {
	private int id;
	private int competenceId;
	private int competenceLevel;
	private String personId;
	
	public PersonalCompetenceLevel(){
		
	}
	
	public PersonalCompetenceLevel(int id, int competenceId, int competenceLevel, String personId){
		this.id = id;
		this.competenceId = competenceId;
		this.competenceLevel = competenceLevel;
		this.personId = personId;
	}
	
	public PersonalCompetenceLevel(int competenceId, int competenceLevel, String personId){
		this.competenceId = competenceId;
		this.competenceLevel = competenceLevel;
		this.personId = personId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCompetenceId() {
		return competenceId;
	}

	public void setCompetenceId(int competenceId) {
		this.competenceId = competenceId;
	}

	public int getCompetenceLevel() {
		return competenceLevel;
	}

	public void setCompetenceLevel(int competenceLevel) {
		this.competenceLevel = competenceLevel;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}
	
	
}
