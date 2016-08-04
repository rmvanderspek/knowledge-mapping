package backend.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import backend.DatabaseConnector;
import backend.SaveCompetences;

@Path("saveusercompetences")
public class SaveUserCompetences{
	//@QueryParam("userid") String userId;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public SaveCompetences[] saveUserCompetences(SaveCompetences[] saveCompetences) {
		DatabaseConnector db = new DatabaseConnector();
		System.out.println("De db connector: " + db);
		System.out.println(saveCompetences);
	//	ArrayList<Profile> list = db.getProfiles();
	//	System.out.println("dit is het: "+list);
		return saveCompetences;
	}
}