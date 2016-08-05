package backend.rest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import backend.DatabaseConnector;
import backend.ProfileCompetences;

@Path("getprofilecompetences")
public class GetProfileCompetences{
    
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ProfileCompetences> getCompetences() {
		DatabaseConnector db = new DatabaseConnector();
		ArrayList<ProfileCompetences> competences = db.getProfileCompetences();
		return competences;
	}
}