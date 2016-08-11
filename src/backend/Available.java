package backend;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "profile")
@XmlAccessorType(XmlAccessType.FIELD)

public class Available {
	private int id;
	private int available;
	private String userid;
	private String dateString;
	
	public Available(){
		
	}
	
	public Available(int id, int available, String userid, String dateString){
		this.id = id;
		this.available = available;
		this.userid = userid;
		this.dateString = dateString;
	}
	
	public Available(int available, String userid, String dateString){
		this.available = available;
		this.userid = userid;
		this.dateString = dateString;
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
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	
	
}
