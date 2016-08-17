package backend.rest;

import java.util.HashMap;
import java.util.Iterator;
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
		List<Persons> list = db.getUsers();
		List<Profiles> userProfiles = null;
		Map<String, List<Profiles>> usersWithProfiles = new HashMap<String, List<Profiles>>();
		Gson obj = new Gson();
		String j = null;
		
		Iterator<Persons> i = list.iterator();
		while (i.hasNext()){
			Persons p = (Persons) i.next();
			userProfiles = db.getUserProfiles(p.getUserId());
			usersWithProfiles.put(p.getUserId(), userProfiles);
			j = obj.toJson(usersWithProfiles);
		}
		
	return j;
	}
}