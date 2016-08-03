package backend.rest;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import backend.DatabaseConnector;
import backend.Profile;

@Path("profiles")
public class GetProfiles{
    
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Profile> getProfiles() {
		DatabaseConnector db = new DatabaseConnector();
		System.out.println("De db connector: " + db);
		ArrayList<Profile> list = db.getProfiles();
		System.out.println("dit is het: "+list);
		return list;
	}
}