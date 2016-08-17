package backend.rest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

import backend.Competences;
import backend.DatabaseConnector;
import backend.Profile_competence_table;
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
			List<Competences> competenceList = (List<Competences>) db.getCompetences();
			Iterator i = competenceList.iterator();
			
			while(i.hasNext()){
				Competences c = (Competences)i.next();
				if (c.getName().equals(name)){
					exists = true;
					id = c.getId();
				}
			}
			if (!exists){
				updatingDone = db.addCompetence(competence);
				while(!updatingDone){
					
				}
				competenceList = (List<Competences>)db.getCompetences();
				Competences newComp = null;
				
				Iterator i2 = competenceList.iterator();
				while (i2.hasNext()){
					newComp = (Competences)i2.next();
				}
				
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
						new Profile_competence_table(999, id));
			}
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
	}	
}