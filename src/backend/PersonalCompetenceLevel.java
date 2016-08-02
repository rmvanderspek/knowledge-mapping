package backend;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "profile")
@XmlAccessorType(XmlAccessType.FIELD)

public class PersonalCompetenceLevel {
	private int id;
	private int competenceId;
	private int level;
	private int personId;
	
	public PersonalCompetenceLevel(){
		
	}
	
	public PersonalCompetenceLevel(int id, int competenceId, int level, int personId){
		
	}
	
	public PersonalCompetenceLevel(int competenceId, int level, int personId){
		
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}
	
	
}
