package backend;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Item {
	@Id
	@GeneratedValue
	private int id;
	private String sender;
	private String receiver;
	private String subject;
	private Date date;
	private String message;
	
	public Item(){
		
	}
	
	public Item(String sender, String receiver, String subject, Date date, String message){
		this.sender = sender;
		this.receiver = receiver;
		this.subject = subject;
		this.date = date;
		this.message = message;
	}
	
	public Item(int id, String sender, String receiver, String subject, Date date, String message){
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.subject = subject;
		this.date = date;
		this.message = message;
	}
		
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return getId() + ", " + getSender() + ", " + getReceiver() + ", " + getDate() + ", " + getSubject() + "; ";
	}

	public void setMessage(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}

	
	
}
