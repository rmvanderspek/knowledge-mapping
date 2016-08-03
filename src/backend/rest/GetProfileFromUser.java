package backend.rest;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import backend.DatabaseConnector;
import backend.Profile;

@Path("getuserprofile")
public class GetProfileFromUser{
	@QueryParam("userid") String userId;
    
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Profile> getUserProfiles() {
		DatabaseConnector db = new DatabaseConnector();
		System.out.println("De db connector: " + db);
		ArrayList<Profile> profiles = db.getUserProfiles(userId);
		System.out.println("dit is het: "+ profiles);
		return profiles;
	}
}