package backend.rest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import backend.DatabaseConnector;
import backend.Profiles;

@Path("getuserprofile")
public class GetProfileFromUser{
	@QueryParam("userid") String userId;
    
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Profiles> getUserProfiles() {
		ArrayList<Profiles> arr = new ArrayList<Profiles>();
		DatabaseConnector db = new DatabaseConnector();
		List<Profiles> profiles = db.getUserProfiles(userId);
		Iterator<Profiles> i = profiles.iterator();
		while(i.hasNext()){
			arr.add((Profiles)i.next());
		}
		return arr;
	}
}