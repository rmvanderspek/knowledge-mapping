package backend.rest;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import backend.DatabaseConnector;
import backend.Persons;

@Path("getusers")
public class GetUsers{
    
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Persons> getUsers() {
		DatabaseConnector db = new DatabaseConnector();
		List<Persons> list = db.getUsers();
		
		return list;
	}
}