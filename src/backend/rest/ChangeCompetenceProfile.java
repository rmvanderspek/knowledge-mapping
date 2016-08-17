package backend.rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.json.JSONException;
import org.json.JSONObject;
import backend.DatabaseConnector;

@Path("changecompetenceprofile")
public class ChangeCompetenceProfile{


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void changeCompetenceProfile(String json) {
		JSONObject obj;
		DatabaseConnector db = new DatabaseConnector();
		try {
			obj =  new JSONObject(json);
			int competenceId = obj.getInt("competenceid");
			int profileId = obj.getInt("profileid");
			
			db.changeCompetenceProfile(competenceId, profileId);
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
	}	
}