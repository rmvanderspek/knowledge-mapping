package backend.rest;

import java.util.ArrayList;

import backend.DatabaseConnector;
import backend.Profile;

@Path("profiles")
public class GetProfiles{
    
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Item> getItems() {
		DatabaseConnector db = new DatabaseConnector();
		System.out.println("De db connector: " + db);
		ArrayList<Profile> list = db.getProfile(1);
		System.out.println("dit is het: "+list);
		return list;
	}
}