package backend;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "profile")
@XmlAccessorType(XmlAccessType.FIELD)

public class ProfileCompetences {
	private int id;
	private int profileId;
	private int competenceId;
	
	public ProfileCompetences(){
		
	}
	
	public ProfileCompetences(int id, int profileId, int competenceId){
		this.id = id;
		this.profileId = profileId;
		this.competenceId = competenceId;
	}
	
	public ProfileCompetences(int profileId, int competenceId){
		this.profileId = profileId;
		this.competenceId = competenceId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public int getCompetenceId() {
		return competenceId;
	}

	public void setCompetenceId(int competenceId) {
		this.competenceId = competenceId;
	}
	
}
