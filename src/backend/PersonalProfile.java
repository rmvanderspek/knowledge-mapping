package backend;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "profile")
@XmlAccessorType(XmlAccessType.FIELD)

public class PersonalProfile {
	private int id;
	private int profileId;
	
	public PersonalProfile(){
		
	}
	
	public PersonalProfile(int id, int profileId){
		this.id = id;
		this.profileId = profileId;
	}
	
	public PersonalProfile(int profileId){
		this.profileId = profileId;
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

}
