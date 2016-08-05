package backend.rest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import backend.DatabaseConnector;
import backend.SaveCompetences;

@Path("saveusercompetences")
public class SaveUserCompetences{
	@QueryParam("userid") String userId;
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveUserCompetences(String json) {
		JSONObject obj;
		
		try {
			obj = new JSONObject(json);
		
			ArrayList<JSONObject> list = new ArrayList<JSONObject>();     
			ArrayList<SaveCompetences> saveList = new ArrayList<SaveCompetences>();
			
			JSONArray jsonArray = obj.getJSONArray("data"); 
						   
			for (int i=0;i<jsonArray.length();i++){ 
				   
			    list.add(new JSONObject(jsonArray.get(i).toString()));
			    saveList.add(new SaveCompetences(
			    		list.get(i).getString("name"), 
			    		list.get(i).getString("description"), 
			    		list.get(i).getInt("level"), 
			    		list.get(i).getInt("id"))
			    		);
				};
			   			
			DatabaseConnector dbc = new DatabaseConnector();
			dbc.updateCompetences(saveList, userId);
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
		return Response.status(200)
				.entity("Competences have been updated!")
				.build();
	}
}