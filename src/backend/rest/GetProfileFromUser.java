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
		System.out.println("test" + userId);
		ArrayList<Profiles> arr = new ArrayList<Profiles>();
		DatabaseConnector db = new DatabaseConnector();
		List<Profiles> profiles = db.getUserProfiles(userId);
		System.out.println(profiles.size() + "size ");
		Iterator<Profiles> i = profiles.iterator();
		while(i.hasNext()){
			System.out.println((i) + "test");
			arr.add((Profiles)i.next());
		}
		return arr;
	}
}