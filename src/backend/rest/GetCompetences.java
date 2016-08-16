package backend.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import backend.Competences;
import backend.DatabaseConnector;

@Path("getcompetences")
public class GetCompetences{
    
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Competences> getCompetences() {
		DatabaseConnector db = new DatabaseConnector();
		List<Competences> competences = db.getCompetences();
		return competences;
	}
}	