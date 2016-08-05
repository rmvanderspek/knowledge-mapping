package backend.rest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import backend.Competence;
import backend.DatabaseConnector;
import backend.PersonalCompetenceLevel;
import backend.SaveCompetences;

@Path("addcompetence")
public class AddCompetence{
@QueryParam("userid") String userId;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addCompetences(String json) {
		JSONObject obj;
		Competence competence = null;
		DatabaseConnector db = new DatabaseConnector();

		try {
			obj = (JSONObject) new JSONObject(json).get("data");
			String name = obj.getString("name");
			String description = obj.getString("description");
			competence = new Competence(name, description);
			db.addCompetence(competence);

			ArrayList<Competence> competenceList = db.getCompetences();
			Competence newComp = competenceList.get(competenceList.size() - 1);
			int id = newComp.getId();

			db.addCompetenceToUser(new SaveCompetences(
					name, description, 0, id), userId);

		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
	}	
}