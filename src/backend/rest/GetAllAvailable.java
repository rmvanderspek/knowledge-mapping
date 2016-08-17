package backend.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import backend.Person_available;
import backend.DatabaseConnector;

@Path("getallavailability")
public class GetAllAvailable{
    
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person_available> getAllAvailable() {
		DatabaseConnector db = new DatabaseConnector();
		List<Person_available> available = db.getAllAvailable();
		return available;
	}
}