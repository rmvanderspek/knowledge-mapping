package backend.rest;

import java.util.ArrayList;

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
		ArrayList<Profile> profiles = db.getUserProfiles(userId);
		return profiles;
	}
}