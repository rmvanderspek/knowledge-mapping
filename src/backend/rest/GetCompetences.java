package backend.rest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import backend.Competence;
import backend.DatabaseConnector;

@Path("getcompetences")
public class GetCompetences{
    
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Competence> getCompetences() {
		DatabaseConnector db = new DatabaseConnector();
		ArrayList<Competence> competences = db.getCompetences();
		return competences;
	}
}	