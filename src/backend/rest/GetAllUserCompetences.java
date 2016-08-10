package backend.rest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import backend.DatabaseConnector;
import backend.PersonalCompetenceLevel;

@Path("getallusercompetences")
public class GetAllUserCompetences{
    
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<PersonalCompetenceLevel> getUserCompetences() {
		DatabaseConnector db = new DatabaseConnector();
		ArrayList<PersonalCompetenceLevel> competences = db.getAllUserCompetences();
		return competences;
	}
}