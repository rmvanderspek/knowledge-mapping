package backend.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import backend.DatabaseConnector;
import backend.Personal_comp_level;

@Path("getusercompetences")
public class GetCompetencesFromUser{
	@QueryParam("userid") String userId;
    
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Personal_comp_level> getUserCompetences() {
		DatabaseConnector db = new DatabaseConnector();
		List<Personal_comp_level> competences = db.getUserCompetences(userId);
		return competences;
	}
}