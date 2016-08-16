package backend.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import backend.DatabaseConnector;
import backend.Persons;
import backend.Profiles;

@Path("getuserswithprofiles")
public class GetUsersWithProfiles{
    
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getUsers() {
		DatabaseConnector db = new DatabaseConnector();
		ArrayList<Persons> list = db.getUsers();
		List<Profiles> userProfiles = null;
		Map<String, List<Profiles>> usersWithProfiles = new HashMap<String, List<Profiles>>();
		Gson obj = new Gson();
		String j = null;
		
		for (int i = 0; i < list.size(); i++){	
			userProfiles = db.getUserProfiles(list.get(i).getUserId());
			usersWithProfiles.put(list.get(i).getUserId(), userProfiles);
			
			j  = obj.toJson(usersWithProfiles);
		}
		return j;
	}
}