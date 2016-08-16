package backend;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "profile")
@XmlAccessorType(XmlAccessType.FIELD)

@Entity
public class Personal_profile {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private int profile_id;
	private String user_id;
	
	public Personal_profile(){
		
	}
	
	public Personal_profile(int id, int profileId, String userId){
		this.id = id;
		this.profile_id = profileId;
		this.user_id = userId;
	}
	
	public Personal_profile(int profileId, String userId){
		this.profile_id = profileId;
		this.user_id = userId;
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
	
	public String getUserId(){
		return user_id;
	}
	
	public void setUserId(String userId){
		this.user_id = userId;
	}

}
