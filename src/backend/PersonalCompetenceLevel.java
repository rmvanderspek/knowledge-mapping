package backend;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "profile")
@XmlAccessorType(XmlAccessType.FIELD)

public class PersonalCompetenceLevel {
	private int id;
	private int competenceId;
	private int competenceLevel;
	private int personId;
	
	public PersonalCompetenceLevel(){
		
	}
	
	public PersonalCompetenceLevel(int id, int competenceId, int competenceLevel, int personId){
		
	}
	
	public PersonalCompetenceLevel(int competenceId, int competenceLevel, int personId){
		
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

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}
	
	
}
