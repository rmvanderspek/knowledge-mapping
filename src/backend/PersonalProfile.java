package backend;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "profile")
@XmlAccessorType(XmlAccessType.FIELD)

public class PersonalProfile {
	private int id;
	private int profileId;
	private String userId;
	
	public PersonalProfile(){
		
	}
	
	public PersonalProfile(int id, int profileId, String userId){
		this.id = id;
		this.profileId = profileId;
		this.userId = userId;
	}
	
	public PersonalProfile(int profileId, String userId){
		this.profileId = profileId;
		this.userId = userId;
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
	
	public String getUserId(){
		return userId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}

}
