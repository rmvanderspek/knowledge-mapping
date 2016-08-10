package backend.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import backend.DatabaseConnector;
import backend.Person;
import backend.PersonalCompetenceLevel;
import backend.Profile;

@Path("getuserswithprofiles")
public class GetUsersWithProfiles{
    
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getUsers() {
		DatabaseConnector db = new DatabaseConnector();
		ArrayList<Person> list = db.getUsers();
		ArrayList<Profile> userProfiles = null;
		Map<String, ArrayList<Profile>> usersWithProfiles = new HashMap<String, ArrayList<Profile>>();
		Gson obj = new Gson();
		String j = null;
		
		for (int i = 0; i < list.size(); i++){	
			userProfiles = db.getUserProfiles(list.get(i).getUserId());
			usersWithProfiles.put(list.get(i).getUserId(), userProfiles);
			
			j  = obj.toJson(usersWithProfiles);
			//obj.put(list.get(i).getUserId(), userProfiles);

		}
		
		System.out.println(j);	

		return j;
	}
}