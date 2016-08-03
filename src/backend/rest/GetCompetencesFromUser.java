package backend.rest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import backend.DatabaseConnector;
import backend.PersonalCompetenceLevel;

@Path("getusercompetences")
public class GetCompetencesFromUser{
	@QueryParam("userid") String userId;
    
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<PersonalCompetenceLevel> getUserCompetences() {
		DatabaseConnector db = new DatabaseConnector();
		System.out.println("De db connector: " + db);
		ArrayList<PersonalCompetenceLevel> competences = db.getUserCompetences(userId);
		System.out.println("dit is het: "+ competences);
		return competences;
	}
}