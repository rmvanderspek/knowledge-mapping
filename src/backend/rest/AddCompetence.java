package backend.rest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

import backend.Competences;
import backend.DatabaseConnector;
import backend.ProfileCompetences;
import backend.SaveCompetences;

@Path("addcompetence")
public class AddCompetence{
@QueryParam("userid") String userId;
boolean updatingDone = false;
boolean exists = false;
int id = 0;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addCompetences(String json) {
		JSONObject obj;
		Competences competence = null;
		DatabaseConnector db = new DatabaseConnector();

		try {
			obj = (JSONObject) new JSONObject(json).get("data");
			String name = obj.getString("name");
			String description = obj.getString("description");
			
			competence = new Competences(name, description);
			ArrayList<Competences> competenceList = (ArrayList<Competences>) db.getCompetences();
			
			for(int i = 0; i < competenceList.size(); i++){
				Competences c = competenceList.get(i);
				if (c.getName().equals(name)){
					exists = true;
					id = c.getId();
				}
			}
			if (!exists){
				updatingDone = db.addCompetence(competence);
				while(!updatingDone){
				
				}
				competenceList = (ArrayList<Competences>) db.getCompetences();
				Competences newComp = competenceList.get(competenceList.size() - 1);
				
				id = newComp.getId();
			}
			
			db.addCompetenceToUser(
					new SaveCompetences(
					name, 
					description, 
					0, 
					id), userId);
			
			if (!exists){
				db.addCompetenceToProfile(
						new ProfileCompetences(999, id));
			}
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
	}	
}