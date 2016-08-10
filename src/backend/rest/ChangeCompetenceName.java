package backend.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.json.JSONException;
import org.json.JSONObject;
import backend.DatabaseConnector;


@Path("changecompetencename")
public class ChangeCompetenceName{

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void changeCompetenceProfile(String json) {
		JSONObject obj;
		DatabaseConnector db = new DatabaseConnector();

		try {
			obj =  new JSONObject(json);
			int competenceId = obj.getInt("competenceid");
			String competenceName = obj.getString("competencename");
			
			db.changeCompetenceName(competenceId, competenceName);	
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
	}	
}