package backend;

import javax.persistence.*;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "profile")
@XmlAccessorType(XmlAccessType.FIELD)

@Entity
public class Personal_comp_level {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private int comp_id;
	private int comp_level;
	private String user_id;
	
	public Personal_comp_level(){
		
	}
	
	public Personal_comp_level(int id, int competenceId, int competenceLevel, String personId){
		this.id = id;
		this.comp_id = competenceId;
		this.comp_level = competenceLevel;
		this.user_id = personId;
	}
	
	public Personal_comp_level(int competenceId, int competenceLevel, String personId){
		this.comp_id = competenceId;
		this.comp_level = competenceLevel;
		this.user_id = personId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCompetenceId() {
		return comp_id;
	}

	public void setCompetenceId(int competenceId) {
		this.comp_id = competenceId;
	}

	public int getCompetenceLevel() {
		return comp_level;
	}

	public void setCompetenceLevel(int competenceLevel) {
		this.comp_level = competenceLevel;
	}

	public String getPersonId() {
		return user_id;
	}

	public void setPersonId(String personId) {
		this.user_id = personId;
	}
	
	
}
