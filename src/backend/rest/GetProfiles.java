package backend.rest;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import backend.DatabaseConnector;
import backend.Profiles;

@Path("profiles")
public class GetProfiles{
    
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Profiles> getProfiles() {
		DatabaseConnector db = new DatabaseConnector();
		List<Profiles> list = db.getProfiles();
		return list;
	}
}