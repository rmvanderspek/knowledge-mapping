package backend;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "profile")
@XmlAccessorType(XmlAccessType.FIELD)

@Entity
public class Profiles {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private String name;
	private int prof_comp_table_id;
	private String description;
	
	public Profiles(){
		
	}
	
	public Profiles(int id, String name, int profileCompetenceTableId, String description){
		this.id = id;
		this.name = name;
		this.prof_comp_table_id = profileCompetenceTableId;
		this.description = description;
	}
	
	public Profiles(String name, int profileCompetenceTableId, String description){
		this.name = name;
		this.prof_comp_table_id = profileCompetenceTableId;
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
		return prof_comp_table_id;
	}

	public void setProfileCompetenceTableId(int profileCompetenceTableId) {
		this.prof_comp_table_id = profileCompetenceTableId;
	}

	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}

	public boolean equals(Object object){
		if(object instanceof Profiles){
			Profiles p = (Profiles) object;
			if (p.getId() == this.id){
				return true;
			}
		}
		return false;	
	}
	
	public int hashCode(){
		return id;
	}
}
	


