package backend;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "profile")
@XmlAccessorType(XmlAccessType.FIELD)

@Entity
public class Profile_competence_table {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private int profile_id;
	private int competences;
	
	public Profile_competence_table(){
		
	}
	
	public Profile_competence_table(int id, int profileId, int competenceId){
		this.id = id;
		this.profile_id = profileId;
		this.competences = competenceId;
	}
	
	public Profile_competence_table(int profileId, int competenceId){
		this.profile_id = profileId;
		this.competences = competenceId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProfileId() {
		return profile_id;
	}

	public void setProfileId(int profileId) {
		this.profile_id = profileId;
	}

	public int getCompetenceId() {
		return competences;
	}

	public void setCompetenceId(int competenceId) {
		this.competences = competenceId;
	}
	
}
