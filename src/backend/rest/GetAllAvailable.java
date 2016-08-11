package backend.rest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import backend.Available;
import backend.DatabaseConnector;

@Path("getallavailability")
public class GetAllAvailable{
    
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Available> getAllAvailable() {
		DatabaseConnector db = new DatabaseConnector();
		ArrayList<Available> available = db.getAllAvailable();
		return available;
	}
}