package backend.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import backend.DatabaseConnector;
import backend.Profile_competence_table;

@Path("getprofilecompetences")
public class GetProfileCompetences{
    
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Profile_competence_table> getCompetences() {
		DatabaseConnector db = new DatabaseConnector();
		List<Profile_competence_table> competences = db.getProfileCompetences();
		return competences;
	}
}