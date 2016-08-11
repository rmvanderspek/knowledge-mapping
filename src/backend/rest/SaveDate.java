package backend.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

import backend.DatabaseConnector;


@Path("savedate")
public class SaveDate{
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void saveDate(String json) {
		String date = "";
		String userId = "";
		try {
			JSONObject obj = new JSONObject(json);
			date = obj.getString("date");
			userId = obj.getString("userid");
		}
		catch(JSONException e){
			e.printStackTrace();
		}
		
		DatabaseConnector db = new DatabaseConnector();
		System.out.println(db.setAvailable(date, userId));		
	}	
}