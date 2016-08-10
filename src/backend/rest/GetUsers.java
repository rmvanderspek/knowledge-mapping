package backend.rest;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import backend.DatabaseConnector;
import backend.Person;

@Path("getusers")
public class GetUsers{
    
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Person> getUsers() {
		DatabaseConnector db = new DatabaseConnector();
		ArrayList<Person> list = db.getUsers();
		
		return list;
	}
}