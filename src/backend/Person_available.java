package backend;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "profile")
@XmlAccessorType(XmlAccessType.FIELD)

@Entity
public class Person_available {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private String user_id;
	private int available;
	private String available_date;
	
	public Person_available(){
		
	}
	
	public Person_available(int id, int available, String userid, String dateString){
		this.id = id;
		this.available = available;
		this.user_id = userid;
		this.available_date = dateString;
	}
	
	public Person_available(int available, String userid, String dateString){
		this.available = available;
		this.user_id = userid;
		this.available_date = dateString;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public String getUserid() {
		return user_id;
	}

	public void setUserid(String userid) {
		this.user_id = userid;
	}

	public String getDateString() {
		return available_date;
	}

	public void setDateString(String dateString) {
		this.available_date = dateString;
	}
	
	
}
