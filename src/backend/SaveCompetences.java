package backend;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "savecompetences")
@XmlAccessorType(XmlAccessType.FIELD)

public class SaveCompetences {
	private String name;
	private String description;
	private int level;
	private int id;
	
	public SaveCompetences(){
		
	}
	
	public SaveCompetences(String name, String description, int level, int id){
		this.name = name;
		this.description = description;
		this.level = level;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}
